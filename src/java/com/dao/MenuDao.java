package com.dao;

import com.entity.Menu;
import com.entity.SysAction;

import java.util.List;

public interface MenuDao {
//    查询自己的属性
    Menu myProperty(Menu menu);
//    查询下一级的数据
    List<Menu> nextProperty(Menu menu);
//    查询父类数据
    Menu parentMenu(int menuId);
// normalActionList普通动作
    List<SysAction> normalActionList(Menu menu);
// authorActionList授权动作
    List<SysAction> authorActionList(Menu menu);
//    listurl
    List<String> ListUrl(String account);
//<!--    插入menu的数据-->
    int insertMenu(Menu menu);
//    <!--    插入sysAction-->
    int insertAction(SysAction sysAction);
//<!--    插入menu的action-->
    int updateMenu(Menu menu);
//    删除操作
    int menuManage_delMenu(Menu menu);
//  编辑修改  menuManage_editMen
    int menuManage_editMenu(Menu menu);
    int menuManage_editMenuAction(SysAction sysAction);
//    查看动作
    SysAction menuManage_toEditAction(Menu menu);
//    修改动作
    int menuManage_editAction(SysAction action);
//    添加动作
    int addAction(SysAction action);
//    删除动作
    int menuManage_delAction(int actionId);
}
