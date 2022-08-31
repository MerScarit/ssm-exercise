package com.scarit.service.impl;

import com.scarit.entity.ExerciseOperLog;
import com.scarit.dao.ExerciseOperLogDao;
import com.scarit.service.ExerciseOperLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 操作日志(ExerciseOperLog)表服务实现类
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
@Service("exerciseOperLogService")
public class ExerciseOperLogServiceImpl implements ExerciseOperLogService {
    @Resource
    private ExerciseOperLogDao exerciseOperLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    @Override
    public ExerciseOperLog queryById(Integer operId) {
        return this.exerciseOperLogDao.queryById(operId);
    }

    /**
     * 分页查询
     *
     * @param exerciseOperLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ExerciseOperLog> queryByPage(ExerciseOperLog exerciseOperLog, PageRequest pageRequest) {
        long total = this.exerciseOperLogDao.count(exerciseOperLog);
        return new PageImpl<>(this.exerciseOperLogDao.queryAllByLimit(exerciseOperLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param exerciseOperLog 实例对象
     * @return 实例对象
     */
    @Override
    @Async("scarit-logger")
    public void insert(ExerciseOperLog exerciseOperLog) {
        System.out.println("scarit-----" + Thread.currentThread().getName());
        this.exerciseOperLogDao.insert(exerciseOperLog);
    }

    /**
     * 修改数据
     *
     * @param exerciseOperLog 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseOperLog update(ExerciseOperLog exerciseOperLog) {
        this.exerciseOperLogDao.update(exerciseOperLog);
        return this.queryById(exerciseOperLog.getOperId());
    }

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer operId) {
        return this.exerciseOperLogDao.deleteById(operId) > 0;
    }
}
