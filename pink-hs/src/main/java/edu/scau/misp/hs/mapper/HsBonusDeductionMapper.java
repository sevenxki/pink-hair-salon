package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsBonusDeduction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsBonusDeduction)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-18 12:40:47
 */
@Mapper
@Repository
public interface HsBonusDeductionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param bonusDeductionId 主键
     * @return 实例对象
     */
    HsBonusDeduction queryById(Integer bonusDeductionId);

    /**
     * 查询指定行数据
     *
     * @param hsBonusDeduction 查询条件
     * @return 对象列表
     */
    List<HsBonusDeduction> queryAll(HsBonusDeduction hsBonusDeduction);

    List<HsBonusDeduction> queryBySalaryId(Integer salaryId);
    /**
     * 统计总行数
     *
     * @param hsBonusDeduction 查询条件
     * @return 总行数
     */
    long count(HsBonusDeduction hsBonusDeduction);

    /**
     * 新增数据
     *
     * @param hsBonusDeduction 实例对象
     * @return 影响行数
     */
    int insert(HsBonusDeduction hsBonusDeduction);

    /**
     * 修改数据
     *
     * @param hsBonusDeduction 实例对象
     * @return 影响行数
     */
    int update(HsBonusDeduction hsBonusDeduction);

    /**
     * 通过主键删除数据
     *
     * @param bonusDeductionId 主键
     * @return 影响行数
     */
    int deleteById(Integer bonusDeductionId);

}

