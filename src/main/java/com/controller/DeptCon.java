package com.controller;

import com.dao.EmpDao;
import com.entity.Dept;
import com.service.MenuService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.net.sdp.SdpSupport;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("emp")
public class DeptCon {
    @Autowired
    MenuService menuService;
    @Autowired
    EmpDao dao;

//    部门管理操作--ListUrl--deptList
    @RequestMapping("deptManage_listDept")
    public ModelAndView deptManage_listDept(HttpServletRequest request,Dept dept){
        ModelAndView modelAndView = new ModelAndView("basic/dept_list");
        Cookie[] cookies = request.getCookies();
        List<String> stringList = menuService.ListUrl(cookies);
        List<Dept> depts = dao.selectDept(dept);
        modelAndView.addObject("deptList",depts);
        modelAndView.addObject("ListUrl",stringList);
        return modelAndView;
    }
//    编辑按钮
    @RequestMapping("deptManage_toEditDept")
    public ModelAndView deptManage_toEditDept(int deptId){
        ModelAndView modelAndView = new ModelAndView("basic/dept_edit");
        Dept dept = dao.toEditDept(deptId);
        modelAndView.addObject("dept",dept);
        return modelAndView;
    }
//    修改编辑数据
    @RequestMapping("deptManage_editDept")
    public String deptManage_editDept(Dept dept){
        int i = dao.editDept(dept);
        return "redirect:/emp/deptManage_listDept";
    }
//   删除表数据
    @RequestMapping("deptManage_delDept")
    public String deptManage_delDept(int deptId){
        int i = dao.delDept(deptId);
        return "redirect:/emp/deptManage_listDept";
    }
//    添加部门
    @RequestMapping("deptManage_toAddDept")
    public String deptManage_toAddDept(){
        return "basic/dept_add";
    }
    @RequestMapping("deptManage_addDept")
    public String deptManage_addDept(Dept dept){
        int i = dao.insetDept(dept);
        return "redirect:/emp/deptManage_listDept";
    }
    @RequestMapping("sfaf")
    public static void ss(){
        System.out.println("kkk");
        System.out.println("ssssssssssssssdasfas");
    }
}
