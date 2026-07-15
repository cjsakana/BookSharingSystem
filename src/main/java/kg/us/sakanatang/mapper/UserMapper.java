package kg.us.sakanatang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kg.us.sakanatang.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author sakana
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2025-04-12 11:21:41
 * @Entity kg.us.sakanatang.domain.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("<script>" +
            "UPDATE user" +
            "<set>" +
            "<if test='name != null'>name = #{name},</if>"+
            "<if test='password != null'>password = #{password},</if>"+
            "<if test='sex != null'>sex = #{sex},</if>"+
            "<if test='signature != null'>signature = #{signature},</if>"+
            "</set>"+
            "WHERE id = #{id}"+
            "</script>")
    int updateUser(@Param("id") int id,
                   @Param("name") String name,
                   @Param("password") String password,
                   @Param("sex") Integer sex,
                   @Param("signature") String signature);
}




