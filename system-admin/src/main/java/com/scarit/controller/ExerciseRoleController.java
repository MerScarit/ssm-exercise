package com.scarit.controller;

import com.scarit.entity.ExerciseRole;
import com.scarit.service.ExerciseRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色信息表(ExerciseRole)表控制层
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
@RestController
@RequestMapping("exerciseRole")
public class ExerciseRoleController {
    /**
     * 服务对象
     */
    @Resource
    private ExerciseRoleService exerciseRoleService;

    /**
     * 分页查询
     *
     * @param exerciseRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ExerciseRole>> queryByPage(ExerciseRole exerciseRole, PageRequest pageRequest) {
        return ResponseEntity.ok(this.exerciseRoleService.queryByPage(exerciseRole, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ExerciseRole> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.exerciseRoleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param exerciseRole 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ExerciseRole> add(ExerciseRole exerciseRole) {
        return ResponseEntity.ok(this.exerciseRoleService.insert(exerciseRole));
    }

    /**
     * 编辑数据
     *
     * @param exerciseRole 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ExerciseRole> edit(ExerciseRole exerciseRole) {
        return ResponseEntity.ok(this.exerciseRoleService.update(exerciseRole));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.exerciseRoleService.deleteById(id));
    }

}

