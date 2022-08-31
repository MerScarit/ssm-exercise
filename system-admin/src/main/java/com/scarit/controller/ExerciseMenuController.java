package com.scarit.controller;

import com.scarit.entity.ExerciseMenu;
import com.scarit.service.ExerciseMenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜单权限表(ExerciseMenu)表控制层
 *
 * @author makejava
 * @since 2022-08-26 16:44:02
 */
@RestController
@RequestMapping("exerciseMenu")
public class ExerciseMenuController {
    /**
     * 服务对象
     */
    @Resource
    private ExerciseMenuService exerciseMenuService;

    /**
     * 分页查询
     *
     * @param exerciseMenu 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ExerciseMenu>> queryByPage(ExerciseMenu exerciseMenu, PageRequest pageRequest) {
        return ResponseEntity.ok(this.exerciseMenuService.queryByPage(exerciseMenu, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ExerciseMenu> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.exerciseMenuService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param exerciseMenu 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ExerciseMenu> add(ExerciseMenu exerciseMenu) {
        return ResponseEntity.ok(this.exerciseMenuService.insert(exerciseMenu));
    }

    /**
     * 编辑数据
     *
     * @param exerciseMenu 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ExerciseMenu> edit(ExerciseMenu exerciseMenu) {
        return ResponseEntity.ok(this.exerciseMenuService.update(exerciseMenu));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.exerciseMenuService.deleteById(id));
    }

}

