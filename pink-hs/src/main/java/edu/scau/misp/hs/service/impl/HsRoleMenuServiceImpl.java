package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsRoleMenu;
import edu.scau.misp.hs.mapper.HsRoleMenuMapper;
import edu.scau.misp.hs.service.HsRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (HsRoleMenu)表服务实现类
 *
 */
@Service
public class HsRoleMenuServiceImpl implements HsRoleMenuService {
    @Autowired
    private HsRoleMenuMapper hsRoleMenuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param roleMenuId 主键
     * @return 实例对象
     */
    @Override
    public HsRoleMenu queryById(Integer roleMenuId) {
        return this.hsRoleMenuMapper.queryById(roleMenuId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsRoleMenu 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsRoleMenu> queryAll(HsRoleMenu hsRoleMenu) {
        return this.hsRoleMenuMapper.queryAll(hsRoleMenu);
    }

    /**
     * 新增数据
     *
     * @param hsRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsRoleMenu hsRoleMenu) {
        this.hsRoleMenuMapper.insert(hsRoleMenu);
    }

    /**
     * 修改数据
     *
     * @param hsRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsRoleMenu hsRoleMenu) {
        this.hsRoleMenuMapper.update(hsRoleMenu);
    }

    /**
     * 通过主键删除数据
     *
     * @param roleMenuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleMenuId) {
        return this.hsRoleMenuMapper.deleteById(roleMenuId) > 0;
    }
}