package com.scarit.dao;

import com.scarit.entity.ExerciseUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 用户信息表(ExerciseUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-26 16:44:04
 */
public interface ExerciseUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    ExerciseUser queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param exerciseUser 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ExerciseUser> queryAllByLimit(@Param("exerciseUser") ExerciseUser exerciseUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param exerciseUser 查询条件
     * @return 总行数
     */
    long count(ExerciseUser exerciseUser);

    /**
     * 新增数据
     *
     * @param exerciseUser 实例对象
     * @return 影响行数
     */
    int insert(ExerciseUser exerciseUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExerciseUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExerciseUser> entities);

    /**
     * 修改数据
     *
     * @param exerciseUser 实例对象
     * @return 影响行数
     */
    int update(ExerciseUser exerciseUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    /**
     * 通过用户名查询用户
     * @param userName
     * @return
     */
    ExerciseUser queryByUserName(String userName);

    /**
     * 通过用户id查询角色和权限等信息
     * @param userId
     * @return
     */
    ExerciseUser getInfo(Long userId);


}

