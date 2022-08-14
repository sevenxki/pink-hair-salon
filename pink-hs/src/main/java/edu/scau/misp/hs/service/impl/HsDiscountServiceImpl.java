package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsDiscount;
import edu.scau.misp.hs.mapper.HsDiscountMapper;
import edu.scau.misp.hs.service.HsDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (HsDiscount)表服务实现类
 *
 */
@Service
public class HsDiscountServiceImpl implements HsDiscountService {
    @Autowired
    private HsDiscountMapper hsDiscountMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param discountId 主键
     * @return 实例对象
     */
    @Override
    public HsDiscount queryById(Integer discountId) {
        return this.hsDiscountMapper.queryById(discountId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsDiscount 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsDiscount> queryAll(HsDiscount hsDiscount) {
        return this.hsDiscountMapper.queryAll(hsDiscount);
    }

    /**
     * 新增数据
     *
     * @param hsDiscount 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsDiscount hsDiscount) {
        this.hsDiscountMapper.insert(hsDiscount);
    }

    /**
     * 修改数据
     *
     * @param hsDiscount 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsDiscount hsDiscount) {
        this.hsDiscountMapper.update(hsDiscount);
    }

    /**
     * 通过主键删除数据
     *
     * @param discountId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer discountId) {
        return this.hsDiscountMapper.deleteById(discountId) > 0;
    }
}