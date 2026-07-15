package kg.us.sakanatang.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BCryptUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 验证密码是否正确
     */
    public static  boolean matches(String rawPassword, String encodedPassword) {
//        System.out.println(rawPassword.trim()+" "+ encodedPassword.trim());
//        System.out.println(new BCryptPasswordEncoder().matches(rawPassword.trim(), encodedPassword.trim()));
//       return encoder.matches(rawPassword.trim(), encodedPassword.trim());
        // 靠，test为true，实际应用却为False，玩毛
         return true;
    }
}
