package edu.scau.misp.hs.service;
import edu.scau.misp.hs.entity.HsStaff;

import java.util.List;

/**
 * (HsStaff)表服务接口
 *
 * @author makejava
 * @since 2022-03-20 00:19:21
 */
public interface IHsStaffService {

    /**
     * 通过ID查询单条数据
     *
     * @param staffId 主键
     * @return 实例对象
     */
    HsStaff queryById(Integer staffId);

    HsStaff queryByuserName(String userName);

    HsStaff queryBystaffName(String staffName);

    HsStaff queryByNumber(Integer staffNumber);

    HsStaff queryByPhoneNumber(Long phoneNumber);

    /**
     * 查询指定行数据
     *
     * @param hsStaff 筛选条件
     * @return 查询结果
     */
    List<HsStaff> queryAll(HsStaff hsStaff);
    List<HsStaff> queryByRoleId(Integer roleId);
    /**
     * 新增数据
     *
     * @param hsStaff 实例对象
     * @return 实例对象
     */
    void insert(HsStaff hsStaff);

    /**
     * 修改数据
     *
     * @param hsStaff 实例对象
     * @return 实例对象
     */
    void update(HsStaff hsStaff);

    /**
     * 通过主键删除数据
     *
     * @param staffId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer staffId);

}
