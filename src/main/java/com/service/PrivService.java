package com.service;

import com.dao.PrivDao;
import com.entity.Role;
import com.entity.SavePrivs;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivService {
    @Autowired
    PrivDao privDao;

    public List<Role> privManage_init(){
        List<Role> roles = privDao.privManage_init();
        return roles;
    }
    public List<List> privManage_loadPrivs(int roleId){
        List<Integer> menus = privDao.privManage_loadPrivsMenu(roleId);
        List<Integer> actions = privDao.privManage_loadPrivsAction(roleId);
        List<List> roles = new ArrayList<List>();
        roles.add(menus);
        roles.add(actions);
        return roles;
    }
    public void privManage_savePrivs(SavePrivs savePrivs){
//        删除原本的数据
        int ia = privDao.privManage_savePrivsDelMenu(savePrivs.getRoleId());
        int ib = privDao.privManage_savePrivsDelAction(savePrivs.getRoleId());
//        插入数据
        for (Integer i:savePrivs.getMenuIds()){
            savePrivs.setMenuId(i);
            int me = privDao.privManage_savePrivsInsertMenu(savePrivs);
        }
        for (Integer i:savePrivs.getActionIds()){
            savePrivs.setActionId(i);
            int action = privDao.privManage_savePrivsInsertAction(savePrivs);
        }
    }
}
