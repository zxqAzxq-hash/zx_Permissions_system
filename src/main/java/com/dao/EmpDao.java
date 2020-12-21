package com.dao;

import com.entity.Dept;
import com.entity.Emp;

import java.util.List;

public interface EmpDao {
    List<Emp> empManage_listEmp(Emp emp);
    List<Dept> selectDept(Dept dept);
    List<Emp> selectEmp();
//    添加员工数据
    int empManage_addEmp(Emp emp);
    Emp toEditEmp(int empId);
    int editEmp(Emp emp);

//    根据id查询部门数据
    Dept toEditDept(int deptId);
//    插入dept中的一条数据
    int editDept(Dept dept);
//    删除
    int delDept(int deptId);
//    添加部门
    int insetDept(Dept dept);
}
