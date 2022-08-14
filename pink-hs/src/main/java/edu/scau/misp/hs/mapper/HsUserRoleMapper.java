package edu.scau.misp.hs.mapper;


import edu.scau.misp.hs.entity.HsUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-20 02:13:47
 */
@Mapper
@Repository
public interface HsUserRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param userRoleId 主键
     * @return 实例对象
     */
    HsUserRole queryById(Integer userRoleId);

    /**
     * 查询指定行数据
     *
     * @param hsUserRole 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<HsUserRole> queryAll(HsUserRole hsUserRole, @Param("pageable") Pageable pageable);

    /**
     * 查询指定行数据
     *
     * @param hsUserRole 查询条件
     * @return 对象列表
     */
    List<HsUserRole> queryAll(HsUserRole hsUserRole);

    /**
     * 统计总行数
     *
     * @param hsUserRole 查询条件
     * @return 总行数
     */
    long count(HsUserRole hsUserRole);

    /**
     * 新增数据
     *
     * @param hsUserRole 实例对象
     * @return 影响行数
     */
    int insert(HsUserRole hsUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsUserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsUserRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsUserRole> entities);

    /**
     * 修改数据
     *
     * @param hsUserRole 实例对象
     * @return 影响行数
     */
    int update(HsUserRole hsUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userRoleId 主键
     * @return 影响行数
     */
    int deleteById(Integer userRoleId);

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    HsUserRole queryByUserId(Integer userId);

    List<HsUserRole> queryByRoleId(Integer roleId);
}

