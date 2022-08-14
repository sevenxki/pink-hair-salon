package edu.scau.misp.hs.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (HsUser)实体类
 *
 */
public class HsUser implements Serializable  {
    private static final long serialVersionUID = -11114435806238286L;
    
    private Integer userId;
    
    private String username;
    
    private String password;

    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private HsUserRole hsUserRole;

    private List<HsRole> hsRoles;

    public List<HsRole> getHsRoles() {
        return hsRoles;
    }

    public void setHsRoles(List<HsRole> hsRoles) {
        this.hsRoles = hsRoles;
    }

    public HsUserRole getHsUserRole() {
        return hsUserRole;
    }

    public void setHsUserRole(HsUserRole hsUserRole) {
        this.hsUserRole = hsUserRole;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Integer userId)
    {
        return userId != null && 1L == userId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    private List<HsRole> roles;
//
//    public List<HsRole> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<HsRole> roles) {
//        this.roles = roles;
//    }

}

