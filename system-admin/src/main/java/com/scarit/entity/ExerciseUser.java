package com.scarit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表(ExerciseUser)实体类
 *
 * @author makejava
 * @since 2022-08-26 16:44:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 976389961698702727L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户账号
     */
    @NotNull
    @Size(min = 5,max = 15,message = "用户名的长度应在5-15之间")
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 密码
     */
    @NotNull
    @Size(min = 5,max = 15,message = "密码的长度应在5-15之间")
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;
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
     * 用户拥有的角色
     */
    private List<ExerciseRole> exerciseRoles;


}

