package edu.scau.misp.hs.service;


import edu.scau.misp.hs.entity.HsRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsRole)表服务接口
 *
 * @author makejava
 * @since 2022-03-17 17:03:38
 */
public interface HsRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    HsRole queryById(Integer roleId);
    HsRole queryByName(String roleName);
    /**
     * 查询多条数据
     *
     * @param hsRole 筛选条件
     * @return 查询结果
     */
    List<HsRole> queryAll(HsRole hsRole);

    /**
     * 新增数据
     *
     * @param hsRole 实例对象
     * @return 实例对象
     */
    HsRole insert(HsRole hsRole);

    /**
     * 修改数据
     *
     * @param hsRole 实例对象
     * @return 实例对象
     */
    HsRole update(HsRole hsRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);

}
