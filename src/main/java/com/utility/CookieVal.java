package com.utility;

import javax.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieVal {
    public String getCookieValues(Cookie[] cookies,String user) {
        String str = null;
        if (cookies == null){
            return null;
        }
        for (int i = 0;i<cookies.length;i++){
            Cookie cookie = cookies[i];
            if (cookie.getName().equalsIgnoreCase(user)){
                str =cookie.getValue();
                return str;
            }
        }
        return null;
    }
}
