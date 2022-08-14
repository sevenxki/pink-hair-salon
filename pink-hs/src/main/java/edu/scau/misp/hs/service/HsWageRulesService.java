package edu.scau.misp.hs.service;
import edu.scau.misp.hs.entity.HsWageRules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsWageRules)表服务接口
 *
 * @author makejava
 * @since 2022-04-29 13:26:24
 */
public interface HsWageRulesService {

    /**
     * 通过ID查询单条数据
     *
     * @param wageRulesId 主键
     * @return 实例对象
     */
    HsWageRules queryById(Integer wageRulesId);

    HsWageRules queryByRoleId(Integer level);

//    /**
//     * 分页查询
//     *
//     * @param hsWageRules 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    Page<HsWageRules> queryByPage(HsWageRules hsWageRules, PageRequest pageRequest);

    /**
     *查询多条数据
     *
     * @param hsWageRules 筛选条件
     * @return 查询结果
     */
    List<HsWageRules> queryAll(HsWageRules hsWageRules);

    /**
     * 新增数据
     *
     * @param hsWageRules 实例对象
     * @return 实例对象
     */
    HsWageRules insert(HsWageRules hsWageRules);

    /**
     * 修改数据
     *
     * @param hsWageRules 实例对象
     * @return 实例对象
     */
    HsWageRules update(HsWageRules hsWageRules);

    /**
     * 通过主键删除数据
     *
     * @param wageRulesId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer wageRulesId);

}
