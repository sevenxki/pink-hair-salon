package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsBonusDeduction;
import edu.scau.misp.hs.entity.HsUser;
import edu.scau.misp.hs.service.HsBonusDeductionService;
import edu.scau.misp.hs.service.HsSalaryService;
import edu.scau.misp.hs.vo.BonusVO;
import edu.scau.misp.sys.security.config.service.HsUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (HsBonusDeduction)表控制层
 *
 * @author makejava
 * @since 2022-05-18 12:40:46
 */
@RestController
@RequestMapping("hsBonusDeduction")
public class HsBonusDeductionController {
    /**
     * 服务对象
     */
    @Resource
    private HsBonusDeductionService hsBonusDeductionService;
    @Resource
    private HsSalaryService hsSalaryService;
    @Resource
    private HsUserService hsUserService;

    /**
     * 分页查询
     *
     * @param hsBonusDeduction 筛选条件
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @return 查询结果
     */
    @GetMapping
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsBonusDeduction hsBonusDeduction) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsBonusDeduction> list = hsBonusDeductionService.queryAll(hsBonusDeduction);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }
    /**
     * 查询列表
     *
     * @param hsBonusDeduction 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsBonusDeduction> list(HsBonusDeduction hsBonusDeduction) {
        return this.hsBonusDeductionService.queryAll(hsBonusDeduction);
    }

    /**
     * 奖励
     *
     * @param bonusVO 实体
     * @return 新增结果
     */
    @GetMapping("/add")
    public String add(BonusVO bonusVO) {
        HsBonusDeduction hsBonusDeduction = new HsBonusDeduction();
        hsBonusDeduction.setUserId(hsUserService.queryByUsername(bonusVO.getUsername()).getUserId());
        hsBonusDeduction.setBonus(bonusVO.getBonus());
        hsBonusDeduction.setTime(new Date());
        hsBonusDeduction.setReason(bonusVO.getReason());
        hsBonusDeduction.setSalaryId(bonusVO.getSalaryId());
        this.hsBonusDeductionService.add(hsBonusDeduction,bonusVO.getUsername());
        return "发放奖励成功";
    }

    /**
     * 扣除
     *
     * @param bonusVO 实体
     * @return 新增结果
     */
    @GetMapping("/sub")
    public String sub(BonusVO bonusVO) {
        HsBonusDeduction hsBonusDeduction = new HsBonusDeduction();
        hsBonusDeduction.setUserId(hsUserService.queryByUsername(bonusVO.getUsername()).getUserId());
        hsBonusDeduction.setDeduction(bonusVO.getBonus());
        hsBonusDeduction.setTime(new Date());
        hsBonusDeduction.setReason(bonusVO.getReason());
        hsBonusDeduction.setSalaryId(bonusVO.getSalaryId());
        this.hsBonusDeductionService.sub(hsBonusDeduction,bonusVO.getUsername());
        return "扣除工资成功";
    }

    /**
     * 查询列表
     *
     * @param salaryId 筛选条件
     * @return 查询结果
     */
    @GetMapping("/sa/{salaryId}")
    public List<HsBonusDeduction> sa(@PathVariable("salaryId") Integer salaryId) {
        return this.hsBonusDeductionService.queryBySalaryId(salaryId);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param bonusDeductionId 主键
     * @return 单条数据
     */
    @GetMapping("{bonusDeductionId}")
    public ResponseEntity<HsBonusDeduction> queryById(@PathVariable("bonusDeductionId") Integer bonusDeductionId) {
        return ResponseEntity.ok(this.hsBonusDeductionService.queryById(bonusDeductionId));
    }

    /**
     * 新增数据
     *
     * @param hsBonusDeduction 实体
     * @return 新增结果
     */
    @PostMapping
    public String insert(@RequestBody HsBonusDeduction hsBonusDeduction) {
        this.hsBonusDeductionService.insert(hsBonusDeduction);
        return "ok";
    }



    /**
     * 编辑数据
     *
     * @param hsBonusDeduction 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsBonusDeduction hsBonusDeduction) {
       this.hsBonusDeductionService.update(hsBonusDeduction);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param bonusDeductionId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{bonusDeductionId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer bonusDeductionId) {
        return ResponseEntity.ok(this.hsBonusDeductionService.deleteById(bonusDeductionId));
    }

}

