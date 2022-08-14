package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.mapper.HsKindMapper;
import edu.scau.misp.hs.service.HsKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (HsKind)表服务实现类
 *
 */
@Service
public class HsKindServiceImpl implements HsKindService {
    @Autowired
    private HsKindMapper hsKindMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param kindId 主键
     * @return 实例对象
     */
    @Override
    public HsKind queryById(Integer kindId) {
        return this.hsKindMapper.queryById(kindId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsKind 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsKind> queryAll(HsKind hsKind) {
        return this.hsKindMapper.queryAll(hsKind);
    }

    /**
     * 新增数据
     *
     * @param hsKind 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsKind hsKind) {
        this.hsKindMapper.insert(hsKind);
    }

    /**
     * 修改数据
     *
     * @param hsKind 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsKind hsKind) {
        this.hsKindMapper.update(hsKind);
    }

    /**
     * 通过主键删除数据
     *
     * @param kindId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer kindId) {
        return this.hsKindMapper.deleteById(kindId) > 0;
    }
}