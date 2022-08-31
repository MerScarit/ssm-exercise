package com.scarit.dao;

import com.scarit.entity.ExerciseUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 用户和角色关联表(ExerciseUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-29 16:40:19
 */
public interface ExerciseUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    ExerciseUserRole queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param exerciseUserRole 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ExerciseUserRole> queryAllByLimit(ExerciseUserRole exerciseUserRole, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param exerciseUserRole 查询条件
     * @return 总行数
     */
    long count(ExerciseUserRole exerciseUserRole);

    /**
     * 新增数据
     *
     * @param exerciseUserRole 实例对象
     * @return 影响行数
     */
    int insert(ExerciseUserRole exerciseUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExerciseUserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseUserRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExerciseUserRole> entities);

    /**
     * 修改数据
     *
     * @param exerciseUserRole 实例对象
     * @return 影响行数
     */
    int update(ExerciseUserRole exerciseUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

}

