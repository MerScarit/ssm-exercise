package com.scarit.entity;

import java.io.Serializable;

/**
 * 用户和角色关联表(ExerciseUserRole)实体类
 *
 * @author makejava
 * @since 2022-08-29 16:40:19
 */
public class ExerciseUserRole implements Serializable {
    private static final long serialVersionUID = -43803144642761523L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}

