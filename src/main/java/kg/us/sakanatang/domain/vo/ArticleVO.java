package kg.us.sakanatang.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleVO {
    private Integer id;
    private Date updatedAt;
    private String bookName;
    private String isbn;
    private String cover;
    private String title;
    private String content;
    private Integer tag;
    private String tagName;
    private Integer userId;
    private String userAvatar;
    private String username;
    private Integer likes;
    private Integer isPublished;
}
