package edu.scau.misp.hs.mapper;


import edu.scau.misp.hs.entity.HsDiscount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsDiscount)表数据库访问层
 *
 */
@Mapper
@Repository
public interface HsDiscountMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param discountId 主键
     * @return 实例对象
     */
    HsDiscount queryById(Integer discountId);

    /**
     * 查询指定行数据
     *
     * @param hsDiscount 查询条件
     * @return 对象列表
     */
    List<HsDiscount> queryAll(HsDiscount hsDiscount);

    /**
     * 统计总行数
     *
     * @param hsDiscount 查询条件
     * @return 总行数
     */
    long count(HsDiscount hsDiscount);

    /**
     * 新增数据
     *
     * @param hsDiscount 实例对象
     * @return 影响行数
     */
    int insert(HsDiscount hsDiscount);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsDiscount> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsDiscount> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsDiscount> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsDiscount> entities);

    /**
     * 修改数据
     *
     * @param hsDiscount 实例对象
     * @return 影响行数
     */
    int update(HsDiscount hsDiscount);

    /**
     * 通过主键删除数据
     *
     * @param discountId 主键
     * @return 影响行数
     */
    int deleteById(Integer discountId);

}

