package kg.us.sakanatang.controller;

import kg.us.sakanatang.common.ApiResponse;
import kg.us.sakanatang.utils.EmailUtil;
import kg.us.sakanatang.utils.RedisUtil;
import kg.us.sakanatang.utils.Util;
import kg.us.sakanatang.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private RedisUtil redisUtil;
    final static String codeKey = "user:verified:";

    @PostMapping("/sendVerifiedCode")
    public ApiResponse<String> sendCode(@RequestBody Map<String, String> params) {
        if (params == null) {
            return ApiResponse.fail(400, "邮箱为空");
        }
        String email = params.get("email");
        if (email.isEmpty()) {
            return ApiResponse.fail(400, "邮箱为空");
        }
        if (!Validator.isValidEmail(email.trim())) {

            return ApiResponse.fail(400, "非法邮箱");
        }

        String code = Util.RandomNumberGenerator();

        // 验证码有效期5分钟
        redisUtil.set(codeKey + email, code, 5, TimeUnit.MINUTES);

        EmailUtil.sendMail(email, "验证码", code);

        return ApiResponse.success();
    }
}
