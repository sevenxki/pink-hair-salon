package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsVip;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (HsVip)表数据库访问层
 *
 */
@Mapper
@Repository
public interface HsVipMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param vipId 主键
     * @return 实例对象
     */
    HsVip queryById(Integer vipId);

    /**
     * 查询指定行数据
     *
     * @param hsVip 查询条件
     * @return 对象列表
     */
    List<HsVip> queryAll(HsVip hsVip);

    /**
     * 统计总行数
     *
     * @param hsVip 查询条件
     * @return 总行数
     */
    long count(HsVip hsVip);

    /**
     * 新增数据
     *
     * @param hsVip 实例对象
     * @return 影响行数
     */
    int insert(HsVip hsVip);

    /**
     * 修改数据
     *
     * @param hsVip 实例对象
     * @return 影响行数
     */
    int update(HsVip hsVip);

    /**
     * 通过主键删除数据
     *
     * @param vipId 主键
     * @return 影响行数
     */
    int deleteById(Integer vipId);

    /**
     * 通过电话找到all
     *
     * @param vipPhone 实例对象
     * @return 影响行数
     */
    HsVip queryAllByPhone(String vipPhone);

    /**
     * 删除vip
     *
     * @param vipPhone
     * @return 影响行数
     */
    int deleteVip(String vipPhone);
}