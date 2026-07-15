package kg.us.sakanatang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kg.us.sakanatang.domain.entity.Article;
import kg.us.sakanatang.domain.vo.ArticleVO;
import kg.us.sakanatang.domain.vo.RecommendVO;
import kg.us.sakanatang.service.ArticleService;
import kg.us.sakanatang.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sakana
* @description 针对表【article(分享书籍表)】的数据库操作Service实现
* @createDate 2025-04-12 16:28:26
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean createArticle(Article article) {
        return  articleMapper.insert(article)>0;
    }

    @Override
    public ArticleVO getArticleById(Integer id) {
        return articleMapper.getArticleVOById(id);
    }


    @Override
    public List<ArticleVO> getUserArticles(Integer userId,int page, int size) {
        int offset=(page-1)*size;
        return articleMapper.getUserArticleVOs(userId,offset,size);
    }

    @Override
    public boolean updateArticle(Article article) {
        System.out.println(article);
        return articleMapper.updateById(article) > 0;
    }

    @Override
    public boolean deleteArticle(Integer id) {
        return articleMapper.deleteById(id) > 0;
    }

    @Override
    public List<ArticleVO> getArticleList(int page, int size, int tagId, String title) {
        int offset=(page-1)*size;
        return articleMapper.getArticleVOList(tagId, title,offset,size);
    }

    @Override
    public boolean togglePublishStatus(Integer id, boolean isPublish) {
        Article article = new Article();
        article.setId(id);
        article.setIsPublished(isPublish ? 1 : 0);
        return articleMapper.updateById(article) > 0;
    }

    @Override
    public List<RecommendVO> recommend() {
        return articleMapper.recommend();
    }
}




