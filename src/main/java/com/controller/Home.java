package com.controller;

import com.entity.Menu;
import com.entity.User;
import com.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("home")
public class Home {

    @Autowired
    HomeService service;

    @RequestMapping("home")
    public ModelAndView home(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        User home = service.home(cookies);
        ModelAndView view = new ModelAndView("home");
        view.addObject(home);
        return view;
    }
    @RequestMapping("welcome")
    @ResponseBody
    public String  welcome(){
        return "welcome";
    }



//项目初始也导航栏页面
    @RequestMapping("loadMenu")
    @ResponseBody
    public List<Menu>  loadMenu(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        return service.loadMenu(cookies);
    }

    @RequestMapping("dele")
    public String logout(){
        return "forward:/login/login";
    }

}
