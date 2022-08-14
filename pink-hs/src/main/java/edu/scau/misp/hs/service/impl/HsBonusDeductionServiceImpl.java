package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsBonusDeduction;
import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.mapper.HsBonusDeductionMapper;
import edu.scau.misp.hs.mapper.HsSalaryMapper;
import edu.scau.misp.hs.mapper.HsUserMapper;
import edu.scau.misp.hs.service.HsBonusDeductionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * (HsBonusDeduction)表服务实现类
 *
 * @author makejava
 * @since 2022-05-18 12:40:47
 */
@Service("hsBonusDeductionService")
public class HsBonusDeductionServiceImpl implements HsBonusDeductionService {
    @Resource
    private HsBonusDeductionMapper hsBonusDeductionMapper;
    @Resource
    private HsSalaryMapper hsSalaryMapper;
    @Resource
    private HsUserMapper hsUserMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param bonusDeductionId 主键
     * @return 实例对象
     */
    @Override
    public HsBonusDeduction queryById(Integer bonusDeductionId) {
        return this.hsBonusDeductionMapper.queryById(bonusDeductionId);
    }

    @Override
    public List<HsBonusDeduction> queryAll(HsBonusDeduction hsBonusDeduction) {
        return this.hsBonusDeductionMapper.queryAll(hsBonusDeduction);
    }

    @Override
    public List<HsBonusDeduction> queryBySalaryId(Integer salaryId) {
        return this.hsBonusDeductionMapper.queryBySalaryId(salaryId);
    }

//    /**
//     * 分页查询
//     *
//     * @param hsBonusDeduction 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<HsBonusDeduction> queryByPage(HsBonusDeduction hsBonusDeduction, PageRequest pageRequest) {
//        long total = this.hsBonusDeductionMapper.count(hsBonusDeduction);
//        return new PageImpl<>(this.hsBonusDeductionMapper.queryAllByLimit(hsBonusDeduction, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param hsBonusDeduction 实例对象
     * @return 实例对象
     */
    @Override
    public HsBonusDeduction insert(HsBonusDeduction hsBonusDeduction) {
        this.hsBonusDeductionMapper.insert(hsBonusDeduction);
        return hsBonusDeduction;
    }

    /**
     * 修改数据
     *
     * @param hsBonusDeduction 实例对象
     * @return 实例对象
     */
    @Override
    public HsBonusDeduction update(HsBonusDeduction hsBonusDeduction) {
        this.hsBonusDeductionMapper.update(hsBonusDeduction);
        return this.queryById(hsBonusDeduction.getBonusDeductionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param bonusDeductionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer bonusDeductionId) {
        return this.hsBonusDeductionMapper.deleteById(bonusDeductionId) > 0;
    }

    /**
     * 奖励
     *
     * @param hsBonusDeduction
     * @return hsBonusDeduction
     */
    @Override
    public HsBonusDeduction add(HsBonusDeduction hsBonusDeduction, String username) {
        Long bonus = hsBonusDeduction.getBonus();  //奖励
        BigDecimal m1 = new BigDecimal(String.valueOf((bonus)));
        //经手人
        Integer userId = hsUserMapper.queryByUsername(username).getUserId();
        hsBonusDeduction.setUserId(userId);
        //工资表
        HsSalary hsSalary = hsSalaryMapper.queryById(hsBonusDeduction.getSalaryId());
        BigDecimal m3 = new BigDecimal(String.valueOf((hsSalary.getBonus())));
        BigDecimal wages = new BigDecimal(String.valueOf((hsSalary.getTotalWages())));
        Long t1 = m3.add(m1).longValue();//总奖励
        BigDecimal m33 = new BigDecimal(String.valueOf((t1)));
        Long t3 = wages.add(m33).longValue();//+
        hsSalary.setBonus(t1);
        hsSalary.setTotalWages(t3);
        hsSalaryMapper.update(hsSalary);
        //奖惩表
        hsBonusDeduction.setTime(new Date());
        this.hsBonusDeductionMapper.insert(hsBonusDeduction);
        return hsBonusDeduction;
    }

    /**
     * 扣除
     *
     * @param hsBonusDeduction
     * @return hsBonusDeduction
     */
    @Override
    public HsBonusDeduction sub(HsBonusDeduction hsBonusDeduction, String username) {
        Long deduction = hsBonusDeduction.getDeduction();  //扣除
        BigDecimal m1 = new BigDecimal(String.valueOf((deduction)));
        BigDecimal m2 = new BigDecimal(String.valueOf((0L)));
        Long t1 = m2.subtract(m1).longValue();//变负值
        BigDecimal m3 = new BigDecimal(String.valueOf((t1)));
        //工资表
        HsSalary hsSalary = hsSalaryMapper.queryById(hsBonusDeduction.getSalaryId());
        BigDecimal m4 = new BigDecimal(String.valueOf((hsSalary.getDeduction())));
        BigDecimal wages = new BigDecimal(String.valueOf((hsSalary.getTotalWages())));
        Long t2 = m4.add(m3).longValue();//总扣除
        BigDecimal m44 = new BigDecimal(String.valueOf((t2)));
        Long t4 = wages.add(m44).longValue();//-
        hsSalary.setDeduction(t2);
        hsSalary.setTotalWages(t4);
        hsSalaryMapper.update(hsSalary);
        //奖惩表
        hsBonusDeduction.setTime(new Date());
        //经手人
        Integer userId = hsUserMapper.queryByUsername(username).getUserId();
        hsBonusDeduction.setUserId(userId);
        this.hsBonusDeductionMapper.insert(hsBonusDeduction);
        return hsBonusDeduction;
    }
}
