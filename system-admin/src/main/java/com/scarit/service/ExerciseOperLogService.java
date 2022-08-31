package com.scarit.service;

import com.scarit.entity.ExerciseOperLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 操作日志(ExerciseOperLog)表服务接口
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
public interface ExerciseOperLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    ExerciseOperLog queryById(Integer operId);

    /**
     * 分页查询
     *
     * @param exerciseOperLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ExerciseOperLog> queryByPage(ExerciseOperLog exerciseOperLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param exerciseOperLog 实例对象
     * @return 实例对象
     */
    void insert(ExerciseOperLog exerciseOperLog);

    /**
     * 修改数据
     *
     * @param exerciseOperLog 实例对象
     * @return 实例对象
     */
    ExerciseOperLog update(ExerciseOperLog exerciseOperLog);

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer operId);

}
