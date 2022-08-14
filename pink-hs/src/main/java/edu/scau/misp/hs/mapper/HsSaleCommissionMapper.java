package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsSaleCommission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsSaleCommission)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-01 01:52:03
 */
@Mapper
@Repository
public interface HsSaleCommissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param saleCommissionId 主键
     * @return 实例对象
     */
    HsSaleCommission queryById(Integer saleCommissionId);

    List<HsSaleCommission> queryByStaff(Integer staffId);

    /**
     * 查询指定行数据
     *
     * @param hsSaleCommission 查询条件
     * @return 对象列表
     */
    List<HsSaleCommission> queryAll(HsSaleCommission hsSaleCommission);

    /**
     * 统计总行数
     *
     * @param hsSaleCommission 查询条件
     * @return 总行数
     */
    long count(HsSaleCommission hsSaleCommission);

    /**
     * 新增数据
     *
     * @param hsSaleCommission 实例对象
     * @return 影响行数
     */
    int insert(HsSaleCommission hsSaleCommission);

    /**
     * 修改数据
     *
     * @param hsSaleCommission 实例对象
     * @return 影响行数
     */
    int update(HsSaleCommission hsSaleCommission);

    /**
     * 通过主键删除数据
     *
     * @param saleCommissionId 主键
     * @return 影响行数
     */
    int deleteById(Integer saleCommissionId);

}

