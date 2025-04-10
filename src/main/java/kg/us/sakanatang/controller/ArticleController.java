package kg.us.sakanatang.controller;

import kg.us.sakanatang.common.ApiResponse;
import kg.us.sakanatang.domain.entity.Article;
import kg.us.sakanatang.domain.vo.ArticleVO;
import kg.us.sakanatang.service.ArticleService;
import kg.us.sakanatang.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    ArticleServiceImpl articleServiceImpl;


    @GetMapping()
    public ArrayList<Article> getArticle(@RequestParam(value = "content", defaultValue = "") String content,
                                         @RequestParam(value = "tag", defaultValue = "0") int tag,
                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "12") int size) {
        if (page == 1) {
            return new ArrayList<>(Arrays.asList(
                    new Article() {{
                        setId(1);
                        setCreatedAt(new Date(1673764200000L)); // 2023-01-15 09:30:00
                        setUpdatedAt(new Date(1674203100000L)); // 2023-01-20 14:25:00
                        setDeletedAt(null);
                        setBookName("三体");
                        setIsbn("9787536692930");
                        setCover("https://example.com/covers/santi.jpg");
                        setTitle("《三体》中的宇宙社会学思考");
                        setContent("《三体》系列展示了宇宙中可能存在的黑暗森林法则...");
                        setUserId(101);
                        setTag(1);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(2);
                        setCreatedAt(new Date(1676020800000L)); // 2023-02-10 11:20:00
                        setUpdatedAt(new Date(1676464800000L)); // 2023-02-15 16:40:00
                        setDeletedAt(null);
                        setBookName("活着");
                        setIsbn("9787506365437");
                        setCover("https://example.com/covers/huozhe.jpg");
                        setTitle("《活着》中的人生哲学");
                        setContent("余华的《活着》通过福贵的一生展现了生命的韧性...");
                        setUserId(102);
                        setTag(2);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(3);
                        setCreatedAt(new Date(1678011300000L)); // 2023-03-05 14:15:00
                        setUpdatedAt(new Date(1678011300000L)); // 2023-03-05 14:15:00
                        setDeletedAt(null);
                        setBookName("人类简史");
                        setIsbn("9787508647357");
                        setCover(null);
                        setTitle("从《人类简史》看认知革命");
                        setContent("赫拉利提出的认知革命改变了人类的发展轨迹...");
                        setUserId(103);
                        setTag(3);
                        setIsPublished(0);
                    }},
                    new Article() {{
                        setId(4);
                        setCreatedAt(new Date(1681814700000L)); // 2023-04-18 10:45:00
                        setUpdatedAt(new Date(1681983000000L)); // 2023-04-20 09:30:00
                        setDeletedAt(null);
                        setBookName("小王子");
                        setIsbn("9787020042494");
                        setCover("https://example.com/covers/xiaowangzi.jpg");
                        setTitle("《小王子》的成人童话世界");
                        setContent("这本写给大人的童话蕴含着深刻的哲学思考...");
                        setUserId(104);
                        setTag(4);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(5);
                        setCreatedAt(new Date(1684750800000L)); // 2023-05-22 16:20:00
                        setUpdatedAt(new Date(1685006100000L)); // 2023-05-25 11:15:00
                        setDeletedAt(null);
                        setBookName("白夜行");
                        setIsbn("9787544258609");
                        setCover(null);
                        setTitle("东野圭吾《白夜行》的叙事艺术");
                        setContent("小说中复杂的叙事结构和人物关系令人叹服...");
                        setUserId(105);
                        setTag(5);
                        setIsPublished(1);
                    }},
                    new Article() {
                        {
                            setId(6);
                            setCreatedAt(new Date(1688116200000L)); // 2023-06-30 13:10:00
                            setUpdatedAt(new Date(1688539500000L)); // 2023-07-05 08:45:00
                            setDeletedAt(null);
                            setBookName("Python编程从入门到实践");
                            setIsbn("9787115428028");
                            setCover("https://example.com/covers/python.jpg");
                            setTitle("Python学习心得分享");
                            setContent("这本书是Python初学者的绝佳选择...");
                            setUserId(106);
                            setTag(6);
                            setIsPublished(1);
                        }
                    },
                    new Article() {{
                        setId(7);
                        setCreatedAt(new Date(1689161400000L)); // 2023-07-12 15:30:00
                        setUpdatedAt(new Date(1689161400000L)); // 2023-07-12 15:30:00
                        setDeletedAt(null);
                        setBookName("百年孤独");
                        setIsbn("9787544258975");
                        setCover("https://example.com/covers/bainiangudu.jpg");
                        setTitle("马尔克斯的魔幻现实主义");
                        setContent("布恩迪亚家族七代人的兴衰展现了拉美的历史...");
                        setUserId(107);
                        setTag(2);
                        setIsPublished(0);
                    }},
                    new Article() {{
                        setId(8);
                        setCreatedAt(new Date(1691486100000L)); // 2023-08-08 09:15:00
                        setUpdatedAt(new Date(1691662800000L)); // 2023-08-10 14:20:00
                        setDeletedAt(null);
                        setBookName("时间简史");
                        setIsbn("9787535732309");
                        setCover(null);
                        setTitle("霍金《时间简史》读后感");
                        setContent("这本书让我对宇宙和时间的理解更加深入...");
                        setUserId(108);
                        setTag(3);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(9);
                        setCreatedAt(new Date(1695098400000L)); // 2023-09-19 11:40:00
                        setUpdatedAt(new Date(1695357000000L)); // 2023-09-22 10:30:00
                        setDeletedAt(null);
                        setBookName("围城");
                        setIsbn("9787020090006");
                        setCover("https://example.com/covers/weicheng.jpg");
                        setTitle("《围城》中的现代人困境");
                        setContent("钱钟书笔下的方鸿渐形象极具代表性...");
                        setUserId(109);
                        setTag(2);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(10);
                        setCreatedAt(new Date(1698231000000L)); // 2023-10-25 14:50:00
                        setUpdatedAt(new Date(1698495300000L)); // 2023-10-28 16:15:00
                        setDeletedAt(null);
                        setBookName("明朝那些事儿");
                        setIsbn("9787506341271");
                        setCover(null);
                        setTitle("通俗历史写作的典范");
                        setContent("这本书让历史变得生动有趣...");
                        setUserId(110);
                        setTag(7);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(13);
                        setCreatedAt(new Date(1698511800000L)); // 2023-10-28 23:50:00
                        setUpdatedAt(new Date(1698776100000L)); // 2023-10-31 01:15:00
                        setDeletedAt(null);
                        setBookName("活着");
                        setIsbn("9787506365437");
                        setCover(null);
                        setTitle("苦难与坚韧的生命");
                        setContent("人是为了活着本身而活着...");
                        setUserId(113);
                        setTag(2);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(14);
                        setCreatedAt(new Date(1698605400000L)); // 2023-10-30 02:50:00
                        setUpdatedAt(new Date(1698869700000L)); // 2023-11-02 04:15:00
                        setDeletedAt(null);
                        setBookName("百年孤独");
                        setIsbn("9787544258974");
                        setCover(null);
                        setTitle("魔幻现实主义的巅峰");
                        setContent("布恩迪亚家族的百年兴衰...");
                        setUserId(114);
                        setTag(4);
                        setIsPublished(1);
                    }}
            ));
        } else if (page == 2) {
            return new ArrayList<>(Arrays.asList(
                    new Article() {{
                        setId(10);
                        setCreatedAt(new Date(1698231000000L)); // 2023-10-25 14:50:00
                        setUpdatedAt(new Date(1698495300000L)); // 2023-10-28 16:15:00
                        setDeletedAt(null);
                        setBookName("明朝那些事儿");
                        setIsbn("9787506341271");
                        setCover(null);
                        setTitle("通俗历史写作的典范");
                        setContent("这本书让历史变得生动有趣...");
                        setUserId(110);
                        setTag(7);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(11);
                        setCreatedAt(new Date(1698324600000L)); // 2023-10-26 17:50:00
                        setUpdatedAt(new Date(1698588900000L)); // 2023-10-29 19:15:00
                        setDeletedAt(null);
                        setBookName("人类简史");
                        setIsbn("9787508647357");
                        setCover(null);
                        setTitle("从动物到上帝");
                        setContent("一部颠覆认知的人类发展史...");
                        setUserId(111);
                        setTag(3);
                        setIsPublished(1);
                    }},
                    new Article() {{
                        setId(12);
                        setCreatedAt(new Date(1698418200000L)); // 2023-10-27 20:50:00
                        setUpdatedAt(new Date(1698682500000L)); // 2023-10-30 22:15:00
                        setDeletedAt(null);
                        setBookName("三体");
                        setIsbn("9787536692930");
                        setCover(null);
                        setTitle("黑暗森林法则");
                        setContent("宇宙就是一座黑暗森林...");
                        setUserId(112);
                        setTag(5);
                        setIsPublished(1);
                    }}

            ));
        } else {
            return new ArrayList<>();
        }
//        ArrayList<Article> list =new ArrayList<>(Arrays.asList(
//                new Article() {{
//                    setId(1);
//                    setCreatedAt(new Date(1673764200000L)); // 2023-01-15 09:30:00
//                    setUpdatedAt(new Date(1674203100000L)); // 2023-01-20 14:25:00
//                    setDeletedAt(null);
//                    setBookName("三体");
//                    setIsbn("9787536692930");
//                    setCover("https://example.com/covers/santi.jpg");
//                    setTitle("《三体》中的宇宙社会学思考");
//                    setContent("《三体》系列展示了宇宙中可能存在的黑暗森林法则...");
//                    setUserId(101);
//                    setTag(1);
//                    setIsPublished(1);
//                }},
//                new Article() {{
//                    setId(2);
//                    setCreatedAt(new Date(1676020800000L)); // 2023-02-10 11:20:00
//                    setUpdatedAt(new Date(1676464800000L)); // 2023-02-15 16:40:00
//                    setDeletedAt(null);
//                    setBookName("活着");
//                    setIsbn("9787506365437");
//                    setCover("https://example.com/covers/huozhe.jpg");
//                    setTitle("《活着》中的人生哲学");
//                    setContent("余华的《活着》通过福贵的一生展现了生命的韧性...");
//                    setUserId(102);
//                    setTag(2);
//                    setIsPublished(1);
//                }},
//                new Article() {{
//                    setId(3);
//                    setCreatedAt(new Date(1678011300000L)); // 2023-03-05 14:15:00
//                    setUpdatedAt(new Date(1678011300000L)); // 2023-03-05 14:15:00
//                    setDeletedAt(null);
//                    setBookName("人类简史");
//                    setIsbn("9787508647357");
//                    setCover(null);
//                    setTitle("从《人类简史》看认知革命");
//                    setContent("赫拉利提出的认知革命改变了人类的发展轨迹...");
//                    setUserId(103);
//                    setTag(3);
//                    setIsPublished(0);
//                }},
//                new Article() {{
//                    setId(4);
//                    setCreatedAt(new Date(1681814700000L)); // 2023-04-18 10:45:00
//                    setUpdatedAt(new Date(1681983000000L)); // 2023-04-20 09:30:00
//                    setDeletedAt(null);
//                    setBookName("小王子");
//                    setIsbn("9787020042494");
//                    setCover("https://example.com/covers/xiaowangzi.jpg");
//                    setTitle("《小王子》的成人童话世界");
//                    setContent("这本写给大人的童话蕴含着深刻的哲学思考...");
//                    setUserId(104);
//                    setTag(4);
//                    setIsPublished(1);
//                }},
//                new Article() {{
//                    setId(5);
//                    setCreatedAt(new Date(1684750800000L)); // 2023-05-22 16:20:00
//                    setUpdatedAt(new Date(1685006100000L)); // 2023-05-25 11:15:00
//                    setDeletedAt(null);
//                    setBookName("白夜行");
//                    setIsbn("9787544258609");
//                    setCover(null);
//                    setTitle("东野圭吾《白夜行》的叙事艺术");
//                    setContent("小说中复杂的叙事结构和人物关系令人叹服...");
//                    setUserId(105);
//                    setTag(5);
//                    setIsPublished(1);
//                }},
//                new Article() {{
//                    setId(6);
//                    setCreatedAt(new Date(1688116200000L)); // 2023-06-30 13:10:00
//                    setUpdatedAt(new Date(1688539500000L)); // 2023-07-05 08:45:00
//                    setDeletedAt(null);
//                    setBookName("Python编程从入门到实践");
//                    setIsbn("9787115428028");
//                    setCover("https://example.com/covers/python.jpg");
//                    setTitle("Python学习心得分享");
//                    setContent("这本书是Python初学者的绝佳选择...");
//                    setUserId(106);
//                    setTag(6);
//                    setIsPublished(1);
//                }},
//                new Article() {{
//                    setId(7);
//                    setCreatedAt(new Date(1689161400000L)); // 2023-07-12 15:30:00
//                    setUpdatedAt(new Date(1689161400000L)); // 2023-07-12 15:30:00
//                    setDeletedAt(null);
//                    setBookName("百年孤独");
//                    setIsbn("9787544258975");
//                    setCover("https://example.com/covers/bainiangudu.jpg");
//                    setTitle("马尔克斯的魔幻现实主义");
//                    setContent("布恩迪亚家族七代人的兴衰展现了拉美的历史...");
//                    setUserId(107);
//                    setTag(2);
//                    setIsPublished(0);
//                }},
//                new Article() {{
//                    setId(8);
//                    setCreatedAt(new Date(1691486100000L)); // 2023-08-08 09:15:00
//                    setUpdatedAt(new Date(1691662800000L)); // 2023-08-10 14:20:00
//                    setDeletedAt(null);
//                    setBookName("时间简史");
//                    setIsbn("9787535732309");
//                    setCover(null);
//                    setTitle("霍金《时间简史》读后感");
//                    setContent("这本书让我对宇宙和时间的理解更加深入...");
//                    setUserId(108);
//                    setTag(3);
//                    setIsPublished(1);
//                }},
//                new Article() {{
//                    setId(9);
//                    setCreatedAt(new Date(1695098400000L)); // 2023-09-19 11:40:00
//                    setUpdatedAt(new Date(1695357000000L)); // 2023-09-22 10:30:00
//                    setDeletedAt(null);
//                    setBookName("围城");
//                    setIsbn("9787020090006");
//                    setCover("https://example.com/covers/weicheng.jpg");
//                    setTitle("《围城》中的现代人困境");
//                    setContent("钱钟书笔下的方鸿渐形象极具代表性...");
//                    setUserId(109);
//                    setTag(2);
//                    setIsPublished(1);
//                }},
//                new Article() {{
//                    setId(10);
//                    setCreatedAt(new Date(1698231000000L)); // 2023-10-25 14:50:00
//                    setUpdatedAt(new Date(1698495300000L)); // 2023-10-28 16:15:00
//                    setDeletedAt(null);
//                    setBookName("明朝那些事儿");
//                    setIsbn("9787506341271");
//                    setCover(null);
//                    setTitle("通俗历史写作的典范");
//                    setContent("这本书让历史变得生动有趣...");
//                    setUserId(110);
//                    setTag(7);
//                    setIsPublished(1);
//                }}
//        ));
//
//        return list;
    }

    @GetMapping("/{id}")
    public ApiResponse<Article> getPersonArticle(
            @RequestAttribute(value = "id", required = true) int id,
            @PathVariable(value = "id") int articleId) {
        try {
            Article article=new Article();
            return ApiResponse.success(article);
        }catch (Exception e){
            return ApiResponse.fail(500,"获取文章失败");
        }
    }


    @PostMapping()
    public ApiResponse<String> create(@RequestAttribute(value = "id", required = true) int id,
                                      @RequestBody Map<String, String> params) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            String bookName = params.get("bookName");
            String cover = params.get("cover");
            String title = params.get("title");
            String content = params.get("content");
            if (bookName == null || cover == null || title == null || content == null) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }
            if (bookName.isEmpty() || cover.isEmpty() || title.isEmpty() || content.isEmpty()) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }

            String tagStr = params.get("tag");
            int tag = Integer.parseInt(tagStr);
            String isbn = params.get("isbn");

            Article article = new Article();
            article.setBookName(bookName);
            article.setIsbn(isbn);
            article.setCover(cover);
            article.setTitle(title);
            article.setContent(content);
            article.setUserId(id);
            article.setTag(tag);

//            articleServiceImpl.

            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "创建文章失败");
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<String> update(@RequestAttribute(value = "id", required = true) int userid,
                                      @PathVariable(value = "id") int articleId,
                                      @RequestBody Map<String, String> params) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            // 查询图书信息
            Article article = new Article();

            // 鉴权
            // 后面的得重写

            String bookName = params.get("bookName");
            String cover = params.get("cover");
            String title = params.get("title");
            String content = params.get("content");
            if (bookName == null || cover == null || title == null || content == null) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }
            if (bookName.isEmpty() || cover.isEmpty() || title.isEmpty() || content.isEmpty()) {
                return ApiResponse.fail(400, "必须参数不能为空");
            }

            String tagStr = params.get("tag");
            int tag = Integer.parseInt(tagStr);

            article.setBookName(bookName);
            article.setCover(cover);
            article.setTitle(title);
            article.setContent(content);
            article.setTag(tag);

//            articleServiceImpl.

            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "修改文章失败");
        }
    }


    @PostMapping("/publish")
    public ApiResponse<String> publish(@RequestAttribute(value = "id", required = true) int userid,
                                       @RequestBody Map<String, String> params) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            String idStr = params.get("id");
            int id = Integer.parseInt(idStr);
            String isPublishedStr = params.get("isPublished");
            int isPublished = Integer.parseInt(isPublishedStr);


//            articleServiceImpl.

            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "发表文章失败");
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> publish(@RequestAttribute(value = "id", required = true) int userid,
                                       @PathVariable(value = "id") int articleId,
                                       @RequestBody Map<String, String> params) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            // 删除
//            articleServiceImpl.
// 清缓存

            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "删除文章失败");
        }
    }
}
