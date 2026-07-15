package kg.us.sakanatang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kg.us.sakanatang.domain.entity.Likes;
import kg.us.sakanatang.domain.vo.ArticleVO;
import kg.us.sakanatang.mapper.ArticleMapper;
import kg.us.sakanatang.service.LikesService;
import kg.us.sakanatang.mapper.LikesMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kg.us.sakanatang.domain.vo.LikesVO;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes>
    implements LikesService{

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean likeArticle(Integer userId, Integer articleId) {
        // 防止重复点赞
        if (likesMapper.isLiked(userId, articleId) > 0) return false;
        Likes like = new Likes();
        like.setUserId(userId);
        like.setArticleId(articleId);

        articleMapper.changeArticleLikes(articleId,1);
        return this.save(like);
    }

    @Override
    public boolean unlikeArticle(Integer userId, Integer articleId) {
        QueryWrapper<Likes> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId).eq("articleId", articleId).isNull("deletedAt");
        Likes like = this.getOne(wrapper);
        if (like == null) return false;
        like.setDeletedAt(new Date());
        articleMapper.changeArticleLikes(articleId,-1);
        return this.updateById(like);
    }

    @Override
    public int countLikesReceivedByUser(Integer userId) {
        return likesMapper.countLikesReceivedByUser(userId);
    }

    @Override
    public int countLikesByArticle(Integer articleId) {
        return likesMapper.countLikesByArticle(articleId);
    }

    @Override
    public boolean isLiked(Integer userId, Integer articleId) {
        return likesMapper.isLiked(userId, articleId) > 0;
    }

    @Override
    public List<LikesVO> getLikesList(Integer userId, Date createdAt,int page,int pageSize) {
        int offset = (page-1)*pageSize;
        return likesMapper.selectLikesWithArticle(userId,createdAt,offset,pageSize);
    }
}




