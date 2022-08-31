package com.scarit.service.impl;

import com.scarit.entity.ExerciseMenu;
import com.scarit.dao.ExerciseMenuDao;
import com.scarit.service.ExerciseMenuService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 菜单权限表(ExerciseMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-08-26 16:44:03
 */
@Service("exerciseMenuService")
public class ExerciseMenuServiceImpl implements ExerciseMenuService {
    @Resource
    private ExerciseMenuDao exerciseMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public ExerciseMenu queryById(Long menuId) {
        return this.exerciseMenuDao.queryById(menuId);
    }

    /**
     * 分页查询
     *
     * @param exerciseMenu 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ExerciseMenu> queryByPage(ExerciseMenu exerciseMenu, PageRequest pageRequest) {
        long total = this.exerciseMenuDao.count(exerciseMenu);
        return new PageImpl<>(this.exerciseMenuDao.queryAllByLimit(exerciseMenu, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param exerciseMenu 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseMenu insert(ExerciseMenu exerciseMenu) {
        this.exerciseMenuDao.insert(exerciseMenu);
        return exerciseMenu;
    }

    /**
     * 修改数据
     *
     * @param exerciseMenu 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseMenu update(ExerciseMenu exerciseMenu) {
        this.exerciseMenuDao.update(exerciseMenu);
        return this.queryById(exerciseMenu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.exerciseMenuDao.deleteById(menuId) > 0;
    }
}
