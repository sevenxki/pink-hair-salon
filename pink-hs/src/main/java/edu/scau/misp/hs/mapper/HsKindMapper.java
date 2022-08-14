package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsKind;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (HsKind)表数据库访问层
 *
 */
@Mapper
@Repository
public interface HsKindMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param kindId 主键
     * @return 实例对象
     */
    HsKind queryById(Integer kindId);

    /**
     * 查询指定行数据
     *
     * @param hsKind 查询条件
     * @return 对象列表
     */
    List<HsKind> queryAll(HsKind hsKind);

    /**
     * 统计总行数
     *
     * @param hsKind 查询条件
     * @return 总行数
     */
    long count(HsKind hsKind);

    /**
     * 新增数据
     *
     * @param hsKind 实例对象
     * @return 影响行数
     */
    int insert(HsKind hsKind);

    /**
     * 修改数据
     *
     * @param hsKind 实例对象
     * @return 影响行数
     */
    int update(HsKind hsKind);

    /**
     * 通过主键删除数据
     *
     * @param kindId 主键
     * @return 影响行数
     */
    int deleteById(Integer kindId);


    /**
     * 查询所有类别
     *
     * @param
     * @return 实例对象
     */
    List<String> queryAllKind();
}