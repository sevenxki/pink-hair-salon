package edu.scau.misp.hs.service;


import edu.scau.misp.hs.entity.HsCustomer;
import edu.scau.misp.hs.entity.HsKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsCustomer)表服务接口
 *
 * @author makejava
 * @since 2022-03-20 01:04:12
 */
public interface HsCustomerService {

    /**
     * 通过ID查询单条数据
     *
     * @param customerId 主键
     * @return 实例对象
     */
    HsCustomer queryById(Integer customerId);

    /**
     * 查询多条数据
     *
     * @param hsCustomer 筛选条件
     * @return 查询结果
     */
    List<HsCustomer> queryAll(HsCustomer hsCustomer);

    /**
     * 新增数据
     *
     * @param hsCustomer 实例对象
     * @return 实例对象
     */
    HsCustomer insert(HsCustomer hsCustomer);

    /**
     * 修改数据
     *
     * @param hsCustomer 实例对象
     * @return 实例对象
     */
    HsCustomer update(HsCustomer hsCustomer);

    /**
     * 通过主键删除数据
     *
     * @param customerId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer customerId);

}
