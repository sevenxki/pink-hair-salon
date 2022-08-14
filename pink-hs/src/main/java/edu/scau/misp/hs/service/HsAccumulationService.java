package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsAccumulation;
import edu.scau.misp.hs.entity.HsVip;

import java.util.List;

/**
 * (HsAccumulation)表服务接口
 *
 */
public interface HsAccumulationService {

    /**
     * 通过ID查询单条数据
     *
     * @param accumulationId 主键
     * @return 实例对象
     */
    HsAccumulation queryById(Integer accumulationId);

    /**
     * 查询多条数据
     *
     * @param hsAccumulation 筛选条件
     * @return 查询结果
     */
    List<HsAccumulation> queryAll(HsAccumulation hsAccumulation);

    /**
     * 新增数据
     *
     * @param hsAccumulation 实例对象
     * @return 实例对象
     */
    void insert(HsAccumulation hsAccumulation);

    /**
     * 修改数据
     *
     * @param hsAccumulation 实例对象
     * @return 实例对象
     */
    void update(HsAccumulation hsAccumulation);

    /**
     * 通过主键删除数据
     *
     * @param accumulationId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer accumulationId);

    /**
     * 新增累积
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    void addNewAccumulation(HsVip hsVip);

    /**
     * 更新累积(充值余额）
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    void updateB(HsVip hsVip);

    /**
     * 更新累积（买卡）
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    void updateC(HsVip hsVip);

    /**
     * 通过vipId删除数据
     *
     * @param vipId
     * @return 是否成功
     */
    void deleteByVipPhone(String vipPhone);

}