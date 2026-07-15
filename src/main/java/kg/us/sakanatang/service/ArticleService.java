package kg.us.sakanatang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kg.us.sakanatang.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import kg.us.sakanatang.domain.vo.ArticleVO;
import kg.us.sakanatang.domain.vo.RecommendVO;

import java.util.List;

/**
* @author sakana
* @description 针对表【article(分享书籍表)】的数据库操作Service
* @createDate 2025-04-12 16:28:26
*/
public interface ArticleService extends IService<Article> {

    // 创建文章
    boolean createArticle(Article article);

    // 获取文章详情
    ArticleVO getArticleById(Integer id);

    // 更新文章
    boolean updateArticle(Article article);

    // 删除文章
    boolean deleteArticle(Integer id);

    // 获取文章列表（分页）
    List<ArticleVO> getArticleList(int page, int size,int tagId,String title);

    // 根据标签获取文章
//    List<ArticleVO> getArticlesByTag(Integer tagId);

    // 获取用户的文章列表
    List<ArticleVO> getUserArticles(Integer userId,int page, int size);

    // 发布/取消发布文章
    boolean togglePublishStatus(Integer id, boolean isPublish);

    List<RecommendVO> recommend();
}
