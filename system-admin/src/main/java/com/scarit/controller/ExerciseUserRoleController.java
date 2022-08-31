package com.scarit.controller;

import com.scarit.entity.ExerciseUserRole;
import com.scarit.service.ExerciseUserRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户和角色关联表(ExerciseUserRole)表控制层
 *
 * @author makejava
 * @since 2022-08-29 16:40:19
 */
@RestController
@RequestMapping("exerciseUserRole")
public class ExerciseUserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private ExerciseUserRoleService exerciseUserRoleService;

    /**
     * 分页查询
     *
     * @param exerciseUserRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ExerciseUserRole>> queryByPage(ExerciseUserRole exerciseUserRole, PageRequest pageRequest) {
        return ResponseEntity.ok(this.exerciseUserRoleService.queryByPage(exerciseUserRole, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ExerciseUserRole> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.exerciseUserRoleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param exerciseUserRole 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ExerciseUserRole> add(ExerciseUserRole exerciseUserRole) {
        return ResponseEntity.ok(this.exerciseUserRoleService.insert(exerciseUserRole));
    }

    /**
     * 编辑数据
     *
     * @param exerciseUserRole 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ExerciseUserRole> edit(ExerciseUserRole exerciseUserRole) {
        return ResponseEntity.ok(this.exerciseUserRoleService.update(exerciseUserRole));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.exerciseUserRoleService.deleteById(id));
    }

}

