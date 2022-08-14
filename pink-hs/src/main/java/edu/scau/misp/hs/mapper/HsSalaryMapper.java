package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.vo.StaffWagesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * (HsSalary)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-01 02:22:31
 */
@Mapper
@Repository
public interface HsSalaryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param salaryId 主键
     * @return 实例对象
     */
    HsSalary queryById(Integer salaryId);

    List<HsSalary> queryByStaffAndTime(Integer staffId);

    /**
     * 查询指定行数据
     *
     * @param hsSalary 查询条件
     * @return 对象列表
     */
    List<HsSalary> queryAll(HsSalary hsSalary);
    /**
     * 统计总行数
     *
     * @param hsSalary 查询条件
     * @return 总行数
     */
    long count(HsSalary hsSalary);

    /**
     * 新增数据
     *
     * @param hsSalary 实例对象
     * @return 影响行数
     */
    int insert(HsSalary hsSalary);

    /**
     * 修改数据
     *
     * @param hsSalary 实例对象
     * @return 影响行数
     */
    int update(HsSalary hsSalary);

    /**
     * 通过主键删除数据
     *
     * @param salaryId 主键
     * @return 影响行数
     */
    int deleteById(Integer salaryId);

}

