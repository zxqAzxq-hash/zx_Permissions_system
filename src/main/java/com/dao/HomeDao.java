package com.dao;

import com.entity.MandR;
import com.entity.Menu;

import java.util.List;

public interface HomeDao {
//    查询roleId
    int roleId(String name);
    List<Menu> homeAll(MandR mandR);

    Menu heardLoadMenu(int actionId);
    List<Menu> twoLoadMenu(int faId);
}
