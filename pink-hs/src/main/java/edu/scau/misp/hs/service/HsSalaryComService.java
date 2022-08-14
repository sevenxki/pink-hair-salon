package edu.scau.misp.hs.service;

import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.entity.HsSalaryCom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsSalaryCom)表服务接口
 *
 * @author makejava
 * @since 2022-05-08 10:52:45
 */
public interface HsSalaryComService {

    /**
     * 通过ID查询单条数据
     *
     * @param salaryComId 主键
     * @return 实例对象
     */
    HsSalaryCom queryById(Integer salaryComId);

//    /**
//     * 分页查询
//     *
//     * @param hsSalaryCom 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    Page<HsSalaryCom> queryByPage(HsSalaryCom hsSalaryCom, PageRequest pageRequest);

    /**
     * 查询多条数据
     *
     * @param hsSalaryCom 筛选条件
     * @return 查询结果
     */
    List<HsSalaryCom> queryAll(HsSalaryCom hsSalaryCom);

    List<HsSalaryCom> queryBySalary(Integer salaryId);

    List<HsSalaryCom> queryByCom(Integer saleCommissionId);

    /**
     * 新增数据
     *
     * @param hsSalaryCom 实例对象
     * @return 实例对象
     */
    HsSalaryCom insert(HsSalaryCom hsSalaryCom);

    /**
     * 修改数据
     *
     * @param hsSalaryCom 实例对象
     * @return 实例对象
     */
    HsSalaryCom update(HsSalaryCom hsSalaryCom);

    /**
     * 通过主键删除数据
     *
     * @param salaryComId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer salaryComId);

}
