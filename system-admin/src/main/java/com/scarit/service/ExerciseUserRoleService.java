package com.scarit.service;

import com.scarit.entity.ExerciseUserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户和角色关联表(ExerciseUserRole)表服务接口
 *
 * @author makejava
 * @since 2022-08-29 16:40:20
 */
public interface ExerciseUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    ExerciseUserRole queryById(Long userId);

    /**
     * 分页查询
     *
     * @param exerciseUserRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ExerciseUserRole> queryByPage(ExerciseUserRole exerciseUserRole, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param exerciseUserRole 实例对象
     * @return 实例对象
     */
    ExerciseUserRole insert(ExerciseUserRole exerciseUserRole);

    /**
     * 修改数据
     *
     * @param exerciseUserRole 实例对象
     * @return 实例对象
     */
    ExerciseUserRole update(ExerciseUserRole exerciseUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

}
