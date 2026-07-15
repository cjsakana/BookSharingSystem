package kg.us.sakanatang.mapper;

import kg.us.sakanatang.domain.entity.Likes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author sakana
* @description 针对表【likes(点赞表)】的数据库操作Mapper
* @createDate 2025-05-11 10:04:05
* @Entity kg.us.sakanatang.domain.entity.Likes
*/
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kg.us.sakanatang.domain.vo.LikesVO;
import java.util.Date;
import java.util.List;

public interface LikesMapper extends BaseMapper<Likes> {
    // 统计某用户的所有文章被点赞的总数
    @Select("SELECT COUNT(*) FROM likes l JOIN article a ON l.articleId = a.id WHERE a.userId = #{userId} AND l.deletedAt IS NULL")
    int countLikesReceivedByUser(@Param("userId") Integer userId);

    // 统计某篇文章被点赞的总数
    @Select("SELECT COUNT(*) FROM likes WHERE articleId = #{articleId} AND deletedAt IS NULL")
    int countLikesByArticle(@Param("articleId") Integer articleId);

    // 查询某用户是否已点赞某文章
    @Select("SELECT COUNT(*) FROM likes WHERE userId = #{userId} AND articleId = #{articleId} AND deletedAt IS NULL")
    int isLiked(@Param("userId") Integer userId, @Param("articleId") Integer articleId);



    @Select("<script>" +
            "SELECT l.id, l.createdAt, l.userId, u.name as username, u.avatar, l.articleId, a.title, a.cover " +
            "FROM likes l " +
            "LEFT JOIN article a ON l.articleId = a.id " +
            "LEFT JOIN user u ON l.userId = u.id " +
            "WHERE l.userId = #{userId} AND l.deletedAt IS NULL " +
            "<if test='createdAt != null'>AND l.createdAt &gt; #{createdAt} </if>" +
            "ORDER BY l.createdAt DESC " +
            "LIMIT #{pageSize} OFFSET #{offset} " +
            "</script>")
    List<LikesVO> selectLikesWithArticle(
        @Param("userId") Integer userId,
        @Param("createdAt") Date createdAt,
        @Param("offset") Integer offset,
        @Param("pageSize") Integer pageSize
    );
}




