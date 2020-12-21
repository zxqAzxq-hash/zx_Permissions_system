package com.dao;

import com.entity.Role;
import com.entity.SavePrivs;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public interface PrivDao {
//    查询所有role表数据
    List<Role> privManage_init();
    List<Integer> privManage_loadPrivsMenu(int roleId);
    List<Integer> privManage_loadPrivsAction(int roleId);
//    保存
    //1.删除
    int privManage_savePrivsDelMenu(int roleId);
    int privManage_savePrivsDelAction(int roleId);
    //2.插入
    int privManage_savePrivsInsertMenu(SavePrivs savePrivs);
    int privManage_savePrivsInsertAction(SavePrivs savePrivs);
}
