package kg.us.sakanatang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    // 手机号正则表达式
    private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";
    // 邮箱正则表达式
//    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";


    public static boolean isValidPhone(String phone) {
        return isValid(PHONE_REGEX, phone);
    }

    public static boolean isValidEmail(String email) {
        return isValid(EMAIL_REGEX, email);
    }

    private static boolean isValid(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
