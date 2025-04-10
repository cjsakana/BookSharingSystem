package kg.us.sakanatang.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    // 1段用于JWT加密的密钥
    private static final String SIGN = "TrmP4hdA5EcZ8T3YN2HhWPSOIQUdm-tPNf1BA3GRcxo=";

    public static final int Duration = 7;

    // 生成TOKEN
    public static String getToken(Map<String, Object> map) {
        Calendar instance = Calendar.getInstance();
        // 默认7天过期
        instance.add(Calendar.DATE, Duration);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        // payload
        map.forEach((key, value) -> {
            if (value instanceof String) {
                builder.withClaim(key, (String) value);
            } else if (value instanceof Integer) {
                builder.withClaim(key, (Integer) value);
            } else if (value instanceof Long) {
                builder.withClaim(key, (Long) value);
            } else if (value instanceof Boolean) {
                builder.withClaim(key, (Boolean) value);
            } else if (value instanceof Date) {
                builder.withClaim(key, (Date) value);
            }
        });
        return builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));
    }

    // 从Token中获取信息
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}