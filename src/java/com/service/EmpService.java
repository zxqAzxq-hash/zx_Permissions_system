package com.service;

import com.dao.EmpDao;
import com.entity.Emp;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    EmpDao empDao;

    public List<Emp> empManage_listEmp(Emp emp){
        //RowBounds
        RowBounds bounds = new RowBounds(0, 5);
        List<Emp> emps = empDao.empManage_listEmp(emp);
        return emps;
    }
}
