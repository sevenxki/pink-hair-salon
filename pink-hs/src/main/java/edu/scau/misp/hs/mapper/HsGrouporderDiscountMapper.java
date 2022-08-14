package edu.scau.misp.hs.mapper;


import edu.scau.misp.hs.entity.HsGrouporderDiscount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsGrouporderDiscount)表数据库访问层
 *
 */
@Mapper
@Repository
public interface HsGrouporderDiscountMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param grouporderDiscountId 主键
     * @return 实例对象
     */

    HsGrouporderDiscount queryById(Integer grouporderDiscountId);

    /**
     * 查询指定行数据
     *
     * @param hsGrouporderDiscount 查询条件
     * @return 对象列表
     */
    List<HsGrouporderDiscount> queryAll(HsGrouporderDiscount hsGrouporderDiscount);

    /**
     * 统计总行数
     *
     * @param hsGrouporderDiscount 查询条件
     * @return 总行数
     */
    long count(HsGrouporderDiscount hsGrouporderDiscount);

    /**
     * 新增数据
     *
     * @param hsGrouporderDiscount 实例对象
     * @return 影响行数
     */
    int insert(HsGrouporderDiscount hsGrouporderDiscount);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsGrouporderDiscount> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsGrouporderDiscount> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsGrouporderDiscount> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsGrouporderDiscount> entities);

    /**
     * 修改数据
     *
     * @param hsGrouporderDiscount 实例对象
     * @return 影响行数
     */
    int update(HsGrouporderDiscount hsGrouporderDiscount);

    /**
     * 通过主键删除数据
     *
     * @param grouporderDiscountId 主键
     * @return 影响行数
     */
    int deleteById(Integer grouporderDiscountId);

    /**
     * 找到会员卡余额充值的所有list
     *
     * @param
     * @return 影响行数
     */
    List<HsGrouporderDiscount> queryVipList();

    /**
     * 找到买理发卡的所有list
     *
     * @param
     * @return 影响行数
     */
    List<HsGrouporderDiscount> queryHairCutList();

    /**
     * 找到买护理卡的所有list
     *
     * @param
     * @return 影响行数
     */
    List<HsGrouporderDiscount> queryHairCareList();

}

