package com.service;


import com.dao.HomeDao;
import com.entity.MandR;
import com.entity.Menu;
import com.entity.User;
import com.utility.CookieVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    CookieVal cookieVal;
    @Autowired
    User user;
    @Autowired
    HomeDao homeDao;

    public User home(Cookie[] cookies){
        String cookieValues = cookieVal.getCookieValues(cookies, "user");
        user.setAccount(cookieValues);
        return user;
    }
    public List<Menu> loadMenu(Cookie[] cookies){
        /* 查询type为1的数据，查询menu表和sysaction表中
                * 存放在list<menu>中
         * type2存放在menu的list<menu>中
         * */
        MandR mandR = new MandR();
        String cookieValues = cookieVal.getCookieValues(cookies, "user");
        int i = homeDao.roleId(cookieValues);
        mandR.setRoleId(i);
        mandR.setParentId(0);


        List<Menu> faMenus = homeDao.homeAll(mandR);
        for (Menu faM:faMenus){
            mandR.setParentId(faM.getMenuId());
            List<Menu> sonMenus = homeDao.homeAll(mandR);
            faM.setSubMenuList(sonMenus);
        }

        return faMenus;
    }
}
