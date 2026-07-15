package kg.us.sakanatang.mapper;

import kg.us.sakanatang.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author sakana
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-04-12 11:21:41
* @Entity kg.us.sakanatang.domain.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    int updateUser(int id,String name,String password,Integer sex,String signature);
}




