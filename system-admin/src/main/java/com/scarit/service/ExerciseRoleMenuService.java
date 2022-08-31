package com.scarit.service;

import com.scarit.entity.ExerciseRoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 角色和菜单关联表(ExerciseRoleMenu)表服务接口
 *
 * @author makejava
 * @since 2022-08-29 16:40:19
 */
public interface ExerciseRoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    ExerciseRoleMenu queryById(Long roleId);

    /**
     * 分页查询
     *
     * @param exerciseRoleMenu 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ExerciseRoleMenu> queryByPage(ExerciseRoleMenu exerciseRoleMenu, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param exerciseRoleMenu 实例对象
     * @return 实例对象
     */
    ExerciseRoleMenu insert(ExerciseRoleMenu exerciseRoleMenu);

    /**
     * 修改数据
     *
     * @param exerciseRoleMenu 实例对象
     * @return 实例对象
     */
    ExerciseRoleMenu update(ExerciseRoleMenu exerciseRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

}
