package com.scarit.service.impl;

import com.scarit.entity.ExerciseUserRole;
import com.scarit.dao.ExerciseUserRoleDao;
import com.scarit.service.ExerciseUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户和角色关联表(ExerciseUserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-08-29 16:40:20
 */
@Service("exerciseUserRoleService")
public class ExerciseUserRoleServiceImpl implements ExerciseUserRoleService {
    @Resource
    private ExerciseUserRoleDao exerciseUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public ExerciseUserRole queryById(Long userId) {
        return this.exerciseUserRoleDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param exerciseUserRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ExerciseUserRole> queryByPage(ExerciseUserRole exerciseUserRole, PageRequest pageRequest) {
        long total = this.exerciseUserRoleDao.count(exerciseUserRole);
        return new PageImpl<>(this.exerciseUserRoleDao.queryAllByLimit(exerciseUserRole, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param exerciseUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseUserRole insert(ExerciseUserRole exerciseUserRole) {
        this.exerciseUserRoleDao.insert(exerciseUserRole);
        return exerciseUserRole;
    }

    /**
     * 修改数据
     *
     * @param exerciseUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseUserRole update(ExerciseUserRole exerciseUserRole) {
        this.exerciseUserRoleDao.update(exerciseUserRole);
        return this.queryById(exerciseUserRole.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.exerciseUserRoleDao.deleteById(userId) > 0;
    }
}
