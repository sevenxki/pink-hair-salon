package edu.scau.misp.hs.service;


import com.alibaba.fastjson.JSONArray;
import edu.scau.misp.hs.entity.HsOrder;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * (HsOrder)表服务接口
 *
 * @author makejava
 * @since 2022-03-29 22:17:24
 */
public interface HsOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param  orderId
     * @return 实例对象
     */
    HsOrder queryById(Integer orderId);

    /**
     * 通过项目名称查询订单
     *
     * @param projectName
     * @return 实例对象
     */
    List<HsOrder> queryByProjectName(String projectName);

    /**
     *查询多条数据
     *
     * @param hsOrder 筛选条件
     * @return 查询结果
     */
    List<HsOrder> queryAll(HsOrder hsOrder);

    /**
     * 新增数据
     *
     * @param hsOrder 实例对象
     * @return 实例对象
     */
    HsOrder insert(HsOrder hsOrder);

    /**
     * 修改数据
     *
     * @param hsOrder 实例对象
     * @return 实例对象
     */
    HsOrder update(HsOrder hsOrder);

    /**
     * 通过主键删除数据
     *
     * @param  orderId
     * @return 是否成功
     */
    boolean deleteById(Integer orderId);

    /**
     *查找项目类别
     * @param
     * @return
     */
    List<String> queryAllKind();

    /**
     * 用项目类型名字查询项目名字
     *
     * @return 查询结果
     */
    List<String> queryAllProjectNameByKind(String kindName);

    /**
     * 用项目类型名字查询项目all
     *
     * @return 查询结果
     */
    List<HsProject> queryAllProjectAllByKind(String kindName);

    /**
     * 查找护理项目name
     *
     * @return 查询结果
     */
    List<String> queryAllHairCare();

    /**
     * 查找护理项目all
     *
     * @return 查询结果
     */
    List<HsProject> queryAllHairCareAll();

    /**
     * 查找技师name
     *
     * @return 查询结果
     */
    List<String> queryTechnician();

    /**
     * 查找技师ALL
     * @param
     * @return
     */
    List<HsStaff> queryTechnicianAll();

    /**
     * 查找对应等级的员工name
     *
     * @return 查询结果
     */
    List<String> queryStaffByLevel(String projectName);

    /**
     * 查找对应等级的员工all
     *
     * @return 查询结果
     */
    List<HsStaff> queryStaffByLevelAll(String projectName);

    /**
     * 用项目名字查询价格
     *
     * @return 查询结果
     * @param order
     */
    String countTotal(@RequestParam("array") JSONArray order);

    void placeOrder(@RequestParam("array") JSONArray placeOrder);

    List<HsOrder> queryNoPayment(HsOrder hsOrder);

}
