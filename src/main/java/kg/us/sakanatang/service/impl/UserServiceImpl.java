package kg.us.sakanatang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kg.us.sakanatang.domain.entity.User;
import kg.us.sakanatang.mapper.UserMapper;
import kg.us.sakanatang.service.UserService;
import kg.us.sakanatang.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sakana
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2025-04-07 22:51:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int createUser(User user) {
        // 检查邮箱是否已存在（且未删除）
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", user.getEmail())
                .isNull("deletedAt");
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new RuntimeException("邮箱已被注册");
        }
        user.setPassword(BCryptUtil.encode(user.getPassword()));
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public User login(String email, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email)
                .isNull("deletedAt");
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new RuntimeException("用户不存在或已被注销");
        }

        if(BCryptUtil.matches(password,user.getPassword())){
            throw new RuntimeException("密码错误");
        }

        return user;
    }

    @Override
    public User getUserById(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deletedAt");
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean deleteUser(int id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public Page<User> getAllUsers(int page, int size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deletedAt");
        return userMapper.selectPage(new Page<>(page, size), queryWrapper);
    }

    @Override
    public boolean updateUser(User user) {
        // 确保用户存在且未删除
        User existingUser = getUserById(user.getId());
        if (existingUser == null) {
            throw new RuntimeException("用户不存在或已被注销");
        }

        return userMapper.updateUser(user.getId(),user.getName(),user.getPassword(),user.getSex(),user.getSignature()) > 0;
    }


    @Override
    public boolean updatePassword(int userId, String newPassword){
        return userMapper.updateUser(userId,"",newPassword,null,"") > 0;

    }
}




