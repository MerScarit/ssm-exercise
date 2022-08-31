package com.scarit.service;

import com.scarit.entity.ExerciseUser;
import com.scarit.entity.LoginUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;

/**
 * 用户信息表(ExerciseUser)表服务接口
 *
 * @author makejava
 * @since 2022-08-26 16:44:04
 */
public interface ExerciseUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    ExerciseUser queryById(Long userId);

    /**
     * 分页查询
     *
     * @param exerciseUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ExerciseUser> queryByPage(ExerciseUser exerciseUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param exerciseUser 实例对象
     * @return 实例对象
     */
    ExerciseUser insert(ExerciseUser exerciseUser);

    /**
     * 修改数据
     *
     * @param exerciseUser 实例对象
     * @return 实例对象
     */
    ExerciseUser update(ExerciseUser exerciseUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    /**
     * 使用用户名和密码登录
     * @param userName
     * @param password
     * @return
     */
    LoginUser login(String userName, String password);

    //登出的方法
    void logout();

    /**
     * 获取用户的所有信息
     * @return
     */
    HashMap<String, List<String>> getInfo();
}
