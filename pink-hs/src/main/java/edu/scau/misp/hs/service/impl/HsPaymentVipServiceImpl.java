package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsPaymentVip;
import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.mapper.HsDiscountMapper;
import edu.scau.misp.hs.mapper.HsPaymentOrderMapper;
import edu.scau.misp.hs.mapper.HsPaymentVipMapper;
import edu.scau.misp.hs.mapper.HsVipMapper;
import edu.scau.misp.hs.service.HsPaymentVipService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * (HsPaymentVip)表服务实现类
 *
 * @author makejava
 * @since 2022-04-16 17:12:08
 */
@Service("hsPaymentVipService")
public class HsPaymentVipServiceImpl implements HsPaymentVipService {
    @Resource
    private HsPaymentVipMapper hsPaymentVipMapper;
    @Resource
    private HsPaymentOrderMapper hsPaymentOrderMapper;
    @Resource
    private HsVipMapper hsVipMapper;
    
    /**
     * 通过ID查询单条数据
     *
     * @param paymentVipId 主键
     * @return 实例对象
     */
    @Override
    public HsPaymentVip queryById(Integer paymentVipId) {
        HsPaymentVip hsPaymentVip = hsPaymentVipMapper.queryById(paymentVipId);
        hsPaymentVip.setHsPaymentOrder(hsPaymentOrderMapper.queryById(hsPaymentVip.getPaymentOrderId()));
        hsPaymentVip.setHsVip(hsVipMapper.queryById(hsPaymentVip.getVipId()));
        return hsPaymentVip;
    }

    @Override
    public HsPaymentVip queryByPayment(Integer paymentOrderId) {
        HsPaymentVip hsPaymentVip = hsPaymentVipMapper.queryByPayment(paymentOrderId);
        hsPaymentVip.setHsPaymentOrder(hsPaymentOrderMapper.queryById(hsPaymentVip.getPaymentOrderId()));
        hsPaymentVip.setHsVip(hsVipMapper.queryById(hsPaymentVip.getVipId()));
        return hsPaymentVip;
    }

    /**
     *
     * @param hsPaymentVip 筛选条件
     * @return
     */
    @Override
    public List<HsPaymentVip> queryAll(HsPaymentVip hsPaymentVip) {
        List<HsPaymentVip> list = hsPaymentVipMapper.queryAll(hsPaymentVip);
        for (HsPaymentVip hsPaymentVip1 : list){
            hsPaymentVip1.setHsPaymentOrder(hsPaymentOrderMapper.queryById(hsPaymentVip1.getPaymentOrderId()));
            hsPaymentVip1.setHsVip(hsVipMapper.queryById(hsPaymentVip.getVipId()));
            hsPaymentVipMapper.update(hsPaymentVip1);
        }
        return list;
    }

    /**
     * 新增数据
     *
     * @param hsPaymentVip 实例对象
     * @return 实例对象
     */
    @Override
    public HsPaymentVip insert(HsPaymentVip hsPaymentVip) {
        this.hsPaymentVipMapper.insert(hsPaymentVip);
        return hsPaymentVip;
    }

    /**
     * 修改数据
     *
     * @param hsPaymentVip 实例对象
     * @return 实例对象
     */
    @Override
    public HsPaymentVip update(HsPaymentVip hsPaymentVip) {
        this.hsPaymentVipMapper.update(hsPaymentVip);
        return this.queryById(hsPaymentVip.getPaymentVipId());
    }

    /**
     * 通过主键删除数据
     *
     * @param paymentVipId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer paymentVipId) {
        return this.hsPaymentVipMapper.deleteById(paymentVipId) > 0;
    }

//    /**
//     * 通过付款订单号删除数据
//     *
//     * @param paymentOrderId 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteByPaymentOrderId(Integer paymentOrderId) {
//        return this.hsPaymentVipMapper.deleteByPaymentOrderId(paymentOrderId) > 0;
//    }
}
