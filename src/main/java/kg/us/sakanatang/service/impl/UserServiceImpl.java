package kg.us.sakanatang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kg.us.sakanatang.domain.entity.User;
import kg.us.sakanatang.domain.vo.UserVO;
import kg.us.sakanatang.mapper.UserMapper;
import kg.us.sakanatang.service.UserService;
import kg.us.sakanatang.utils.BCryptUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
//    @CacheEvict(allEntries = true) // 创建用户后清空所有用户相关缓存
    @CacheEvict(cacheNames = "users", allEntries = true) // 创建用户后清空所有缓存
    public UserVO createUser(User user) {
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

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    @Caching(
            cacheable = {
//                    @Cacheable(key = "'email:' + #email", unless = "#result == null"),
//                    @Cacheable(value = "nullValueCache", key = "'email:' + #email", condition = "#result == null")
                    @Cacheable(cacheNames = "users", key = "'email:' + #email", unless = "#result == null"), // 正常缓存
                    @Cacheable(cacheNames = "nullValueCache", key = "'email:' + #email", condition = "#result == null") // 空值缓存（1分钟过期）

            }
    )
    public UserVO login(String email, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email)
                .isNull("deletedAt");
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new RuntimeException("用户不存在或已被注销");
        }

        if (!BCryptUtil.matches(password, user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(user, userVO);
        System.out.println(userVO);
        return userVO;
    }

    @Override
    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "users", key = "'id:' + #id", unless = "#result == null"), // 正常缓存

//                    @Cacheable(key = "'id:' + #id", unless = "#result == null"),
                    @Cacheable(value = "nullValueCache", key = "'id:' + #id", condition = "#result == null")
            }
    )
    public UserVO  getUserById(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deletedAt");
        User user= userMapper.selectOne(queryWrapper);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "users", key = "'id:' + #id"), // 清除用户缓存
            @CacheEvict(cacheNames = "users", key = "'email:*'"),   // 清除所有邮箱缓存（模糊匹配）
            @CacheEvict(cacheNames = "users", key = "'list:*'")     // 清除所有列表缓存

//            @CacheEvict(key = "'id:' + #id"),
//            @CacheEvict(key = "'email:*'"),
//            @CacheEvict(key = "'list:*'")
    })
    public boolean deleteUser(int id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
//    @Cacheable(key = "'list:' + #page + ':' + #size") // 分页缓存用户列表
    @Cacheable(cacheNames = "users", key = "'list:' + #page + ':' + #size") // 分页缓存
    public List<UserVO> getAllUsers(int page, int size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deletedAt");
        Page<User> users = userMapper.selectPage(new Page<>(page, size), queryWrapper);

        return users.getRecords().stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
    }

    @Override
    @Caching(
//            put = @CachePut(key = "'id:' + #user.id"), // 更新用户缓存
//            evict = {
//                    @CacheEvict(key = "'email:' + #user.email"), // 清除邮箱缓存
//                    @CacheEvict(key = "'list:*'")                // 清除所有列表缓存
//            }
            put = @CachePut(cacheNames = "users", key = "'id:' + #user.id"), // 更新用户缓存
            evict = {
                    @CacheEvict(cacheNames = "users", key = "'email:' + #user.email"), // 清除邮箱缓存
                    @CacheEvict(cacheNames = "users", key = "'list:*'")                // 清除所有列表缓存
            }
    )
    public boolean updateUser(User user) {
        UserVO existingUser = getUserById(user.getId());
        if (existingUser == null) {
            throw new RuntimeException("用户不存在或已被注销");
        }

        return userMapper.updateUser(user.getId(), user.getName(), user.getPassword(), user.getSex(), user.getSignature()) > 0;
    }


    @Override
//    @Caching(
//            put = @CachePut(key = "'id:' + #user.id"), // 更新用户缓存
//            evict = {
//                    @CacheEvict(key = "'email:' + #user.email"), // 清除邮箱缓存
//                    @CacheEvict(key = "'list:*'")                // 清除所有列表缓存
//            }
//    )
    @Caching(
            put = @CachePut(cacheNames = "users", key = "'id:' + #userId"), // 更新用户缓存
            evict = {
                    @CacheEvict(cacheNames = "users", key = "'email:*'"),       // 清除所有邮箱缓存
                    @CacheEvict(cacheNames = "users", key = "'list:*'")         // 清除所有列表缓存
            }
    )
    public boolean updatePassword(int userId, String newPassword) {
        return userMapper.updateUser(userId, "", newPassword, null, "") > 0;

    }
}




