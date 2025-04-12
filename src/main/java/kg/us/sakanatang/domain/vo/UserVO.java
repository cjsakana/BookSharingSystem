package kg.us.sakanatang.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVO implements Serializable {
    private Integer id;
    private Date createdAt;
    private String avatar;
    private String name;
    private String email;
    private Integer role;
    private Integer sex;
    private String signature;

    private static final long serialVersionUID = 1L;
}
