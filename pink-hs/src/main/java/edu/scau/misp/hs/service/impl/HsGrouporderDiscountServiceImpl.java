package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsAccumulation;
import edu.scau.misp.hs.entity.HsGrouporder;
import edu.scau.misp.hs.entity.HsGrouporderDiscount;
import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.mapper.HsGrouporderDiscountMapper;
import edu.scau.misp.hs.mapper.HsGrouporderMapper;
import edu.scau.misp.hs.service.HsGrouporderDiscountService;
import edu.scau.misp.hs.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (HsGrouporderDiscount)表服务实现类
 *
 */
@Service
public class HsGrouporderDiscountServiceImpl implements HsGrouporderDiscountService {
    @Autowired
    private HsGrouporderDiscountMapper hsGrouporderDiscountMapper;
    @Autowired
    private HsGrouporderMapper hsGrouporderMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param grouporderDiscountId 主键
     * @return 实例对象
     */
    @Override
    public HsGrouporderDiscount queryById(Integer grouporderDiscountId) {
        return this.hsGrouporderDiscountMapper.queryById(grouporderDiscountId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsGrouporderDiscount 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsGrouporderDiscount> queryAll(HsGrouporderDiscount hsGrouporderDiscount) {
        return this.hsGrouporderDiscountMapper.queryAll(hsGrouporderDiscount);
    }

    /**
     * 新增数据
     *
     * @param hsGrouporderDiscount 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsGrouporderDiscount hsGrouporderDiscount) {
        this.hsGrouporderDiscountMapper.insert(hsGrouporderDiscount);
    }

    /**
     * 修改数据
     *
     * @param hsGrouporderDiscount 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsGrouporderDiscount hsGrouporderDiscount) {
        this.hsGrouporderDiscountMapper.update(hsGrouporderDiscount);
    }

    /**
     * 通过主键删除数据
     *
     * @param grouporderDiscountId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer grouporderDiscountId) {
        return this.hsGrouporderDiscountMapper.deleteById(grouporderDiscountId) > 0;
    }

    /**
     * 新增vip订单明细
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public void addNew(HsVip hsVip,String no) {
        Integer id = this.hsGrouporderMapper.queryByNO(no).getGrouporderId();
        HsGrouporderDiscount hsGrouporderDiscount1 = new HsGrouporderDiscount();
        hsGrouporderDiscount1.setGrouporderId(id);
        HsGrouporderDiscount hsGrouporderDiscount2 = new HsGrouporderDiscount();
        hsGrouporderDiscount2.setGrouporderId(id);
        HsGrouporderDiscount hsGrouporderDiscount3 = new HsGrouporderDiscount();
        hsGrouporderDiscount3.setGrouporderId(id);
        if(hsVip.getVipBalance()!=null){
            hsGrouporderDiscount1.setDiscountId(1);
            hsGrouporderDiscount1.setQuantity(1);
            hsGrouporderDiscount1.setAmount(hsVip.getVipBalance());
            this.hsGrouporderDiscountMapper.insert(hsGrouporderDiscount1);
        }
        if(hsVip.getVipHaircut()!=null){
            hsGrouporderDiscount2.setDiscountId(2);
            hsGrouporderDiscount2.setQuantity(hsVip.getVipHaircut());
            hsGrouporderDiscount2.setAmount(hsVip.getVipHaircut()*150.0);
            this.hsGrouporderDiscountMapper.insert(hsGrouporderDiscount2);
        }
        if(hsVip.getVipHaircare()!=null){
            hsGrouporderDiscount3.setDiscountId(3);
            hsGrouporderDiscount3.setQuantity(hsVip.getVipHaircare());
            hsGrouporderDiscount3.setAmount(hsVip.getVipHaircare()*200.0);
            this.hsGrouporderDiscountMapper.insert(hsGrouporderDiscount3);
        }
    }


}