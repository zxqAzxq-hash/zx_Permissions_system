package com.utility;

import com.entity.MenuType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GetType {
    public List<MenuType> getType(){
        List<MenuType> menus = new ArrayList<>();
        MenuType menuType = new MenuType();
        MenuType menuType1 = new MenuType();
        menuType.setTypeId(1);
        menuType.setTypeName("一级菜单");
        menus.add(menuType);
        menuType1.setTypeId(2);
        menuType1.setTypeName("二级菜单");
        menus.add(menuType1);
        return menus;
    }
}
