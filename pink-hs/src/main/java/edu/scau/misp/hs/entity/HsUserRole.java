package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsUserRole)实体类
 *
 */
public class HsUserRole implements Serializable {
    private static final long serialVersionUID = 724862520168562629L;
    
    private Integer userRoleId;
    
    private Integer userId;
    
    private Integer roleId;


    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}

