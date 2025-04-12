package kg.us.sakanatang.service;

import kg.us.sakanatang.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import kg.us.sakanatang.domain.vo.UserVO;

import java.util.List;

/**
* @author sakana
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-04-12 11:21:41
*/
public interface UserService extends IService<User> {
    UserVO createUser(User user);
    UserVO login(String email, String password);
    UserVO  getUserById(int id);
    boolean deleteUser(int id);
    List<UserVO> getAllUsers(int page, int size);
     boolean updateUser(User user);
     boolean updatePassword(int userId, String newPassword);
}
