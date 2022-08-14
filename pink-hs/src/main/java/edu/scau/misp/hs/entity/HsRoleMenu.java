package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsRoleMenu)实体类
 *
 * @author makejava
 * @since 2022-05-12 15:30:44
 */
public class HsRoleMenu implements Serializable {
    private static final long serialVersionUID = 489272425504568677L;
    /**
     * 角色菜单序号
     */
    private Integer roleMenuId;
    /**
     * 角色序号
     */
    private Integer roleId;
    /**
     * 菜单/按钮ID
     */
    private Integer menuId;


    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}

