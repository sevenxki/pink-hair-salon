package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.vo.StaffWagesVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

/**
 * (HsSalary)表服务接口
 *
 * @author makejava
 * @since 2022-05-01 02:22:31
 */
public interface HsSalaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param salaryId 主键
     * @return 实例对象
     */
    HsSalary queryById(Integer salaryId);

    List<HsSalary> queryByStaffAndTime(Integer staffId);

//    /**
//     * 分页查询
//     *
//     * @param hsSalary 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    Page<HsSalary> queryByPage(HsSalary hsSalary, PageRequest pageRequest);

    /**
     * 查询多条数据
     *
     * @param hsSalary 筛选条件
     * @return 查询结果
     */
    List<HsSalary> queryAll(HsSalary hsSalary);

    /**
     * 新增数据
     *
     * @param hsSalary 实例对象
     * @return 实例对象
     */
    HsSalary insert(HsSalary hsSalary);

    /**
     * 修改数据
     *
     * @param hsSalary 实例对象
     * @return 实例对象
     */
    HsSalary update(HsSalary hsSalary);

    /**
     * 通过主键删除数据
     *
     * @param salaryId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer salaryId);

    List<StaffWagesVo> moAdd(HsSalary hsSalary);

}
