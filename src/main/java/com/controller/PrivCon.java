package com.controller;

import com.entity.Menu;
import com.entity.Role;
import com.entity.SavePrivs;
import com.entity.SysAction;
import com.service.MenuService;
import com.service.PrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("priv")
public class PrivCon {
    @Autowired
    MenuService menuService;
    @Autowired
    PrivService privService;
//    进入权限管理
    @RequestMapping("privManage_init")
    public ModelAndView privManage_init(){
        ModelAndView modelAndView = new ModelAndView("system/privilege");
//        查询role表信息
        List<Role> roleList = privService.privManage_init();
        modelAndView.addObject("roleList",roleList);
        return modelAndView;
    }
//    查询所有内容返回Menu
    @RequestMapping("privManage_loadTree")
    @ResponseBody
    public Menu privManage_loadTree(long ts){
        return menuService.menuManage_menuTree();
    }
//    查询所有权限
    @RequestMapping("privManage_loadPrivs")
    @ResponseBody
    public List<List> privManage_loadPrivs(int roleId,long ts){
        List<List> lists = privService.privManage_loadPrivs(roleId);
        return lists;
    }
//    privManage_loadAction?menuId=3
    @RequestMapping("privManage_loadAction")
    @ResponseBody
    public List<SysAction> privManage_loadAction(Menu menu){
        List<SysAction> sysActions = menuService.authorActionList(menu);
        return sysActions;
    }
//    保存设置privManage_savePrivs
    @RequestMapping("privManage_savePrivs")
    @ResponseBody
    public String privManage_savePrivs(SavePrivs savePrivs){
        privService.privManage_savePrivs(savePrivs);

        return "succ";
    }
}
