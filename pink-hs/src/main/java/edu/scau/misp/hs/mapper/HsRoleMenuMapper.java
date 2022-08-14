package edu.scau.misp.hs.mapper;


import edu.scau.misp.hs.entity.HsRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsRoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-12 15:30:44
 */
@Mapper
@Repository
public interface HsRoleMenuMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param roleMenuId 主键
     * @return 实例对象
     */
    HsRoleMenu queryById(Integer roleMenuId);

    /**
     * 查询指定行数据
     *
     * @param hsRoleMenu 查询条件
     * @return 对象列表
     */
    List<HsRoleMenu> queryAll(HsRoleMenu hsRoleMenu);

    /**
     * 统计总行数
     *
     * @param hsRoleMenu 查询条件
     * @return 总行数
     */
    long count(HsRoleMenu hsRoleMenu);

    /**
     * 新增数据
     *
     * @param hsRoleMenu 实例对象
     * @return 影响行数
     */
    int insert(HsRoleMenu hsRoleMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsRoleMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsRoleMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsRoleMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsRoleMenu> entities);

    /**
     * 修改数据
     *
     * @param hsRoleMenu 实例对象
     * @return 影响行数
     */
    int update(HsRoleMenu hsRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleMenuId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleMenuId);

}

