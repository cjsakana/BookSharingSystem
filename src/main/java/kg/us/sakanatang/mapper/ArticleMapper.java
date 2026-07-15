package kg.us.sakanatang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kg.us.sakanatang.domain.entity.Article;
import kg.us.sakanatang.domain.vo.ArticleVO;
import kg.us.sakanatang.domain.vo.RecommendVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author sakana
 * @description 针对表【article(分享书籍表)】的数据库操作Mapper
 * @createDate 2025-04-12 16:28:26
 * @Entity kg.us.sakanatang.domain.entity.Article
 */
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT a.*, u.name as username, u.avatar as userAvatar, t.name as tagName " +
            "FROM article a " +
            "LEFT JOIN user u ON a.userId = u.id " +
            "LEFT JOIN tag t ON a.tag = t.id " +
            "WHERE a.id = #{id}")
    ArticleVO getArticleVOById(@Param("id")Integer id);

    @Select("SELECT a.*, u.name as username, u.avatar as userAvatar, t.name as tagName " +
            "FROM article a " +
            "LEFT JOIN user u ON a.userId = u.id " +
            "LEFT JOIN tag t ON a.tag = t.id " +
            "WHERE a.userId = #{userId} " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<ArticleVO> getUserArticleVOs(@Param("userId")  Integer userId,
                                      @Param("offset") Integer offset,
                                      @Param("pageSize") Integer pageSize );

    @Select("<script>" +
            "SELECT a.*, u.name as username, u.avatar as userAvatar, t.name as tagName " +
            "FROM article a " +
            "LEFT JOIN user u ON a.userId = u.id " +
            "LEFT JOIN tag t ON a.tag = t.id " +
            "<where>" +
            "a.isPublished = 1 "+
            "<if test='tagId != null and tagId != 0'> AND a.tag = #{tagId} </if>" +
            "<if test='title != null and title != \"\"'> AND a.title LIKE CONCAT('%', #{title}, '%') </if>" +
            "</where>" +
            "ORDER BY a.updatedAt DESC " +
            "LIMIT #{pageSize} OFFSET #{offset}" +
            "</script>")
    List<ArticleVO> getArticleVOList(@Param("tagId") Integer tagId,
                                     @Param("title") String title,
                                     @Param("offset") Integer offset,
                                     @Param("pageSize") Integer pageSize
    );


    @Update("UPDATE article SET likes = likes + #{delta} WHERE id = #{id}")
    int changeArticleLikes(@Param("id") Integer id, @Param("delta") Integer delta);

    
    @Select("SELECT id, title FROM article WHERE isPublished = 1 ORDER BY RAND() LIMIT 5")
    List<RecommendVO> recommend();
}




