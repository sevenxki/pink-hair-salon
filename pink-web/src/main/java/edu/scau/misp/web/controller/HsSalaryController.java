package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.entity.HsStaff;
import edu.scau.misp.hs.entity.HsWageRules;
import edu.scau.misp.hs.mapper.HsStaffMapper;
import edu.scau.misp.hs.service.HsSalaryService;
import edu.scau.misp.hs.service.HsWageRulesService;
import edu.scau.misp.hs.service.IHsStaffService;
import edu.scau.misp.hs.vo.StaffWagesVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (HsSalary)表控制层
 *
 * @author makejava
 * @since 2022-05-01 02:22:31
 */
@RestController
@RequestMapping("hsSalary")
public class HsSalaryController {
    /**
     * 服务对象
     */
    @Resource
    private HsSalaryService hsSalaryService;
    @Resource
    private HsWageRulesService hsWageRulesService;
    @Resource
    private IHsStaffService hsStaffService;

    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsSalary 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsSalary hsSalary) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsSalary> list = hsSalaryService.queryAll(hsSalary);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 分页查询list
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param swv 筛选条件
     * @return 查询结果
     */
    @GetMapping("/swv")
    public PageInfo swv(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, StaffWagesVo swv) {
        PageHelper.startPage(pageNum,pageSize);
        HsStaff hs = new HsStaff();
        hs.setStaffName(swv.getStaffName());
        hs.setStaffNumber(swv.getStaffNumber());
        List<HsStaff> hsStaff = hsStaffService.queryAll(hs);
        List<StaffWagesVo> staffWagesVos = new ArrayList<>();
        for (HsStaff hsStaff1 : hsStaff){
            HsSalary hsS = new HsSalary();
            hsS.setStaffId(hsStaff1.getStaffId());
            List<HsSalary> list = hsSalaryService.queryAll(hsS);
            for (HsSalary hsSalary : list){
                StaffWagesVo staffWagesVo = new StaffWagesVo();
                staffWagesVo.setHsSalary(hsSalary);
//            staffWagesVo.setStaffId(hsSalary.getStaffId());
                staffWagesVo.setStaffName(hsSalary.getHsStaff().getStaffName());
                staffWagesVo.setStaffNumber(hsSalary.getHsStaff().getStaffNumber());
//            staffWagesVo.setBasicWage(hsWageRulesService.queryByRoleId(Integer.parseInt(hsSalary.getHsStaff().getLevel())).getBasicWage());
                staffWagesVo.setSettleStatus(Integer.parseInt(hsSalary.getStatus()));
//            staffWagesVo.setTotalCommission(hsSalary.getTotalCommission());
//            staffWagesVo.setTotalWages(hsSalary.getTotalWages());
                staffWagesVo.setYear(hsSalary.getPayTime().substring(0, hsSalary.getPayTime().indexOf("-")));
                staffWagesVo.setMonth(hsSalary.getPayTime().substring(5,7));
//            staffWagesVo.setGender(hsSalary.getHsStaff().getGender());
//            staffWagesVo.setRoleId(hsSalary.getHsStaff().getRoleId());
                staffWagesVos.add(staffWagesVo);
            }
        }
        PageInfo<List> pageInfo = new PageInfo(staffWagesVos);
        return pageInfo;
    }


    /**
     * 查询列表
     *
     * @param swv 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<StaffWagesVo> queryAll(StaffWagesVo swv) {
//        List<HsSalary> list = hsSalaryService.queryAll(hsSalary);
        List<StaffWagesVo> staffWagesVos = new ArrayList<>();
        HsStaff hs = new HsStaff();
        hs.setUsername(swv.getStaffName());
        hs.setStaffNumber(swv.getStaffNumber());
        List<HsStaff> hsStaff = hsStaffService.queryAll(hs);
        for (HsStaff hsStaff1 : hsStaff) {
            HsSalary hsS = new HsSalary();
            hsS.setStaffId(hsStaff1.getStaffId());
            List<HsSalary> list = hsSalaryService.queryAll(hsS);
            for (HsSalary hsSalary1 : list) {
                StaffWagesVo staffWagesVo = new StaffWagesVo();
                staffWagesVo.setHsSalary(hsSalary1);
//            HsWageRules hsWageRules = new HsWageRules();
//            hsWageRules.setLevel(Integer.parseInt(hsSalary.getHsStaff().getLevel()));
//            hsWageRules.setRoleId(hsSalary.getHsStaff().getRoleId());
//            List<HsWageRules> h = hsWageRulesService.queryAll(hsWageRules);
//            for (HsWageRules h1 : h) {
//                staffWagesVo.setStaffId(hsSalary.getStaffId());
                staffWagesVo.setStaffName(hsSalary1.getHsStaff().getStaffName());
                staffWagesVo.setStaffNumber(hsSalary1.getHsStaff().getStaffNumber());
                staffWagesVo.setSettleStatus(Integer.parseInt(hsSalary1.getStatus()));
////                staffWagesVo.setBasicWage(h1.getBasicWage());
//                staffWagesVo.setSettleStatus(Integer.parseInt(hsSalary.getStatus()));
//                staffWagesVo.setTotalCommission(hsSalary.getTotalCommission());
//                staffWagesVo.setTotalWages(hsSalary.getTotalWages());
                staffWagesVo.setYear(hsSalary1.getPayTime().substring(0, hsSalary1.getPayTime().indexOf("-")));
                staffWagesVo.setMonth(hsSalary1.getPayTime().substring(5, 7));
//                staffWagesVo.setGender(hsSalary.getHsStaff().getGender());
//                staffWagesVo.setRoleId(hsSalary.getHsStaff().getRoleId());
                staffWagesVos.add(staffWagesVo);
//            }
            }
        }
        return staffWagesVos ;
    }

    /**
     * 分页查询  本月的
     *
     * @param hsSalary 筛选条件
     * @return 查询结果
     */
    @GetMapping("/month")
    //    public List<StaffWagesVo> queryMonth(HsSalary hsSalary) {
    public PageInfo queryMonth(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsSalary hsSalary) {
        PageHelper.startPage(pageNum,pageSize);
        List<StaffWagesVo> staffWagesVos = hsSalaryService.moAdd(hsSalary);
        PageInfo<List> pageInfo = new PageInfo(staffWagesVos);
        return pageInfo;
//        return  staffWagesVos;
    }

    /**
     * 发放工资的操作，修改工资发放的状态
     *
     * @param salaryId 筛选条件
     * @return 查询结果
     */
    @GetMapping("/settle/{salaryId}")
        public StaffWagesVo settle(@PathVariable("salaryId") Integer salaryId) {
        HsSalary hsSalary = hsSalaryService.queryById(salaryId);
        System.out.println(hsSalary.getStatus());
        hsSalary.setStatus("1");
        hsSalary.setSettleTime(new Date());
        hsSalaryService.update(hsSalary);
        StaffWagesVo staffWagesVo = new StaffWagesVo();
        staffWagesVo.setHsSalary(hsSalary);
        staffWagesVo.setStaffName(hsSalary.getHsStaff().getStaffName());
        staffWagesVo.setStaffNumber(hsSalary.getHsStaff().getStaffNumber());
        staffWagesVo.setSettleStatus(Integer.parseInt(hsSalary.getStatus()));
        staffWagesVo.setYear(hsSalary.getPayTime().substring(0, hsSalary.getPayTime().indexOf("-")));
        staffWagesVo.setMonth(hsSalary.getPayTime().substring(5, 7));
        return staffWagesVo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param salaryId 主键
     * @return 单条数据
     */
    @GetMapping("{salaryId}")
    public HsSalary queryById(@PathVariable("salaryId") Integer salaryId) {

        return this.hsSalaryService.queryById(salaryId);
    }

    /**
     * 新增数据
     *
     * @param hsSalary 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsSalary hsSalary) {
       this.hsSalaryService.insert(hsSalary);
        return "ok";
    }

    /**
     * 编辑数据
     *
     * @param hsSalary 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsSalary hsSalary) {
        this.hsSalaryService.update(hsSalary);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param salaryId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{salaryId}")
    public Boolean deleteById(@PathVariable Integer salaryId) {
        return this.hsSalaryService.deleteById(salaryId);
    }

}

