package edu.scau.misp.hs.mapper;
import edu.scau.misp.hs.entity.HsWageRules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsWageRules)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-29 13:26:19
 */
@Mapper
@Repository
public interface HsWageRulesMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param wageRulesId 主键
     * @return 实例对象
     */
    HsWageRules queryById(Integer wageRulesId);

    HsWageRules queryByRoleId(Integer level);

    /**
     * 查询指定行数据
     *
     * @param hsWageRules 查询条件
     * @return 对象列表
     */
    List<HsWageRules> queryAll(HsWageRules hsWageRules);

    /**
     * 统计总行数
     *
     * @param hsWageRules 查询条件
     * @return 总行数
     */
    long count(HsWageRules hsWageRules);

    /**
     * 新增数据
     *
     * @param hsWageRules 实例对象
     * @return 影响行数
     */
    int insert(HsWageRules hsWageRules);

//    /**
//     * 批量新增数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsWageRules> 实例对象列表
//     * @return 影响行数
//     */
//    int insertBatch(@Param("entities") List<HsWageRules> entities);
//
//    /**
//     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsWageRules> 实例对象列表
//     * @return 影响行数
//     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
//     */
//    int insertOrUpdateBatch(@Param("entities") List<HsWageRules> entities);

    /**
     * 修改数据
     *
     * @param hsWageRules 实例对象
     * @return 影响行数
     */
    int update(HsWageRules hsWageRules);

    /**
     * 通过主键删除数据
     *
     * @param wageRulesId 主键
     * @return 影响行数
     */
    int deleteById(Integer wageRulesId);

}

