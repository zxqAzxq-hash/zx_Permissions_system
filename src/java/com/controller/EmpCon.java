package com.controller;

import com.dao.EmpDao;
import com.entity.Dept;
import com.entity.Emp;
import com.service.EmpService;
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
@RequestMapping("emp")
public class EmpCon {

    @Autowired
    MenuService menuService;
    @Autowired
    EmpService empService;

//    员工表主页面--emp---ListUrl---empList
    @RequestMapping("empManage_listEmp")
    public ModelAndView empManage_listEmp(Emp emp, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("basic/emp_list");
        Cookie[] cookies = request.getCookies();
        List<String> stringList = menuService.ListUrl(cookies);
//        查询emp的所有
        List<Emp> emps = empService.empManage_listEmp(emp);
        modelAndView.addObject("ListUrl",stringList);
        modelAndView.addObject("empList",emps);
        return modelAndView;
    }
    @Autowired
    EmpDao dao;
//    加载添加员工页面
    @RequestMapping("empManage_toAddEmp")
    public ModelAndView empManage_toAddEmp(){
        ModelAndView modelAndView = new ModelAndView("basic/emp_add");
        List<Dept> depts = dao.selectDept(null);
        List<Emp> emps = dao.selectEmp();
        modelAndView.addObject("empTypeList",emps);
        modelAndView.addObject("deptList",depts);
        return modelAndView;
    }
//    添加员工
    @RequestMapping("empManage_addEmp")
    public String empManage_addEmp(Emp emp){
        dao.empManage_addEmp(emp);
        return "redirect:/emp/empManage_listEmp";
    }
//    编辑员工数据--empManage_toEditEmp.do?empId=1002
    @RequestMapping("empManage_toEditEmp")
    public ModelAndView empManage_toEditEmp(int empId){
        ModelAndView modelAndView = new ModelAndView("basic/emp_edit");
        Emp emp = dao.toEditEmp(empId);
        List<Dept> depts = dao.selectDept(null);
        List<Emp> emps = dao.selectEmp();
        modelAndView.addObject("empTypeList",emps);
        modelAndView.addObject("deptList",depts);
        modelAndView.addObject("emp",emp);
        return modelAndView;
    }
//    提交修改员工
    @RequestMapping("empManage_editEmp")
    public String empManage_editEmp(Emp emp){
        int i = dao.editEmp(emp);
        return "redirect:/emp/empManage_listEmp";
    }

}

