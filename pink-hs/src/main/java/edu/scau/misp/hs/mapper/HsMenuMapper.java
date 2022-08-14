package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsMenu)表数据库访问层
 *
 *
 * @since 2022-03-18 21:14:32
 */
@Mapper
@Repository
public interface HsMenuMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    HsMenu queryById(Integer menuId);

    /**
     * 查询指定行数据
     *
     * @param hsMenu 查询条件
     * @return 对象列表
     */
    List<HsMenu> queryAll(HsMenu hsMenu);
    /**
     * 统计总行数
     *
     * @param hsMenu 查询条件
     * @return 总行数
     */
    long count(HsMenu hsMenu);

    /**
     * 新增数据
     *
     * @param hsMenu 实例对象
     * @return 影响行数
     */
    int insert(HsMenu hsMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsMenu> entities);

    /**
     * 修改数据
     *
     * @param hsMenu 实例对象
     * @return 影响行数
     */
    int update(HsMenu hsMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Integer menuId);

}

