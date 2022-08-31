package com.scarit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 角色信息表(ExerciseRole)实体类
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRole implements Serializable {
    private static final long serialVersionUID = -70518239005625275L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标签
     */
    private String roleTag;
    /**
     * 显示顺序
     */
    private Integer roleSort;
    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
    * 角色拥有的权限
    */
    private List<ExerciseMenu> exerciseMenus;



}

