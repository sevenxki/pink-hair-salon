package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsVip;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * (HsVip)表服务接口
 *
 */
public interface HsVipService {

    /**
     * 通过ID查询单条数据
     *
     * @param vipId 主键
     * @return 实例对象
     */
    HsVip queryById(Integer vipId);

    /**
     * 查询多条数据
     *
     * @param hsVip 筛选条件
     * @return 查询结果
     */
    List<HsVip> queryAll(HsVip hsVip);

    /**
     * 新增数据
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    void insert(HsVip hsVip);

    /**
     * 修改数据
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    void update(HsVip hsVip);

    /**
     * 通过主键删除数据
     *
     * @param vipId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer vipId);

    /**
     * 通过电话找到all
     *
     * @param vipPhone 实例对象
     * @return 影响行数
     */
    HsVip queryAllByPhone(String vipPhone);

    /**
     * 新增Vip
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    void addNewVip(HsVip hsVip);

    /**
     * 删除vip
     *
     * @param vipPhone
     * @return 删除是否成功
     */
    Boolean deleteVip(@PathVariable String vipPhone);

    /**
     * 充值余额
     *
     * @param
     * @return 实例对象
     */
    void recharge(HsVip hsVip);

    /**
     * 充值优惠卡
     *
     * @param
     * @return 实例对象
     */
    void group(HsVip hsVip);
}