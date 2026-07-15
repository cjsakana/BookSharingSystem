package kg.us.sakanatang.controller;

import kg.us.sakanatang.common.ApiResponse;
import kg.us.sakanatang.domain.entity.Article;
import kg.us.sakanatang.domain.vo.ArticleVO;
import kg.us.sakanatang.domain.vo.RecommendVO;
import kg.us.sakanatang.service.ArticleService;
import kg.us.sakanatang.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping()
    public ArrayList<ArticleVO> getArticle(@RequestParam(value = "query", defaultValue = "") String query,
                                           @RequestParam(value = "tag", defaultValue = "0") int tag,
                                           @RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        List<ArticleVO> articles = articleService.getArticleList(page, size, tag, query);
        return (ArrayList<ArticleVO>) articles;
    }

    @GetMapping("/{id}")
    public ApiResponse<ArticleVO> getArticle(
            @PathVariable(value = "id") int articleId) {
        try {
            ArticleVO articleVO = articleService.getArticleById(articleId);
            if (articleVO == null) {
                return ApiResponse.fail(500, "获取文章失败");
            }
            return ApiResponse.success(articleVO);
        } catch (Exception e) {
            return ApiResponse.fail(500, "获取文章失败");
        }
    }


    @PostMapping()
    public ApiResponse<String> create(@RequestAttribute(value = "id", required = true) int id,
                                      @RequestBody Map<String, Object> params) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            String bookName = (String) params.get("bookName");
            String cover = (String) params.get("cover");
            String title = (String) params.get("title");
            String content = (String) params.get("content");
            if (bookName == null || title == null || content == null) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }
            if (bookName.isEmpty() || title.isEmpty() || content.isEmpty()) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }

            int isPublished = (int) params.get("isPublished");
            int tag = (int) params.get("tag");
            String isbn = (String) params.get("isbn");

            Article article = new Article();
            article.setBookName(bookName);
            article.setIsbn(isbn);
            article.setCover(cover);
            article.setTitle(title);
            article.setContent(content);
            article.setUserId(id);
            article.setTag(tag);
            article.setIsPublished(isPublished);

            boolean ok = articleService.createArticle(article);
            if (!ok) {
                return ApiResponse.fail(500, "创建文章失败");

            }
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "创建文章失败");
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<String> update(@RequestAttribute(value = "id", required = true) int userid,
                                      @PathVariable(value = "id") int articleId,
                                      @RequestBody Map<String, Object> params) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            // 查询图书信息
            Article article = new Article();
            ArticleVO articleVO = articleService.getArticleById(articleId);
            if (articleVO.getUserId() != userid) {
                return ApiResponse.fail(401, "无权限");
            }
            System.out.println(articleVO);
            BeanUtils.copyProperties(articleVO, article);
            System.out.println(222);


            String bookName = (String) params.get("bookName");
            String cover = (String) params.get("cover");
            String title = (String) params.get("title");
            String content = (String) params.get("content");
            if (bookName == null || title == null || content == null) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }
            if (bookName.isEmpty() || title.isEmpty() || content.isEmpty()) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }

            int tag = (int) params.get("tag");
            int isPublished = (int) params.get("isPublished");

            article.setBookName(bookName);
            article.setCover(cover);
            article.setTitle(title);
            article.setContent(content);
            article.setTag(tag);
            article.setIsPublished(isPublished);
            article.setCover("https://sakanatang.dpdns.org/default-img.png");

            System.out.println(111);
            articleService.updateArticle(article);

            return ApiResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.fail(500, "修改文章失败");
        }
    }

    @PutMapping("/{id}/publish")
    public ApiResponse<Boolean> publishArticle(@PathVariable Integer id) {
        try {
            articleService.togglePublishStatus(id, true);
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "发表文章失败");
        }
    }

    @PutMapping("/{id}/unpublish")
    public ApiResponse<Boolean> unpublishArticle(@PathVariable Integer id) {
        try {
            articleService.togglePublishStatus(id, false);
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "取消发表文章失败");
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> del(@RequestAttribute(value = "id", required = true) int userid,
                                   @RequestAttribute(value = "role", required = true) int role,
                                   @PathVariable(value = "id") int articleId) {
        try {
            // 删除
            articleService.deleteArticle(articleId);

            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "删除文章失败");
        }
    }

    @GetMapping("/getUserArticle")
    public List<ArticleVO> getPersonalArticle(@RequestAttribute(value = "id", required = true) int userid,
                                              @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        return articleService.getUserArticles(userid, page, size);
    }

    @GetMapping("/recommend")
    public ApiResponse<List<RecommendVO>> recommend() {
        String key = "BookSharingSystem::recommend";
        // 先从缓存获取
        Set<Object> recommendVOSet = redisUtil.getZSetReverseRange(key, 0, -1);
        List<RecommendVO> recommendVOS;
        if (recommendVOSet != null && !recommendVOSet.isEmpty()) {
            // 缓存命中，类型转换
            recommendVOS = new ArrayList<>();
            for (Object obj : recommendVOSet) {
                if (obj instanceof RecommendVO) {
                    recommendVOS.add((RecommendVO) obj);
                }
            }
        } else {
            // 缓存未命中，从服务获取
            recommendVOS = articleService.recommend();
            // 写入缓存并设置1小时过期
            if (recommendVOS != null && !recommendVOS.isEmpty()) {
                for (RecommendVO vo : recommendVOS) {
                    redisUtil.addToZSet(key, vo, vo.getId()); // 分数以id，保证顺序
                }
                redisUtil.expire(key, 1, java.util.concurrent.TimeUnit.HOURS);
            }
        }
        return ApiResponse.success(recommendVOS);
    }

    @PostMapping("/uploadCover")
    public ApiResponse<String> uploadCover(@RequestAttribute(value = "id", required = true) int userid,
                                           @RequestParam("cover") MultipartFile file,
                                           HttpSession session) {
        // 获取项目根路径
        String uploadPath = "C:\\Users\\sakana\\Desktop\\java学习\\广软\\BookSharingSystem\\src\\main\\webapp\\images\\cover\\";
        System.out.println(uploadPath);

        if (file.isEmpty()) {
            return ApiResponse.fail(500, "请上传图片");
        }
        try {
            // 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 生成固定文件名（基于用户会话）
            String sessionId = session.getId();
            String newFilename = String.valueOf(userid)+"_" + sessionId + suffix;

            // 创建目录
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 删除用户之前的图片（如果存在）
            File[] existingFiles = dir.listFiles((d, name)
                    -> name.startsWith(userid + "_" + sessionId));
            if (existingFiles != null) {
                for (File existingFile : existingFiles) {
                    existingFile.delete();
                }
            }

            // 保存新文件
            File dest = new File(uploadPath + newFilename);
            file.transferTo(dest);

            // 返回访问路径
            String accessUrl = "http://localhost:9090/images/cover/" + newFilename;

            return ApiResponse.success(accessUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.fail(500, "上传失败: " + e.getMessage());
        }
    }
}
