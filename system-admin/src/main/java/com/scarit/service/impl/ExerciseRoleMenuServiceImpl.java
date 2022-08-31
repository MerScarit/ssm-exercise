package com.scarit.service.impl;

import com.scarit.entity.ExerciseRoleMenu;
import com.scarit.dao.ExerciseRoleMenuDao;
import com.scarit.service.ExerciseRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 角色和菜单关联表(ExerciseRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-08-29 16:40:19
 */
@Service("exerciseRoleMenuService")
public class ExerciseRoleMenuServiceImpl implements ExerciseRoleMenuService {
    @Resource
    private ExerciseRoleMenuDao exerciseRoleMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public ExerciseRoleMenu queryById(Long roleId) {
        return this.exerciseRoleMenuDao.queryById(roleId);
    }

    /**
     * 分页查询
     *
     * @param exerciseRoleMenu 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ExerciseRoleMenu> queryByPage(ExerciseRoleMenu exerciseRoleMenu, PageRequest pageRequest) {
        long total = this.exerciseRoleMenuDao.count(exerciseRoleMenu);
        return new PageImpl<>(this.exerciseRoleMenuDao.queryAllByLimit(exerciseRoleMenu, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param exerciseRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseRoleMenu insert(ExerciseRoleMenu exerciseRoleMenu) {
        this.exerciseRoleMenuDao.insert(exerciseRoleMenu);
        return exerciseRoleMenu;
    }

    /**
     * 修改数据
     *
     * @param exerciseRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseRoleMenu update(ExerciseRoleMenu exerciseRoleMenu) {
        this.exerciseRoleMenuDao.update(exerciseRoleMenu);
        return this.queryById(exerciseRoleMenu.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.exerciseRoleMenuDao.deleteById(roleId) > 0;
    }
}
