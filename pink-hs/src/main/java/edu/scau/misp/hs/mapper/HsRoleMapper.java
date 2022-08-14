package edu.scau.misp.hs.mapper;


import edu.scau.misp.hs.entity.HsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-17 17:03:34
 */
@Mapper
@Repository
public interface HsRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    HsRole queryById(Integer roleId);

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    HsRole queryByIdAndAva(Integer roleId);

    HsRole queryByName(String roleName);
    /**
     * 查询指定行数据
     *
     * @param hsRole 查询条件
     * @return 对象列表
     */
    List<HsRole> queryAll(HsRole hsRole);



    /**
     * 新增数据
     *
     * @param hsRole 实例对象
     * @return 影响行数
     */
    int insert(HsRole hsRole);

    /**
     * 修改数据
     *
     * @param hsRole 实例对象
     * @return 影响行数
     */
    int update(HsRole hsRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Integer roleId);

}

