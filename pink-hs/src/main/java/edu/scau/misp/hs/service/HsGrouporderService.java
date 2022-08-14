package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsGrouporder;
import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.vo.DashboardVo;

import java.util.List;

/**
 * (HsGrouporder)表服务接口
 *
 */
public interface HsGrouporderService {

    /**
     * 通过ID查询单条数据
     *
     * @param grouporderId 主键
     * @return 实例对象
     */
    HsGrouporder queryById(Integer grouporderId);

    /**
     * 查询多条数据
     *
     * @param hsGrouporder 筛选条件
     * @return 查询结果
     */
    List<HsGrouporder> queryAll(HsGrouporder hsGrouporder);

    /**
     * 新增数据
     *
     * @param hsGrouporder 实例对象
     * @return 实例对象
     */
    void insert(HsGrouporder hsGrouporder);

    /**
     * 修改数据
     *
     * @param hsGrouporder 实例对象
     * @return 实例对象
     */
    void update(HsGrouporder hsGrouporder);

    /**
     * 通过主键删除数据
     *
     * @param grouporderId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer grouporderId);

    /**
     * 新增vip订单
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    String addNewGroup(HsVip hsVip);


}