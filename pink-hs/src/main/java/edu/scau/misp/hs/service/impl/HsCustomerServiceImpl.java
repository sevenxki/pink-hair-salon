package edu.scau.misp.hs.service.impl;


import edu.scau.misp.hs.entity.HsCustomer;
import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.mapper.HsCustomerMapper;
import edu.scau.misp.hs.service.HsCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsCustomer)表服务实现类
 *
 */
@Service("hsCustomerService")
public class HsCustomerServiceImpl implements HsCustomerService {
    @Resource
    private HsCustomerMapper hsCustomerMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param customerId 主键
     * @return 实例对象
     */
    @Override
    public HsCustomer queryById(Integer customerId) {
        return this.hsCustomerMapper.queryById(customerId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsCustomer 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsCustomer> queryAll(HsCustomer hsCustomer) {
        return this.hsCustomerMapper.queryAll(hsCustomer);
    }

    /**
     * 新增数据
     *
     * @param hsCustomer 实例对象
     * @return 实例对象
     */
    @Override
    public HsCustomer insert(HsCustomer hsCustomer) {
        this.hsCustomerMapper.insert(hsCustomer);
        return hsCustomer;
    }

    /**
     * 修改数据
     *
     * @param hsCustomer 实例对象
     * @return 实例对象
     */
    @Override
    public HsCustomer update(HsCustomer hsCustomer) {
        this.hsCustomerMapper.update(hsCustomer);
        return this.queryById(hsCustomer.getCustomerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param customerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer customerId) {
        return this.hsCustomerMapper.deleteById(customerId) > 0;
    }
}
