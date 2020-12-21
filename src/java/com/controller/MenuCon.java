package com.controller;
import com.entity.Menu;
import com.entity.MenuType;
import com.entity.SysAction;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuCon {

    @Autowired
    MenuService menuService;

//    进入菜单管理页面
    @RequestMapping("menuManage_init")
    public String menuManage_init(){
        return "dev/menu_manage";
    }

    @RequestMapping("menuManage_menuTree")
    @ResponseBody
    public Menu menuManage_menuTree(long ts){
        return menuService.menuManage_menuTree();
    }
//    menu_detail?menuId=0&type=1
    /* ListUrl==所有的权限路径-
     * menu--subMenuList==--normalActionList---authorActionList
     * menu.type menu.sysAction.actionName menu.remark menuId menuName parentId
    * */
    @RequestMapping("menu_detail")
    public ModelAndView menu_detail(com.entity.Menu m,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("dev/menu_detail");
        /*查询自己的属性*/
        Menu menu = menuService.myProperty(m);
//        查询下级菜单
        List<Menu> subMenuList = menuService.nextProperty(m);
//        根据menu的parentId查询父类的对象
        int parentId = menu.getParentId();
        Menu parentMenu = menuService.parentMenu(parentId);
//        普通动作
        List<SysAction> normalActionList = menuService.normalActionList(m);
//        授权动作
        List<SysAction> authorActionList = menuService.authorActionList(m);
//        获取ListUrl
        //获取cookie，找到对应的角色，通过角色去寻找对应的menuId，
        Cookie[] cookies = request.getCookies();
        List<String> stringList = menuService.ListUrl(cookies);
        modelAndView.addObject("ListUrl",stringList);
        modelAndView.addObject("normalActionList",normalActionList);
        modelAndView.addObject("authorActionList",authorActionList);
        modelAndView.addObject("parentMenu",parentMenu);
        modelAndView.addObject("subMenuList",subMenuList);
        modelAndView.addObject("menu",menu);


/*        request.setAttribute("menu",menu);
        request.setAttribute("subMenuList",subMenuList);*/
        return modelAndView;
    }
//    子菜单按钮 toAddSubMenu?menuId=0&type=1
    @RequestMapping("toAddSubMenu")
    public ModelAndView toAddSubMenu(Menu m){
        ModelAndView modelAndView = new ModelAndView("dev/menu_add");
        /*查询自己的属性*/
        Menu menu = menuService.myProperty(m);
        List<MenuType> menuTypes = menuService.toAddSubMenu();
        modelAndView.addObject("menu",menu);
        modelAndView.addObject("menuTypeList",menuTypes);
        return modelAndView;
    }
//    保存添加子菜单
    @RequestMapping("addMenu")
    @ResponseBody
    public void addMenu(Menu m){
        boolean b = menuService.addMenu(m);
        System.out.println(b);

    }
//    删除列?menuId=62&parentId=0&type=1
    @RequestMapping("menuManage_delMenu")
    public String menuManage_delMenu(Menu menu){
        boolean b = menuService.menuManage_delMenu(menu);
        return "include/taglib";
    }
//    修改当前菜单属性：menuManage_toEditMenu.do?menuId=1&parentId=0&actionId=1
    @RequestMapping("menuManage_toEditMenu")
    public ModelAndView menuManage_toEditMenu(Menu m){
        ModelAndView modelAndView = new ModelAndView("dev/menu_edit");
        /*查询自己的属性*/
        Menu menu = menuService.myProperty(m);
        Menu parentMenu = menuService.parentMenu(m.getParentId());
        List<MenuType> menuTypeList = menuService.toAddSubMenu();
        modelAndView.addObject("menu",menu);
        modelAndView.addObject("parentMenu",parentMenu);
        modelAndView.addObject("menuTypeList",menuTypeList);
        return modelAndView;
    }
//    提交修改menuManage_editMenu
    @RequestMapping("menuManage_editMenu")
    public String menuManage_editMenu(Menu menu){
        boolean b = menuService.menuManage_editMenu(menu);
        return "include/taglib";
    }
//    编辑动作menuManage_toEditAction.do?actionId=76&menuId=0
    @RequestMapping("menuManage_toEditAction")
    public ModelAndView menuManage_toEditAction(Menu m){
        ModelAndView modelAndView = new ModelAndView("dev/action_edit");
        Menu menu = menuService.myProperty(m);
        SysAction sysAction = menuService.menuManage_toEditAction(m);
        modelAndView.addObject("menu",menu);
        modelAndView.addObject("action",sysAction);
        return modelAndView;
    }
        //actionName: actionName
        //actionId: 76
        //menuId: 0
        //type: 1
        //remark: 一级菜单
//    提交修改动作
    @RequestMapping("menuManage_editAction")
    public String menuManage_editAction(SysAction s){
        boolean b = menuService.menuManage_editAction(s);
        return "include/taglib";
    }
//    toAddAction?menuId=56
    @RequestMapping("toAddAction")
    public ModelAndView toAddAction(Menu m){
        ModelAndView modelAndView = new ModelAndView("dev/action_add");
        Menu menu = menuService.myProperty(m);
        modelAndView.addObject("menu",menu);
        return modelAndView;
    }
//    menuId: 5
//    actionName: 2222
//    type: 1
//    remark: 2222
//    添加修改
    @RequestMapping("addAction")
    public String addAction(SysAction sysAction){
        menuService.addAction(sysAction);
        return "include/taglib";
    }
//    menuManage_delAction.do?actionId=90&menuId=56
//    删除
    @RequestMapping("menuManage_delAction")
    public String menuManage_delAction(int actionId,int menuId){
        menuService.menuManage_delAction(actionId);

        return "include/taglib";
    }
}
