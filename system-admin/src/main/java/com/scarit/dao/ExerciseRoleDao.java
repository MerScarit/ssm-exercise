package com.scarit.dao;

import com.scarit.entity.ExerciseRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 角色信息表(ExerciseRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
public interface ExerciseRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    ExerciseRole queryById(Long roleId);

    /**
     * 查询指定行数据
     *
     * @param exerciseRole 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ExerciseRole> queryAllByLimit(ExerciseRole exerciseRole, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param exerciseRole 查询条件
     * @return 总行数
     */
    long count(ExerciseRole exerciseRole);

    /**
     * 新增数据
     *
     * @param exerciseRole 实例对象
     * @return 影响行数
     */
    int insert(ExerciseRole exerciseRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExerciseRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExerciseRole> entities);

    /**
     * 修改数据
     *
     * @param exerciseRole 实例对象
     * @return 影响行数
     */
    int update(ExerciseRole exerciseRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Long roleId);

}

