package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.entity.HsSaleCommission;
import edu.scau.misp.hs.mapper.HsSaleCommissionMapper;
import edu.scau.misp.hs.service.HsSaleCommissionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsSaleCommission)表服务实现类
 *
 * @author makejava
 * @since 2022-05-01 01:52:03
 */
@Service("hsSaleCommissionService")
public class HsSaleCommissionServiceImpl implements HsSaleCommissionService {
    @Resource
    private HsSaleCommissionMapper hsSaleCommissionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param saleCommissionId 主键
     * @return 实例对象
     */
    @Override
    public HsSaleCommission queryById(Integer saleCommissionId) {
        return this.hsSaleCommissionMapper.queryById(saleCommissionId);
    }

    @Override
    public List<HsSaleCommission> queryByStaff(Integer staffId) {
        return this.hsSaleCommissionMapper.queryByStaff(staffId);
    }
//
//    /**
//     * 分页查询
//     *
//     * @param hsSaleCommission 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<HsSaleCommission> queryByPage(HsSaleCommission hsSaleCommission, PageRequest pageRequest) {
//        long total = this.hsSaleCommissionMapper.count(hsSaleCommission);
//        return new PageImpl<>(this.hsSaleCommissionMapper.queryAll(hsSaleCommission, pageRequest), pageRequest, total);
//    }

    /**
     * 查询指定行数据
     *
     * @param hsSaleCommission 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsSaleCommission> queryAll(HsSaleCommission hsSaleCommission) {
        return this.hsSaleCommissionMapper.queryAll(hsSaleCommission);
    }

    /**
     * 新增数据
     *
     * @param hsSaleCommission 实例对象
     * @return 实例对象
     */
    @Override
    public HsSaleCommission insert(HsSaleCommission hsSaleCommission) {
        this.hsSaleCommissionMapper.insert(hsSaleCommission);
        return hsSaleCommission;
    }

    /**
     * 修改数据
     *
     * @param hsSaleCommission 实例对象
     * @return 实例对象
     */
    @Override
    public HsSaleCommission update(HsSaleCommission hsSaleCommission) {
        this.hsSaleCommissionMapper.update(hsSaleCommission);
        return this.queryById(hsSaleCommission.getSaleCommissionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param saleCommissionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer saleCommissionId) {
        return this.hsSaleCommissionMapper.deleteById(saleCommissionId) > 0;
    }
}
