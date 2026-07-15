package kg.us.sakanatang.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LikesVO {
    private int id;
    private Date createdAt;
    private int userId;
    private String username;
    private String avatar;
    private int articleId;
    private String title;
    private String cover;
}
