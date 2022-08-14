package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsMenu;
import edu.scau.misp.hs.mapper.HsMenuMapper;
import edu.scau.misp.hs.service.HsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (HsMenu)表服务实现类
 *
 */
@Service
public class HsMenuServiceImpl implements HsMenuService {
    @Autowired
    private HsMenuMapper hsMenuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public HsMenu queryById(Integer menuId) {
        return this.hsMenuMapper.queryById(menuId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsMenu 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsMenu> queryAll(HsMenu hsMenu) {
        return this.hsMenuMapper.queryAll(hsMenu);
    }

    /**
     * 新增数据
     *
     * @param hsMenu 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsMenu hsMenu) {
        this.hsMenuMapper.insert(hsMenu);
    }

    /**
     * 修改数据
     *
     * @param hsMenu 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsMenu hsMenu) {
        this.hsMenuMapper.update(hsMenu);
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer menuId) {
        return this.hsMenuMapper.deleteById(menuId) > 0;
    }
}