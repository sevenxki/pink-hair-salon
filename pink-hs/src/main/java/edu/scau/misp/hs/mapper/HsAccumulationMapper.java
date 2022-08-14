package edu.scau.misp.hs.mapper;


import edu.scau.misp.hs.entity.HsAccumulation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsAccumulation)表数据库访问层
 *
 */
@Mapper
@Repository
public interface HsAccumulationMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param accumulationId 主键
     * @return 实例对象
     */
    HsAccumulation queryById(Integer accumulationId);

    /**
     * 查询指定行数据
     *
     * @param hsAccumulation 查询条件
     * @return 对象列表
     */
    List<HsAccumulation> queryAll(HsAccumulation hsAccumulation);

    /**
     * 统计总行数
     *
     * @param hsAccumulation 查询条件
     * @return 总行数
     */
    long count(HsAccumulation hsAccumulation);

    /**
     * 新增数据
     *
     * @param hsAccumulation 实例对象
     * @return 影响行数
     */
    int insert(HsAccumulation hsAccumulation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsAccumulation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsAccumulation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsAccumulation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsAccumulation> entities);

    /**
     * 修改数据
     *
     * @param hsAccumulation 实例对象
     * @return 影响行数
     */
    int update(HsAccumulation hsAccumulation);

    /**
     * 通过主键删除数据
     *
     * @param accumulationId 主键
     * @return 影响行数
     */
    int deleteById(Integer accumulationId);

    /**
     * 通过VipID查询单条数据
     *
     * @param vipId
     * @return 实例对象
     */
    HsAccumulation queryByVipId(Integer vipId);
}

