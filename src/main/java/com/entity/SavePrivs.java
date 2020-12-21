package com.entity;

import java.util.List;

public class SavePrivs {
    private int roleId;
    private int menuId;
    private int actionId;
    private int[] menuIds;
    private int[] actionIds;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(int[] menuIds) {
        this.menuIds = menuIds;
    }

    public int[] getActionIds() {
        return actionIds;
    }

    public void setActionIds(int[] actionIds) {
        this.actionIds = actionIds;
    }
}
