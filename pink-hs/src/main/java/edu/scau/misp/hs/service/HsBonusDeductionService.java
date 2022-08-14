package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsBonusDeduction;
import edu.scau.misp.hs.entity.HsDiscount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsBonusDeduction)表服务接口
 *
 * @author makejava
 * @since 2022-05-18 12:40:47
 */
public interface HsBonusDeductionService {

    /**
     * 通过ID查询单条数据
     *
     * @param bonusDeductionId 主键
     * @return 实例对象
     */
    HsBonusDeduction queryById(Integer bonusDeductionId);

    /**
     * 查询多条数据
     *
     * @param hsBonusDeduction 筛选条件
     * @return 查询结果
     */
    List<HsBonusDeduction> queryAll(HsBonusDeduction hsBonusDeduction);

    List<HsBonusDeduction> queryBySalaryId(Integer salaryId);
    /**
     * 新增数据
     *
     * @param hsBonusDeduction 实例对象
     * @return 实例对象
     */
    HsBonusDeduction insert(HsBonusDeduction hsBonusDeduction);

    /**
     * 修改数据
     *
     * @param hsBonusDeduction 实例对象
     * @return 实例对象
     */
    HsBonusDeduction update(HsBonusDeduction hsBonusDeduction);

    /**
     * 通过主键删除数据
     *
     * @param bonusDeductionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer bonusDeductionId);

    HsBonusDeduction add(HsBonusDeduction hsBonusDeduction, String username);

    HsBonusDeduction sub(HsBonusDeduction hsBonusDeduction, String username);
}
