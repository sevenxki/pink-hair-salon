package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.entity.HsSalaryCom;
import edu.scau.misp.hs.mapper.HsSalaryComMapper;
import edu.scau.misp.hs.service.HsSalaryComService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsSalaryCom)表服务实现类
 *
 * @author makejava
 * @since 2022-05-08 10:52:47
 */
@Service("hsSalaryComService")
public class HsSalaryComServiceImpl implements HsSalaryComService {
    @Resource
    private HsSalaryComMapper hsSalaryComMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param salaryComId 主键
     * @return 实例对象
     */
    @Override
    public HsSalaryCom queryById(Integer salaryComId) {
        return this.hsSalaryComMapper.queryById(salaryComId);
    }

//    /**
//     * 分页查询
//     *
//     * @param hsSalaryCom 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<HsSalaryCom> queryByPage(HsSalaryCom hsSalaryCom, PageRequest pageRequest) {
//        long total = this.hsSalaryComMapper.count(hsSalaryCom);
//        return new PageImpl<>(this.hsSalaryComMapper.queryAllByLimit(hsSalaryCom, pageRequest), pageRequest, total);
//    }

    /**
     * 查询指定行数据
     *
     * @param hsSalaryCom 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsSalaryCom> queryAll(HsSalaryCom hsSalaryCom) {
        return this.hsSalaryComMapper.queryAll(hsSalaryCom);
    }

    @Override
    public List<HsSalaryCom> queryBySalary(Integer salaryId) {
        return this.hsSalaryComMapper.queryBySalary(salaryId);
    }

    @Override
    public List<HsSalaryCom> queryByCom(Integer saleCommissionId) {
        return this.hsSalaryComMapper.queryByCom(saleCommissionId);
    }

    /**
     * 新增数据
     *
     * @param hsSalaryCom 实例对象
     * @return 实例对象
     */
    @Override
    public HsSalaryCom insert(HsSalaryCom hsSalaryCom) {
        this.hsSalaryComMapper.insert(hsSalaryCom);
        return hsSalaryCom;
    }

    /**
     * 修改数据
     *
     * @param hsSalaryCom 实例对象
     * @return 实例对象
     */
    @Override
    public HsSalaryCom update(HsSalaryCom hsSalaryCom) {
        this.hsSalaryComMapper.update(hsSalaryCom);
        return this.queryById(hsSalaryCom.getSalaryComId());
    }

    /**
     * 通过主键删除数据
     *
     * @param salaryComId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer salaryComId) {
        return this.hsSalaryComMapper.deleteById(salaryComId) > 0;
    }
}
