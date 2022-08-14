package edu.scau.misp.hs.service;


import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.entity.HsProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsProject)表服务接口
 *
 * @author makejava
 * @since 2022-03-16 13:04:41
 */
public interface HsProjectService {

    /**
     * 通过ID查询单条数据
     *
     * @param projectId 主键
     * @return 实例对象
     */
    HsProject queryById(Integer projectId);

    /**
     * 查询多条数据
     *
     * @param hsProject 筛选条件
     * @return 查询结果
     */
    List<HsProject> queryAll(HsProject hsProject);

    /**
     * 新增数据
     *
     * @param hsProject 实例对象
     * @return 实例对象
     */
    HsProject insert(HsProject hsProject);

    /**
     * 修改数据
     *
     * @param hsProject 实例对象
     * @return 实例对象
     */
    HsProject update(HsProject hsProject);

    /**
     * 通过主键删除数据
     *
     * @param projectId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer projectId);

    /**
     * 修改数据
     *
     * @param hsPrice 实例对象
     * @return 实例对象
     */
    List<HsProject> hsPrice(String hsPrice);

}
