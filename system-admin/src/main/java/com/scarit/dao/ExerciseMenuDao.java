package com.scarit.dao;

import com.scarit.entity.ExerciseMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 菜单权限表(ExerciseMenu)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-26 16:44:02
 */
public interface ExerciseMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    ExerciseMenu queryById(Long menuId);

    /**
     * 查询指定行数据
     *
     * @param exerciseMenu 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ExerciseMenu> queryAllByLimit(ExerciseMenu exerciseMenu, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param exerciseMenu 查询条件
     * @return 总行数
     */
    long count(ExerciseMenu exerciseMenu);

    /**
     * 新增数据
     *
     * @param exerciseMenu 实例对象
     * @return 影响行数
     */
    int insert(ExerciseMenu exerciseMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExerciseMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExerciseMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExerciseMenu> entities);

    /**
     * 修改数据
     *
     * @param exerciseMenu 实例对象
     * @return 影响行数
     */
    int update(ExerciseMenu exerciseMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Long menuId);

}

