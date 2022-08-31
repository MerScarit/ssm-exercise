package com.scarit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * 角色和菜单关联表(ExerciseRoleMenu)实体类
 *
 * @author makejava
 * @since 2022-08-26 16:44:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -72892853129531015L;

    //分页使用的字段
    private int page;
    private int size;
    private Sort sort;
}

