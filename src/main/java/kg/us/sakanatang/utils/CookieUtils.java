package kg.us.sakanatang.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    /**
     * 设置 Cookie（支持跨域配置）
     *
     * @param response HttpServletResponse
     * @param name     Cookie 名称
     * @param value    Cookie 值
     * @param maxAge   有效期（秒）
     * @param domain   可访问的域名（如 ".example.com"），null 表示当前域名
     * @param sameSite SameSite 策略（"None"、"Lax"、"Strict"）
     * @param secure   是否仅 HTTPS 传输
     */
    public static void setCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge,
            String domain,
            String sameSite,
            boolean secure
    ) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(secure); // SameSite=None 时必须为 true

        // 设置 Domain（跨子域时使用）
        if (domain != null) {
            cookie.setDomain(domain);
        }

        // 设置 SameSite（兼容新旧浏览器）
        String cookieHeader = String.format(
                "%s=%s; Path=%s; HttpOnly; SameSite=%s; %s",
                cookie.getName(),
                cookie.getValue(),
                cookie.getPath(),
                sameSite,
                secure ? "Secure" : ""
        );
        response.addHeader("Set-Cookie", cookieHeader.trim());

        // 传统方式（兼容旧版 Servlet）
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 简化版（默认 SameSite=Lax，不跨域）
     */
    public static void setCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge
    ) {
        setCookie(response, name, value, maxAge, null, "Lax", false);
    }

    /**
     * 简化版（默认有效期 7 天）
     */
    public static void setCookie(
            HttpServletResponse response,
            String name,
            String value
    ) {
        setCookie(response, name, value, 7 * 24 * 60 * 60, null, "Lax", false);
    }

    /**
     * 跨域专用（SameSite=None + Secure）
     */
    public static void setCrossDomainCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge,
            String domain
    ) {
        setCookie(response, name, value, maxAge, domain, "None", true);
    }

    /**
     * 清空普通Cookie（SameSite=Lax）
     *
     * @param response HttpServletResponse
     * @param name     Cookie名称
     */
    public static void clearCookie(HttpServletResponse response, String name) {
        setCookie(response, name, "", 0, null, "Lax", false);
    }

    /**
     * 清空跨域Cookie（SameSite=None + Secure）
     *
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param domain   需要清空的域名（如".example.com"）
     */
    public static void clearCrossDomainCookie(HttpServletResponse response, String name, String domain) {
        setCookie(response, name, "", 0, domain, "None", true);
    }

    /**
     * 清空Cookie（完全控制参数）
     *
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param domain   域名（null表示当前域名）
     * @param sameSite SameSite策略
     * @param secure   是否仅HTTPS
     */
    public static void clearCookie(
            HttpServletResponse response,
            String name,
            String domain,
            String sameSite,
            boolean secure
    ) {
        setCookie(response, name, "", 0, domain, sameSite, secure);
    }
}