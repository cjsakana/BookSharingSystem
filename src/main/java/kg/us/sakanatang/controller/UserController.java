package kg.us.sakanatang.controller;

import kg.us.sakanatang.common.ApiResponse;
import kg.us.sakanatang.domain.entity.User;
import kg.us.sakanatang.domain.vo.UserVO;
import kg.us.sakanatang.service.UserService;
import kg.us.sakanatang.utils.CookieUtils;
import kg.us.sakanatang.utils.JWTUtil;
import kg.us.sakanatang.utils.RedisUtil;
import kg.us.sakanatang.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    // 用户注册
    @PostMapping("/register")
    public ApiResponse<UserVO> register(@RequestBody Map<String, String> params, HttpServletResponse response) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            String email = params.get("email");
            String password = params.get("password");
            if (email == null || password == null) {
                return ApiResponse.fail(400, "邮箱和密码不能为空");
            }
            if (email.isEmpty() || password.isEmpty()) {
                return ApiResponse.fail(400, "邮箱和密码不能为空");
            }


            boolean isEmail = Validator.isValidEmail(email);
            if (!isEmail) {
                return ApiResponse.fail(400, "邮箱不正确");
            }

            String code = params.get("code").trim();
            if (!redisUtil.checkCode(email, code)) {
                return ApiResponse.fail(400, "验证码不正确");
            }


            String name = params.get("name");
            if (name == null) {
                name = "微信用户";
            }

            String sexStr = params.get("sex");
            if (sexStr == null) {
                sexStr = "2";
            }
            int sex = Integer.parseInt(sexStr);


            User user = new User();
            user.setEmail(email.trim());
            user.setPassword(password.trim());
            user.setName(name.trim());
            user.setSex(sex);
            user.setRole(0);
            user.setSignature(params.get("signature"));


            UserVO userVO = userService.createUser(user);
            int id = userVO.getId();
            if (id <= 0) {
                return ApiResponse.fail(500, "用户注册失败");
            }

            // 注册成功删除验证码
            String codeKey = "user:verified:";
            redisUtil.del(codeKey + email);

            String token = JWTUtil.getToken(new ConcurrentHashMap<>() {{
                put("id", id);
                put("role", 0);
            }});
            CookieUtils.setCookie(response, "token", "Bearer+" + token);

            return ApiResponse.success(userVO);
        } catch (Exception e) {
            return ApiResponse.fail(500, "系统异常: " + e.getMessage());
        }
    }

    // 用户登录
    @PostMapping("/login")
    public ApiResponse<UserVO> login(@RequestBody Map<String, String> params, HttpServletResponse response) {
        try {
            if (params == null) {
                return ApiResponse.fail(400, "请求参数不能为空");
            }

            String email = params.get("email");
            String password = params.get("password");
            if (email == null || password == null) {
                return ApiResponse.fail(400, "邮箱和密码不能为空");
            }
            if (email.isEmpty() || password.isEmpty()) {
                return ApiResponse.fail(400, "邮箱和密码不能为空");
            }

            boolean isEmail = Validator.isValidEmail(email);
            if (!isEmail) {
                return ApiResponse.fail(400, "邮箱不正确");
            }


            UserVO userVO = userService.login(email, password);
            if (userVO == null) {
                return ApiResponse.fail(401, "邮箱或密码错误");
            }

            String token = JWTUtil.getToken(new ConcurrentHashMap<>() {{
                put("id", userVO.getId());
                put("role", 0);
            }});
            CookieUtils.setCookie(response, "token", "Bearer+" + token);

            return ApiResponse.success(userVO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ApiResponse.fail(500, "登录失败，请重试"+e.getMessage());
        }
    }

    // 获取用户信息
    @GetMapping("")
    public ApiResponse<UserVO> getUser(@RequestAttribute(value = "role", required = true) int role,
                                       @RequestAttribute(value = "id", required = true) int id) {
        try {
            if (id < 0) {
                return ApiResponse.fail(400, "非法用户");
            }

            UserVO userVO = userService.getUserById(id);
            if (userVO == null) {
                return ApiResponse.fail(404, "用户不存在");
            }


            return ApiResponse.success(userVO);
        } catch (Exception e) {
            return ApiResponse.fail(500, "获取用户信息失败");
        }
    }

    // 分页获取用户列表
    @GetMapping("/all")
    public ApiResponse<List<UserVO>> getUsers(
            @RequestAttribute(value = "role", required = true) int role,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        if (role != 1) {
            return ApiResponse.fail(401, "无权限");
        }
        try {
            List<UserVO> userVOList = userService.getAllUsers(page, size);

            return ApiResponse.success(userVOList);
        } catch (Exception e) {
            return ApiResponse.fail(500, "获取用户列表失败");
        }
    }

    // 更新用户信息
    @PutMapping("")
    public ApiResponse<UserVO> updateUser(
            @RequestAttribute(value = "id", required = true) int id,
            @RequestBody Map<String, String> params) {
        try {
            if (params == null || params.isEmpty()) {
                return ApiResponse.fail(400, "更新内容不能为空");
            }

            // 构造更新对象
            User user = new User();
            user.setId(id);

            if (params.containsKey("name")) {
                String name = params.get("name").trim();
                user.setName(name);
            }
            if (params.containsKey("password")) {
                String password = params.get("password").trim();
                user.setPassword(password);
            }
            if (params.containsKey("sex")) {
                int sex = Integer.parseInt(params.get("sex"));
                if (sex >= 0 && sex <= 2) {
                    user.setSex(sex);
                }
            }
            if (params.containsKey("signature")) {
                String signature = params.get("signature").trim();
                user.setSignature(signature);
            }


            boolean success = userService.updateUser(user);
            if (!success) {
                return ApiResponse.fail(500, "更新用户信息失败");
            }

            // 返回更新后的用户信息
            UserVO userVO = userService.getUserById(id);

            return ApiResponse.success(userVO);
        } catch (Exception e) {
            return ApiResponse.fail(500, "更新用户信息失败");
        }
    }

    // 修改密码
    @PutMapping("/ResetPassword")
    public ApiResponse<Void> updatePassword(
            @RequestAttribute(value = "id", required = true) int id,
            @RequestBody Map<String, String> params,
            HttpServletResponse response) {
        try {
            if (params == null || params.get("oldPassword") == null || params.get("newPassword") == null) {
                return ApiResponse.fail(400, "旧密码和新密码不能为空");
            }

            String oldPassword = params.get("oldPassword").trim();
            String newPassword = params.get("newPassword").trim();

            if (oldPassword.isEmpty() || newPassword.isEmpty()) {
                return ApiResponse.fail(400, "旧密码和新密码不能为空");
            }

            boolean success = userService.updatePassword(id, newPassword);
            if (!success) {
                return ApiResponse.fail(500, "修改密码失败");
            }

            // 修改密码了就应该清空cookie
            CookieUtils.clearCookie(response, "token");

            // TODO 表来记录无效Token，不做了

            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "修改密码失败");
        }
    }

    // 注销用户
    @DeleteMapping("")
    public ApiResponse<Void> deleteUser(@RequestAttribute(value = "id", required = true) int id, HttpServletResponse response) {
        try {
            boolean success = userService.deleteUser(id);
            if (!success) {
                return ApiResponse.fail(500, "注销用户失败");
            }

            // 就应该清空cookie
            CookieUtils.clearCookie(response, "token");
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.fail(500, "注销用户失败");
        }
    }
}
