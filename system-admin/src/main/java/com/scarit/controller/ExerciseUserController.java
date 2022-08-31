package com.scarit.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scarit.annotation.HasPermission;
import com.scarit.annotation.HasRole;
import com.scarit.annotation.Log;
import com.scarit.annotation.Repeat;
import com.scarit.constant.Constants;
import com.scarit.entity.ExerciseUser;
import com.scarit.entity.LoginUser;
import com.scarit.service.ExerciseUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 用户信息表(ExerciseUser)表控制层
 *
 * @author makejava
 * @since 2022-08-26 16:44:04   
 */
@RestController
@RequestMapping("exerciseUser")
public class ExerciseUserController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ExerciseUserService exerciseUserService;

    /**
     * 分页查询
     *
     * @param exerciseUser 筛选条件
     * @return 查询结果
     */
    @GetMapping
    @Log(tittle="查询用户",businessType="用户操作")
    @Repeat(100)
    public ResponseEntity<Page<ExerciseUser>> queryByPage( ExerciseUser exerciseUser) {
        return ResponseEntity.ok(this.exerciseUserService.queryByPage(exerciseUser, PageRequest.of(exerciseUser.getPage(),exerciseUser.getSize())));
    }

    /**
     *
     * @return
     */
    @GetMapping("getInfo")
    public ResponseEntity<HashMap<String, List<String>>> getInfo() {
        return ResponseEntity.ok(this.exerciseUserService.getInfo());
    }
    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @HasPermission("system:user:query")
    public ResponseEntity<ExerciseUser> queryById(@PathVariable("id") Long id) {
        //
        LoginUser loginUser = getLoginUser();
        //获取用户，根据用户查询他的角色或者权限，去判断接口所需要的权限是否包含在用户所拥有的权限当中
        List<String> hasPerms = redisTemplate.getObject(Constants.PERMS_PREFIX + loginUser.getToken(), new TypeReference<>() {
        });
        if(!hasPerms.contains("system:user:query")){
            throw new RuntimeException("您没有该权限");
        }

        return ResponseEntity.ok(this.exerciseUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param exerciseUser 实体
     * @return 新增结果
     */
    @PostMapping
    @HasRole({"admin", "hr"})
    @Log(tittle="创建用户",businessType="用户操作")
    @Repeat
    public ResponseEntity<ExerciseUser> add(ExerciseUser exerciseUser) {
        return ResponseEntity.ok(this.exerciseUserService.insert(exerciseUser));
    }
    /**
     * 编辑数据
     * @param exerciseUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ExerciseUser> edit(ExerciseUser exerciseUser) {
        return ResponseEntity.ok(this.exerciseUserService.update(exerciseUser));
    }
    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.exerciseUserService.deleteById(id));
    }

}

