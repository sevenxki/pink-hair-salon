package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsProject)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-16 13:04:37
 */
@Mapper
@Repository
public interface HsProjectMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param projectId 主键
     * @return 实例对象
     */
    HsProject queryById(Integer projectId);

    /**
     * 查询指定行数据
     *
     * @param hsProject 查询条件
     * @return 对象列表
     */
    List<HsProject> queryAll(HsProject hsProject);
    /**
     * 统计总行数
     *
     * @param hsProject 查询条件
     * @return 总行数
     */
    long count(HsProject hsProject);

    /**
     * 新增数据
     *
     * @param hsProject 实例对象
     * @return 影响行数
     */
    int insert(HsProject hsProject);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsProject> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HsProject> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HsProject> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<HsProject> entities);

    /**
     * 修改数据
     *
     * @param hsProject 实例对象
     * @return 影响行数
     */
    int update(HsProject hsProject);

    /**
     * 通过主键删除数据
     *
     * @param projectId 主键
     * @return 影响行数
     */
    int deleteById(Integer projectId);

    /**
     * 查找价格区间
     *
     * @param hsPrice
     */
    List<HsProject> price(Integer hsPrice);


    /**
     *
     * @param
     * @return
     */
    Long queryPriceByProjectId(Integer projectId);

    /**
     * 用项目类型名字查询项目名字
     *
     * @return 查询结果
     */
    List<String> queryAllProjectNameByKind(String kindName);
    /**
     * 用项目类型名字查询项目all
     *
     * @return 查询结果
     */
    List<HsProject> queryAllProjectAllByKind(String kindName);

    /**
     * 查找护理项目name
     *
     * @return 查询结果
     */
    List<String> queryAllHairCare();

    /**
     * 查找护理项目all
     *
     * @return 查询结果
     */
    List<HsProject> queryAllHairCareAll();

    /**
     * 查找对应等级的员工name
     *
     * @return 查询结果
     */
    List<String> queryStaffByLevel(String projectName);
    /**
     * 查找对应等级的员工all
     *
     * @return 查询结果
     */
    List<HsStaff> queryStaffByLevelAll(String projectName);

    /**
     * 用项目名字查询价格
     *
     * @return 查询结果
     */
    HsProject queryPriceByProjectName(String projectName);

    /**
     * 用项目名字查询Id
     *
     * @return 查询结果
     */
    HsProject queryProjectIdByName(String projectName);

}

