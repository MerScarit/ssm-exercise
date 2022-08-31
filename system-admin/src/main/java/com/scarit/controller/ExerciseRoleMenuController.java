package com.scarit.controller;

import com.scarit.entity.ExerciseRoleMenu;
import com.scarit.service.ExerciseRoleMenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色和菜单关联表(ExerciseRoleMenu)表控制层
 *
 * @author makejava
 * @since 2022-08-29 16:40:11
 */
@RestController
@RequestMapping("exerciseRoleMenu")
public class ExerciseRoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private ExerciseRoleMenuService exerciseRoleMenuService;

    /**
     * 分页查询
     *
     * @param exerciseRoleMenu 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ExerciseRoleMenu>> queryByPage(ExerciseRoleMenu exerciseRoleMenu, PageRequest pageRequest) {
        return ResponseEntity.ok(this.exerciseRoleMenuService.queryByPage(exerciseRoleMenu, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ExerciseRoleMenu> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.exerciseRoleMenuService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param exerciseRoleMenu 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ExerciseRoleMenu> add(ExerciseRoleMenu exerciseRoleMenu) {
        return ResponseEntity.ok(this.exerciseRoleMenuService.insert(exerciseRoleMenu));
    }

    /**
     * 编辑数据
     *
     * @param exerciseRoleMenu 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ExerciseRoleMenu> edit(ExerciseRoleMenu exerciseRoleMenu) {
        return ResponseEntity.ok(this.exerciseRoleMenuService.update(exerciseRoleMenu));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.exerciseRoleMenuService.deleteById(id));
    }

}

