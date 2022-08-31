package com.scarit.service.impl;

import com.scarit.entity.ExerciseRole;
import com.scarit.dao.ExerciseRoleDao;
import com.scarit.service.ExerciseRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 角色信息表(ExerciseRole)表服务实现类
 *
 * @author makejava
 * @since 2022-08-26 16:44:04
 */
@Service("exerciseRoleService")
public class ExerciseRoleServiceImpl implements ExerciseRoleService {
    @Resource
    private ExerciseRoleDao exerciseRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public ExerciseRole queryById(Long roleId) {
        return this.exerciseRoleDao.queryById(roleId);
    }

    /**
     * 分页查询
     *
     * @param exerciseRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ExerciseRole> queryByPage(ExerciseRole exerciseRole, PageRequest pageRequest) {
        long total = this.exerciseRoleDao.count(exerciseRole);
        return new PageImpl<>(this.exerciseRoleDao.queryAllByLimit(exerciseRole, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param exerciseRole 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseRole insert(ExerciseRole exerciseRole) {
        this.exerciseRoleDao.insert(exerciseRole);
        return exerciseRole;
    }

    /**
     * 修改数据
     *
     * @param exerciseRole 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseRole update(ExerciseRole exerciseRole) {
        this.exerciseRoleDao.update(exerciseRole);
        return this.queryById(exerciseRole.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.exerciseRoleDao.deleteById(roleId) > 0;
    }
}
