package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsDiscount;
import edu.scau.misp.hs.entity.HsPaymentDiscount;
import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.mapper.HsDiscountMapper;
import edu.scau.misp.hs.mapper.HsPaymentDiscountMapper;
import edu.scau.misp.hs.mapper.HsPaymentOrderMapper;
import edu.scau.misp.hs.service.HsPaymentDiscountService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsPaymentDiscount)表服务实现类
 *
 * @author makejava
 * @since 2022-04-16 17:12:19
 */
@Service("hsPaymentDiscountService")
public class HsPaymentDiscountServiceImpl implements HsPaymentDiscountService {
    @Resource
    private HsPaymentDiscountMapper hsPaymentDiscountMapper;
    @Resource
    private HsPaymentOrderMapper hsPaymentOrderMapper;
    @Resource
    private HsDiscountMapper hsDiscountMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param paymentDiscountId 主键
     * @return 实例对象
     */
    @Override
    public HsPaymentDiscount queryById(Integer paymentDiscountId) {
        HsPaymentDiscount hsPaymentDiscount = hsPaymentDiscountMapper.queryById(paymentDiscountId);
        hsPaymentDiscount.setHsPaymentOrder(hsPaymentOrderMapper.queryById(hsPaymentDiscount.getPaymentOrderId()));
        hsPaymentDiscount.setHsDiscount(hsDiscountMapper.queryById(hsPaymentDiscount.getDiscountId()));
        hsPaymentDiscountMapper.update(hsPaymentDiscount);
        return hsPaymentDiscount;
    }

    @Override
    public HsPaymentDiscount queryByPayment(Integer paymentOrderId) {
        HsPaymentDiscount hsPaymentDiscount = hsPaymentDiscountMapper.queryByPayment(paymentOrderId);
        hsPaymentDiscount.setHsPaymentOrder(hsPaymentOrderMapper.queryById(paymentOrderId));
        hsPaymentDiscount.setHsDiscount(hsDiscountMapper.queryById(hsPaymentDiscount.getDiscountId()));
        return hsPaymentDiscount;
    }

    /**
     *
     * @param hsPaymentDiscount 筛选条件
     * @return
     */
    @Override
    public List<HsPaymentDiscount> queryAll(HsPaymentDiscount hsPaymentDiscount) {
        List<HsPaymentDiscount> list = this.hsPaymentDiscountMapper.queryAll(hsPaymentDiscount);
        for (HsPaymentDiscount hsPaymentDiscount1 : list){
            hsPaymentDiscount1.setHsPaymentOrder(hsPaymentOrderMapper.queryById(hsPaymentDiscount1.getPaymentOrderId()));
            hsPaymentDiscount1.setHsDiscount(hsDiscountMapper.queryById(hsPaymentDiscount.getDiscountId()));
            hsPaymentDiscountMapper.update(hsPaymentDiscount1);
        }
        return list;
    }

    /**
     * 新增数据
     *
     * @param hsPaymentDiscount 实例对象
     * @return 实例对象
     */
    @Override
    public HsPaymentDiscount insert(HsPaymentDiscount hsPaymentDiscount) {
        this.hsPaymentDiscountMapper.insert(hsPaymentDiscount);
        return hsPaymentDiscount;
    }

    /**
     * 修改数据
     *
     * @param hsPaymentDiscount 实例对象
     * @return 实例对象
     */
    @Override
    public HsPaymentDiscount update(HsPaymentDiscount hsPaymentDiscount) {
        this.hsPaymentDiscountMapper.update(hsPaymentDiscount);
        return this.queryById(hsPaymentDiscount.getPaymentDiscountId());
    }

    /**
     * 通过主键删除数据
     *
     * @param paymentDiscountId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer paymentDiscountId) {
        return this.hsPaymentDiscountMapper.deleteById(paymentDiscountId) > 0;
    }

//    /**
//     * 通过付款订单号删除数据
//     *
//     * @param paymentOrderId 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteByPaymentOrderId(Integer paymentOrderId) {
//        return this.hsPaymentDiscountMapper.deleteByPaymentOrderId(paymentOrderId) > 0;
//    }


}
