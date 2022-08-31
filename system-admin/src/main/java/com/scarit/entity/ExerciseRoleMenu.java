package com.scarit.entity;

import java.io.Serializable;

/**
 * 角色和菜单关联表(ExerciseRoleMenu)实体类
 *
 * @author makejava
 * @since 2022-08-29 16:40:11
 */
public class ExerciseRoleMenu implements Serializable {
    private static final long serialVersionUID = 190995222610913531L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}

