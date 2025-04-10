package kg.us.sakanatang.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private Integer id;
    private Date createdAt;
    private String name;
    private String email;
    private Integer role;
    private Integer sex;
    private String signature;
}
