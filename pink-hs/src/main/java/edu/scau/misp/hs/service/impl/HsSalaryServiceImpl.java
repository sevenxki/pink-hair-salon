package edu.scau.misp.hs.service.impl;
import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.hs.service.HsSalaryService;
import edu.scau.misp.hs.util.ServiceException;
import edu.scau.misp.hs.vo.StaffWagesVo;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (HsSalary)表服务实现类
 *
 * @author makejava
 * @since 2022-05-01 02:22:31
 */
@Service("hsSalaryService")
public class HsSalaryServiceImpl implements HsSalaryService {
    @Resource
    private HsSalaryMapper hsSalaryMapper;
    @Resource
    private HsSaleCommissionMapper hsSaleCommissionMapper;
    @Resource
    private HsSalaryComMapper hsSalaryComMapper;
    @Resource
    private HsStaffMapper hsStaffMapper;
    @Resource
    private HsWageRulesMapper hsWageRulesMapper;
    @Resource
    private HsBonusDeductionMapper hsBonusDeductionMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param salaryId 主键
     * @return 实例对象
     */
    @Override
    public HsSalary queryById(Integer salaryId) {
        return this.hsSalaryMapper.queryById(salaryId);
    }

    @Override
    public List<HsSalary> queryByStaffAndTime(Integer staffId) {
        return this.hsSalaryMapper.queryByStaffAndTime(staffId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsSalary 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsSalary> queryAll(HsSalary hsSalary) {
        return this.hsSalaryMapper.queryAll(hsSalary);
    }

    /**
     * 新增数据
     *
     * @param hsSalary 实例对象
     * @return 实例对象
     */
    @Override
    public HsSalary insert(HsSalary hsSalary) {
        hsSalary.setBonus(0L);
        hsSalary.setDeduction(0L);
        this.hsSalaryMapper.insert(hsSalary);
        return hsSalary;
    }

    /**
     * 修改数据
     *
     * @param hsSalary 实例对象
     * @return 实例对象
     */
    @Override
    public HsSalary update(HsSalary hsSalary) {
        this.hsSalaryMapper.update(hsSalary);
        return this.queryById(hsSalary.getSalaryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param salaryId 主键
     * @return 是否成功
     */
    boolean a,b,c,d;
    @Override
    public boolean deleteById(Integer salaryId) {
        List<HsSalaryCom> hsSalaryComList = hsSalaryComMapper.queryBySalary(salaryId);
        List<HsBonusDeduction> hsBonusDeductionList = hsBonusDeductionMapper.queryBySalaryId(salaryId);
        if (hsSalaryComList != null && !hsSalaryComList.isEmpty()){
                for (HsSalaryCom hsSalaryCom : hsSalaryComList){
                    b = hsSaleCommissionMapper.deleteById(hsSalaryCom.getSaleCommissionId())>0;
                    if (b){
                        a = hsSalaryComMapper.deleteById(hsSalaryCom.getSalaryComId())>0;
                        if (a){
                            if (hsBonusDeductionList != null && !hsBonusDeductionList.isEmpty()){
                                for (HsBonusDeduction hsBonusDeduction : hsBonusDeductionList){
                                    c =hsBonusDeductionMapper.deleteById(hsBonusDeduction.getBonusDeductionId())>0;
                                }
                            }
                        }
                    }
                }
        }
        d = hsSalaryMapper.deleteById(salaryId) > 0;
        return c;
    }

    /**
     * 分页查询  本月的
     *
     * @param hsSalary 筛选条件
     * @return 查询结果
     */
    public List<StaffWagesVo> moAdd(HsSalary hsSalary){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String s = formatter.format(date);   //当前日期
        List<HsSalary> hsSalarys = new ArrayList<>();
        List<HsStaff> hsStaffList = hsStaffMapper.queryAll(new HsStaff());
        List<StaffWagesVo> staffWagesVos = new ArrayList<>();
        for (HsStaff hsStaff : hsStaffList){
//            HsSalary hs = new HsSalary();
            hsSalary.setStaffId(hsStaff.getStaffId());//通过员工号
            hsSalary.setPayTime(s);//通过时间查询
            List<HsSalary> hsSalaries = hsSalaryMapper.queryAll(hsSalary);
            if (hsSalaries!=null && !hsSalaries.isEmpty()) {  //有订单的
                for (HsSalary hsSalary1 : hsSalaries) {
                    StaffWagesVo staffWagesVo = new StaffWagesVo();
                    staffWagesVo.setHsSalary(hsSalary1);
                    staffWagesVo.setDeduction(0L);
                    staffWagesVo.setBonus(0L);
                    staffWagesVo.setStaffName(hsSalary1.getHsStaff().getStaffName());
                    staffWagesVo.setStaffNumber(hsSalary1.getHsStaff().getStaffNumber());
                    staffWagesVo.setSettleStatus(Integer.parseInt(hsSalary1.getStatus()));
                    staffWagesVo.setYear(hsSalary1.getPayTime().substring(0, hsSalary1.getPayTime().indexOf("-")));
                    staffWagesVo.setMonth(hsSalary1.getPayTime().substring(5, 7));
                    staffWagesVos.add(staffWagesVo);
//                    hsSalary1.setStaffId(hsStaff.getStaffId());
//                    hsSalary1.setHsStaff(hsStaffService.queryById(hsSalary1.getStaffId()));
//                    hsSalary1.setHsWageRules(hsWageRulesService.queryById(hsSalary1.getWageRulesId()));
//                    hsSalarys.add(hsSalary1);
                }
            }else {  //本月一个订单都没有的
                System.out.println("来过了");
                HsSalary hsSalary1 = new HsSalary();
                HsWageRules hsWageRules = new HsWageRules();
                if (Integer.parseInt(hsStaff.getLevel())==4){//收银员
                    hsWageRules.setLevel(6);
                    hsWageRules.setRoleId(hsStaff.getRoleId());
                    List<HsWageRules> h = hsWageRulesMapper.queryAll(hsWageRules);
                    for (HsWageRules h1 : h){
                        hsSalary1.setHsWageRules(h1);
                        hsSalary1.setWageRulesId(h1.getWageRulesId());
                        hsSalary1.setTotalWages(h1.getBasicWage());
                        hsSalary1.setStaffId(hsStaff.getStaffId());
                        hsSalary1.setHsStaff(hsStaffMapper.queryById(hsSalary1.getStaffId()));
                        hsSalary1.setStatus("0");
                        hsSalary1.setTotalCommission(0L);
                        hsSalary1.setDeduction(0L);
                        hsSalary1.setBonus(0L);
                        hsSalary1.setPayTime(s);
                        hsSalarys.add(hsSalary1);
                        hsSalaryMapper.insert(hsSalary1);
                        StaffWagesVo staffWagesVo = new StaffWagesVo();
                        staffWagesVo.setHsSalary(hsSalary1);
                        staffWagesVo.setDeduction(0L);
                        staffWagesVo.setBonus(0L);
                        staffWagesVo.setStaffName(hsSalary1.getHsStaff().getStaffName());
                        staffWagesVo.setStaffNumber(hsSalary1.getHsStaff().getStaffNumber());
                        staffWagesVo.setYear(hsSalary1.getPayTime().substring(0, hsSalary1.getPayTime().indexOf("-")));
                        staffWagesVo.setMonth(hsSalary1.getPayTime().substring(5, 7));
                        staffWagesVos.add(staffWagesVo);
                    }
                }else if (Integer.parseInt(hsStaff.getLevel())==5){
                    System.out.println("来过了");
                    hsWageRules.setLevel(0);
                    hsWageRules.setRoleId(hsStaff.getRoleId());
                    List<HsWageRules> h = hsWageRulesMapper.queryAll(hsWageRules);
                    for (HsWageRules h1 : h){
                        hsSalary1.setHsWageRules(h1);
                        hsSalary1.setWageRulesId(h1.getWageRulesId());
                        hsSalary1.setTotalWages(h1.getBasicWage());
                        hsSalary1.setStaffId(hsStaff.getStaffId());
                        hsSalary1.setHsStaff(hsStaffMapper.queryById(hsSalary1.getStaffId()));
                        hsSalary1.setStatus("0");
                        hsSalary1.setTotalCommission(0L);
                        hsSalary1.setDeduction(0L);
                        hsSalary1.setBonus(0L);
                        hsSalary1.setPayTime(s);
                        hsSalarys.add(hsSalary1);
                        hsSalaryMapper.insert(hsSalary1);
                        StaffWagesVo staffWagesVo = new StaffWagesVo();
                        staffWagesVo.setHsSalary(hsSalary1);
                        staffWagesVo.setDeduction(0L);
                        staffWagesVo.setBonus(0L);
                        staffWagesVo.setStaffName(hsSalary1.getHsStaff().getStaffName());
                        staffWagesVo.setStaffNumber(hsSalary1.getHsStaff().getStaffNumber());
                        staffWagesVo.setYear(hsSalary1.getPayTime().substring(0, hsSalary1.getPayTime().indexOf("-")));
                        staffWagesVo.setMonth(hsSalary1.getPayTime().substring(5, 7));
                        staffWagesVos.add(staffWagesVo);
                    }
                }else if (Integer.parseInt(hsStaff.getLevel())==0){
                    System.out.println("lili");
                    hsWageRules.setLevel(4);
                    hsWageRules.setRoleId(hsStaff.getRoleId());
                    List<HsWageRules> h = hsWageRulesMapper.queryAll(hsWageRules);
                    for (HsWageRules h1 : h){
                        hsSalary1.setHsWageRules(h1);
                        hsSalary1.setWageRulesId(h1.getWageRulesId());
                        hsSalary1.setTotalWages(h1.getBasicWage());
                        hsSalary1.setStaffId(hsStaff.getStaffId());
                        hsSalary1.setHsStaff(hsStaffMapper.queryById(hsSalary1.getStaffId()));
                        hsSalary1.setStatus("0");
                        hsSalary1.setTotalCommission(0L);
                        hsSalary1.setDeduction(0L);
                        hsSalary1.setBonus(0L);
                        hsSalary1.setPayTime(s);
                        hsSalarys.add(hsSalary1);
                        hsSalaryMapper.insert(hsSalary1);
                        StaffWagesVo staffWagesVo = new StaffWagesVo();
                        staffWagesVo.setHsSalary(hsSalary1);
                        staffWagesVo.setDeduction(0L);
                        staffWagesVo.setBonus(0L);
                        staffWagesVo.setStaffName(hsSalary1.getHsStaff().getStaffName());
                        staffWagesVo.setStaffNumber(hsSalary1.getHsStaff().getStaffNumber());
                        staffWagesVo.setYear(hsSalary1.getPayTime().substring(0, hsSalary1.getPayTime().indexOf("-")));
                        staffWagesVo.setMonth(hsSalary1.getPayTime().substring(5, 7));
                        staffWagesVos.add(staffWagesVo);
                    }
                }else{
                    hsWageRules.setLevel(Integer.parseInt(hsStaff.getLevel()));
                    hsWageRules.setRoleId(hsStaff.getRoleId());
                    List<HsWageRules> h = hsWageRulesMapper.queryAll(hsWageRules);
                    for (HsWageRules h1 : h) {
                        hsSalary1.setHsWageRules(h1);
                        hsSalary1.setWageRulesId(h1.getWageRulesId());
                        hsSalary1.setTotalWages(h1.getBasicWage());
                        hsSalary1.setStaffId(hsStaff.getStaffId());
                        hsSalary1.setHsStaff(hsStaffMapper.queryById(hsSalary1.getStaffId()));
                        hsSalary1.setStatus("0");
                        hsSalary1.setTotalCommission(0L);
                        hsSalary1.setDeduction(0L);
                        hsSalary1.setBonus(0L);
                        hsSalary1.setPayTime(s);
                        hsSalarys.add(hsSalary1);
                        hsSalaryMapper.insert(hsSalary1);
                        StaffWagesVo staffWagesVo = new StaffWagesVo();
                        staffWagesVo.setHsSalary(hsSalary1);
                        staffWagesVo.setDeduction(0L);
                        staffWagesVo.setBonus(0L);
                        staffWagesVo.setStaffName(hsSalary1.getHsStaff().getStaffName());
                        staffWagesVo.setStaffNumber(hsSalary1.getHsStaff().getStaffNumber());
                        staffWagesVo.setYear(hsSalary1.getPayTime().substring(0, hsSalary1.getPayTime().indexOf("-")));
                        staffWagesVo.setMonth(hsSalary1.getPayTime().substring(5, 7));
                        staffWagesVos.add(staffWagesVo);
                    }
                }
            }
        }
        return staffWagesVos;
    }
}
