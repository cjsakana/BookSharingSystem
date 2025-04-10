package kg.us.sakanatang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kg.us.sakanatang.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author sakana
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-04-07 22:51:51
*/
public interface UserService extends IService<User> {
    /**
     * 创建用户（检查邮箱是否已存在）
     */
    int createUser(User user);

    /**
     * 登录（校验邮箱和密码）
     */
    User login(String email, String password);

    /**
     * 查询用户（通过id，确保未删除）
     */
    User getUserById(int id);

    /**
     * 注销用户（软删除，设置 deletedAt）
     */
    boolean deleteUser(int id);

    /**
     * 分页获取所有用户（仅返回未删除用户）
     */
    Page<User> getAllUsers(int page, int size);

    /**
     * 修改用户信息（除密码外）
     */
    boolean updateUser(User user);

    /**
     * 修改密码
     */
    boolean updatePassword(int userId, String newPassword);
}
