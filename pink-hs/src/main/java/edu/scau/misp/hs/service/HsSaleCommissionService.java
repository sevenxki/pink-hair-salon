package edu.scau.misp.hs.service;
import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.entity.HsSaleCommission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HsSaleCommission)表服务接口
 *
 * @author makejava
 * @since 2022-05-01 01:52:03
 */
public interface HsSaleCommissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param saleCommissionId 主键
     * @return 实例对象
     */
    HsSaleCommission queryById(Integer saleCommissionId);

    List<HsSaleCommission> queryByStaff(Integer staffId);

//    /**
//     * 分页查询
//     *
//     * @param hsSaleCommission 筛选条件
//     * @return 查询结果
//     */
//    Page<HsSaleCommission> queryByPage(HsSaleCommission hsSaleCommission);

    /**
     * 查询多条数据
     *
     * @param hsSaleCommission 筛选条件
     * @return 查询结果
     */
    List<HsSaleCommission> queryAll(HsSaleCommission hsSaleCommission);

    /**
     * 新增数据
     *
     * @param hsSaleCommission 实例对象
     * @return 实例对象
     */
    HsSaleCommission insert(HsSaleCommission hsSaleCommission);

    /**
     * 修改数据
     *
     * @param hsSaleCommission 实例对象
     * @return 实例对象
     */
    HsSaleCommission update(HsSaleCommission hsSaleCommission);

    /**
     * 通过主键删除数据
     *
     * @param saleCommissionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer saleCommissionId);

}
