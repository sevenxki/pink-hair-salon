package edu.scau.misp.hs.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (HsMenu)实体类
 *
 * @author makejava
 * @since 2022-05-12 16:25:56
 */
public class HsMenu implements Serializable {
    private static final long serialVersionUID = 315850471910245546L;
    /**
     * 菜单/按钮ID
     */
    private Integer menuId;
    /**
     * 菜单/按钮名称
     */
    private String menuName;
    /**
     * 上级菜单ID
     */
    private Long parentId;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由参数
     */
    private String query;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 0不可用，1可用
     */
    private Integer available;


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

}

