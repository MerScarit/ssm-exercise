package com.scarit.controller;

import com.scarit.entity.ExerciseOperLog;
import com.scarit.service.ExerciseOperLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 操作日志(ExerciseOperLog)表控制层
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
@RestController
@RequestMapping("exerciseOperLog")
public class ExerciseOperLogController {
    /**
     * 服务对象
     */
    @Resource
    private ExerciseOperLogService exerciseOperLogService;

    /**
     * 分页查询
     *
     * @param exerciseOperLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ExerciseOperLog>> queryByPage(ExerciseOperLog exerciseOperLog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.exerciseOperLogService.queryByPage(exerciseOperLog, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ExerciseOperLog> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.exerciseOperLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param exerciseOperLog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<String> add(ExerciseOperLog exerciseOperLog) {
        this.exerciseOperLogService.insert(exerciseOperLog);
        return ResponseEntity.ok().build();
    }

    /**
     * 编辑数据
     * @param exerciseOperLog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ExerciseOperLog> edit(ExerciseOperLog exerciseOperLog) {
        return ResponseEntity.ok(this.exerciseOperLogService.update(exerciseOperLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.exerciseOperLogService.deleteById(id));
    }

}

