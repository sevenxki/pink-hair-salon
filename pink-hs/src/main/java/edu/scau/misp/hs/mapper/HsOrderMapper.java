package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsOrder)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-29 22:17:19
 */
@Mapper
@Repository
public interface HsOrderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param  orderId
     * @return 实例对象
     */
    HsOrder queryById(Integer orderId);

    /**
     * 通过项目名称查询订单
     *
     * @param projectName
     * @return 实例对象
     */
    List<HsOrder> queryByProjectName(String projectName);

    /**
     * 查询指定行数据
     *
     * @param hsOrder 查询条件
     * @return 对象列表
     */
    List<HsOrder> queryAll(HsOrder hsOrder);

    /**
     * 统计总行数
     *
     * @param hsOrder 查询条件
     * @return 总行数
     */
    long count(HsOrder hsOrder);

    /**
     * 新增数据
     *
     * @param hsOrder 实例对象
     * @return 影响行数
     */
    int insert(HsOrder hsOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsOrder> entities);

    /**
     * 修改数据
     *
     * @param hsOrder 实例对象
     * @return 影响行数
     */
    int update(HsOrder hsOrder);

    /**
     * 通过主键删除数据
     *
     * @param  orderId
     * @return 影响行数
     */
    int deleteById(Integer orderId);

    /**
     * 查询未支付订单
     * @param hsOrder
     * @return
     */
    List<HsOrder> queryNoPayment(HsOrder hsOrder);

    /**
     * 查询已支付订单
     * @param
     * @return
     */
    List<HsOrder> queryPayment();
}

