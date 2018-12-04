package common.util;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookieUtil {


    public Cookie setCookie(String key, String value) throws UnsupportedEncodingException {

        // 为中文转码
        Cookie cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));

        cookie.setMaxAge(60*60*24);

        // 设置有效域为所有
        cookie.setPath("/");

        return cookie;
    }
}
