package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-17 15:21:49
 */
@Mapper
@Repository
public interface HsUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    HsUser queryById(Integer userId);

    /**
     * 统计总行数
     *
     * @param hsUser 查询条件
     * @return 总行数
     */
    long count(HsUser hsUser);

    /**
     * 新增数据
     *
     * @param hsUser 实例对象
     * @return 影响行数
     */
    int insert(HsUser hsUser);

//    /**
//     * 批量新增数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsUser> 实例对象列表
//     * @return 影响行数
//     */
//    int insertBatch(@Param("entities") List<HsUser> entities);
//
//    /**
//     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsUser> 实例对象列表
//     * @return 影响行数
//     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
//     */
//    int insertOrUpdateBatch(@Param("entities") List<HsUser> entities);

    /**
     * 修改数据
     *
     * @param hsUser 实例对象
     * @return 影响行数
     */
    int update(HsUser hsUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

    /**
     * 登录：根据用户名查用户
     * @param username
     * @return
     */
    HsUser queryByUsername(String username);

    /**
     * 查询指定行数据
     *
     * @param hsUser 查询条件
     * @return 对象列表
     */
    List<HsUser> queryAll(HsUser hsUser);

}

