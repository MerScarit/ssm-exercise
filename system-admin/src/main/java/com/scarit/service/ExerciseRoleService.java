package com.scarit.service;

import com.scarit.entity.ExerciseRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 角色信息表(ExerciseRole)表服务接口
 *
 * @author makejava
 * @since 2022-08-26 16:44:04
 */
public interface ExerciseRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    ExerciseRole queryById(Long roleId);

    /**
     * 分页查询
     *
     * @param exerciseRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ExerciseRole> queryByPage(ExerciseRole exerciseRole, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param exerciseRole 实例对象
     * @return 实例对象
     */
    ExerciseRole insert(ExerciseRole exerciseRole);

    /**
     * 修改数据
     *
     * @param exerciseRole 实例对象
     * @return 实例对象
     */
    ExerciseRole update(ExerciseRole exerciseRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

}
