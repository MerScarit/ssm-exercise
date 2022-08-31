package com.scarit.dao;

import com.scarit.entity.ExerciseOperLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 操作日志(ExerciseOperLog)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
public interface ExerciseOperLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    ExerciseOperLog queryById(Integer operId);

    /**
     * 查询指定行数据
     *
     * @param exerciseOperLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ExerciseOperLog> queryAllByLimit(ExerciseOperLog exerciseOperLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param exerciseOperLog 查询条件
     * @return 总行数
     */
    long count(ExerciseOperLog exerciseOperLog);

    /**
     * 新增数据
     *
     * @param exerciseOperLog 实例对象
     * @return 影响行数
     */
    int insert(ExerciseOperLog exerciseOperLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseOperLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExerciseOperLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseOperLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExerciseOperLog> entities);

    /**
     * 修改数据
     *
     * @param exerciseOperLog 实例对象
     * @return 影响行数
     */
    int update(ExerciseOperLog exerciseOperLog);

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 影响行数
     */
    int deleteById(Integer operId);

}

