package kg.us.sakanatang.mapper;

import kg.us.sakanatang.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author sakana
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-04-07 22:51:51
* @Entity kg.us.sakanatang.domain.entity.User
*/


public interface UserMapper extends BaseMapper<User> {
    int updateUser(int id,String name,String password,Integer sex,String signature);
}




