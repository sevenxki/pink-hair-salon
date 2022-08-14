package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsCustomer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsCustomer)表数据库访问层
 *
 *
 */
@Mapper
@Repository
public interface HsCustomerMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param customerId 主键
     * @return 实例对象
     */
    HsCustomer queryById(Integer customerId);

    /**
     * 查询指定行数据
     *
     * @param hsCustomer 查询条件

     * @return 对象列表
     */
    List<HsCustomer> queryAll(HsCustomer hsCustomer);

    /**
     * 统计总行数
     *
     * @param hsCustomer 查询条件
     * @return 总行数
     */
    long count(HsCustomer hsCustomer);

    /**
     * 新增数据
     *
     * @param hsCustomer 实例对象
     * @return 影响行数
     */
    int insert(HsCustomer hsCustomer);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsCustomer> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsCustomer> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsCustomer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsCustomer> entities);

    /**
     * 修改数据
     *
     * @param hsCustomer 实例对象
     * @return 影响行数
     */
    int update(HsCustomer hsCustomer);

    /**
     * 通过主键删除数据
     *
     * @param customerId 主键
     * @return 影响行数
     */
    int deleteById(Integer customerId);

}

