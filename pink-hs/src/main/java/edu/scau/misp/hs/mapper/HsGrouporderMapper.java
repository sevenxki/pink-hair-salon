package edu.scau.misp.hs.mapper;


import edu.scau.misp.hs.entity.HsGrouporder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * (HsGrouporder)表数据库访问层
 *
 */
@Mapper
@Repository
public interface HsGrouporderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param grouporderId 主键
     * @return 实例对象
     */
    HsGrouporder queryById(Integer grouporderId);

    /**
     * 查询指定行数据
     *
     * @param hsGrouporder 查询条件
     * @return 对象列表
     */
    List<HsGrouporder> queryAll(HsGrouporder hsGrouporder);

    /**
     * 统计总行数
     *
     * @param hsGrouporder 查询条件
     * @return 总行数
     */
    long count(HsGrouporder hsGrouporder);

    /**
     * 新增数据
     *
     * @param hsGrouporder 实例对象
     * @return 影响行数
     */
    int insert(HsGrouporder hsGrouporder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsGrouporder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsGrouporder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsGrouporder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsGrouporder> entities);

    /**
     * 修改数据
     *
     * @param hsGrouporder 实例对象
     * @return 影响行数
     */
    int update(HsGrouporder hsGrouporder);

    /**
     * 通过主键删除数据
     *
     * @param grouporderId 主键
     * @return 影响行数
     */
    int deleteById(Integer grouporderId);

    /**
     * 通过NO查询单条数据
     *
     * @param grouporderNo
     * @return 实例对象
     */
    HsGrouporder queryByNO(String grouporderNo);

    /**
     * between
     *
     * @param
     * @return 实例对象
     */
    List<HsGrouporder> queryTime(Date a1, Date a2);
}

