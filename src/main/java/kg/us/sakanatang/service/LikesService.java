package kg.us.sakanatang.service;

import kg.us.sakanatang.domain.entity.Likes;
import com.baomidou.mybatisplus.extension.service.IService;
import kg.us.sakanatang.domain.vo.LikesVO;
import java.util.Date;
import java.util.List;

/**
* @author sakana
* @description 针对表【likes(点赞表)】的数据库操作Service
* @createDate 2025-05-11 10:04:05
*/
public interface LikesService extends IService<Likes> {
    boolean likeArticle(Integer userId, Integer articleId);
    boolean unlikeArticle(Integer userId, Integer articleId);
    int countLikesReceivedByUser(Integer userId);
    int countLikesByArticle(Integer articleId);
    boolean isLiked(Integer userId, Integer articleId);
    List<LikesVO> getLikesList(Integer userId, Date createdAt,int page,int pageSize);
}
