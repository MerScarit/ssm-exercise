package com.scarit.service;

import com.scarit.entity.ExerciseMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 菜单权限表(ExerciseMenu)表服务接口
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
public interface ExerciseMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    ExerciseMenu queryById(Long menuId);

    /**
     * 分页查询
     *
     * @param exerciseMenu 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ExerciseMenu> queryByPage(ExerciseMenu exerciseMenu, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param exerciseMenu 实例对象
     * @return 实例对象
     */
    ExerciseMenu insert(ExerciseMenu exerciseMenu);

    /**
     * 修改数据
     *
     * @param exerciseMenu 实例对象
     * @return 实例对象
     */
    ExerciseMenu update(ExerciseMenu exerciseMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

}
