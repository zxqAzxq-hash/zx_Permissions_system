package com.controller;

import com.entity.User;
import com.service.LoginService;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "login")
public class Login {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "login")
    public String login(){
        System.out.println("ceshi1");
        return "user_login";
    }

    @RequestMapping(value = "userLogin")
    @ResponseBody
    public String isLogin(User user, HttpServletResponse response){
        String isLogin = loginService.testJDBCLinker(user);
        if ("success".equals(isLogin)){
            Cookie cookie = new Cookie("user", user.getAccount());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return isLogin;
    }

}
