package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.entity.HsSalaryCom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (HsSalaryCom)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-08 10:52:39
 */
@Mapper
@Repository
public interface HsSalaryComMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param salaryComId 主键
     * @return 实例对象
     */
    HsSalaryCom queryById(Integer salaryComId);

    /**
     * 查询指定行数据
     *
     * @param hsSalaryCom 查询条件
     * @return 对象列表
     */
    List<HsSalaryCom> queryAll(HsSalaryCom hsSalaryCom);

    List<HsSalaryCom> queryBySalary(Integer salaryId);

    List<HsSalaryCom> queryByCom(Integer saleCommissionId);

    /**
     * 统计总行数
     *
     * @param hsSalaryCom 查询条件
     * @return 总行数
     */
    long count(HsSalaryCom hsSalaryCom);

    /**
     * 新增数据
     *
     * @param hsSalaryCom 实例对象
     * @return 影响行数
     */
    int insert(HsSalaryCom hsSalaryCom);

//    /**
//     * 批量新增数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsSalaryCom> 实例对象列表
//     * @return 影响行数
//     */
//    int insertBatch(@Param("entities") List<HsSalaryCom> entities);
//
//    /**
//     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsSalaryCom> 实例对象列表
//     * @return 影响行数
//     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
//     */
//    int insertOrUpdateBatch(@Param("entities") List<HsSalaryCom> entities);

    /**
     * 修改数据
     *
     * @param hsSalaryCom 实例对象
     * @return 影响行数
     */
    int update(HsSalaryCom hsSalaryCom);

    /**
     * 通过主键删除数据
     *
     * @param salaryComId 主键
     * @return 影响行数
     */
    int deleteById(Integer salaryComId);

}

