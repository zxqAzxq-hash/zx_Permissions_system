package com.service;

import com.dao.HomeDao;
import com.dao.MenuDao;
import com.entity.Menu;
import com.entity.MenuType;
import com.entity.SysAction;
import com.utility.CookieVal;
import com.utility.GetType;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.VariableElement;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    HomeService homeService;
    @Autowired
    HomeDao homeDao;
    @Autowired
    MenuDao menuDao;


    public Menu menuManage_menuTree(){
        List<Menu> faMenus = homeDao.twoLoadMenu(0);
        for (Menu faM:faMenus){
            List<Menu> sonMenus = homeDao.twoLoadMenu(faM.getMenuId());
            faM.setSubMenuList(sonMenus);
        }
        Menu loadMenu = homeDao.heardLoadMenu(0);
        loadMenu.setSubMenuList(faMenus);
        return loadMenu;
    }

    public Menu myProperty(Menu m){
        Menu menu = menuDao.myProperty(m);
        return menu;
    }
    public List<Menu> nextProperty(Menu m){
        List<Menu> menus = menuDao.nextProperty(m);
        return menus;
    }
    public Menu parentMenu(int menuId){
        if (menuId == 999)menuId = 0;
        Menu menu = menuDao.parentMenu(menuId);
        return menu;
    }

    public List<SysAction> normalActionList(Menu menu){
        List<SysAction> sysActions = menuDao.normalActionList(menu);
        return sysActions;
    }
    public List<SysAction> authorActionList(Menu menu){
        List<SysAction> sysActions = menuDao.authorActionList(menu);
        return sysActions;
    }
    @Autowired
    CookieVal cookieVal;
    public List<String>  ListUrl(Cookie[] cookies){
//        获取当前用户的cookie
        String cookieValues = cookieVal.getCookieValues(cookies, "user");
        List<String> list = menuDao.ListUrl(cookieValues);
        return list;
    }
    @Autowired
    GetType getType;

    public List<MenuType> toAddSubMenu(){
        List<MenuType> type = getType.getType();
        return type;
    }

    public boolean  addMenu(Menu menu){
        int menuId = menuDao.insertMenu(menu);
        SysAction sysAction = new SysAction();
        sysAction.setActionName(menu.getActionName());
//        获取到的自增主键
        sysAction.setMenuId(menu.getMenuId());
        sysAction.setRemark(menu.getRemark());
        sysAction.setType(menu.getType());
        int actionId = menuDao.insertAction(sysAction);
        Menu m = new Menu();
        m.setMenuId(menu.getMenuId());
        m.setActionId(sysAction.getActionId());
        int i = menuDao.updateMenu(m);
        return true;
    }
    public boolean menuManage_delMenu(Menu menu){
        int i = menuDao.menuManage_delMenu(menu);
        return true;
    }
    public boolean menuManage_editMenu(Menu menu){
        SysAction sysAction = new SysAction();
        sysAction.setActionId(menu.getSysAction().getActionId());
        sysAction.setRemark(menu.getRemark());
        sysAction.setActionName(menu.getActionName());
        int i = menuDao.menuManage_editMenu(menu);
        int action = menuDao.menuManage_editMenuAction(sysAction);
        return true;
    }
    public SysAction menuManage_toEditAction(Menu menu){
        SysAction sysAction = menuDao.menuManage_toEditAction(menu);
        return sysAction;
    }
    public boolean menuManage_editAction(SysAction action){
        int i = menuDao.menuManage_editAction(action);
        return true;
    }
    public boolean addAction(SysAction sysAction){
        menuDao.addAction(sysAction);
        return true;
    }
    public void menuManage_delAction(int actionId){
        int action = menuDao.menuManage_delAction(actionId);
    }
}
