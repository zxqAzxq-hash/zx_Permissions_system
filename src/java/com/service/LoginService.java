package com.service;

import com.dao.LoginDao;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginDao login;

    public String testJDBCLinker(User user){
        int login2Login = login.isLogin(user);
        if (login2Login == 1){
            return "success";
        }else if (login2Login == 0){
            return "sys error";
        }else {
            return "db error";
        }
    }
}
