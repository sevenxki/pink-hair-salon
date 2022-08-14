package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.service.HsAccumulationService;
import edu.scau.misp.hs.service.HsGrouporderDiscountService;
import edu.scau.misp.hs.service.HsGrouporderService;
import edu.scau.misp.hs.service.HsVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsVip)表控制层
 *
 */
@RestController
@RequestMapping("hsVip")
public class HsVipController {
    /**
     * 服务对象
     */
    @Autowired
    private HsVipService hsVipService;
    @Autowired
    private HsAccumulationService hsAccumulationService;
    @Autowired
    private HsGrouporderService hsGrouporderService;
    @Autowired
    private HsGrouporderDiscountService hsGrouporderDiscountService;


    /**
     * 查询列表
     *
     * @param hsVip 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsVip> list(HsVip hsVip) {
        return this.hsVipService.queryAll(hsVip);
    }

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsVip 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsVip hsVip) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsVip> list = hsVipService.queryAll(hsVip);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param vipId 主键
     * @return 单条数据
     */
    @GetMapping("/getById/{vipId}")
    public HsVip getById(@PathVariable("vipId") Integer vipId) {
        return this.hsVipService.queryById(vipId);
    }

    /**
     * 新增数据
     *
     * @param hsVip 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public String add(@RequestBody HsVip hsVip) {
        this.hsVipService.insert(hsVip);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsVip 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public String edit(@RequestBody HsVip hsVip) {
        this.hsVipService.update(hsVip);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param vipId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{vipId}")
    public Boolean deleteById(@PathVariable Integer vipId) {
        return this.hsVipService.deleteById(vipId);
    }

    /**
     * 通过电话找到all
     *
     * @param vipPhone 实例对象
     * @return
     */
    @GetMapping("/queryAllByPhone/{vipPhone}")
    public HsVip queryAllByPhone(@PathVariable String vipPhone){
        return this.hsVipService.queryAllByPhone(vipPhone);
    }

    /**
     * 新增Vip
     *
     * @param hsVip 实体
     * @return 新增结果
     */
    @PostMapping("/addNewVip")
    public String addNewVip(@RequestBody HsVip hsVip) {
        this.hsVipService.addNewVip(hsVip);
        this.hsAccumulationService.addNewAccumulation(hsVip);
        String no = this.hsGrouporderService.addNewGroup(hsVip);
        this.hsGrouporderDiscountService.addNew(hsVip,no);
        return "ok";
    }

    /**
     * 删除vip
     *
     * @param vipPhone
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteVip/{vipPhone}")
    public Boolean deleteVip(@PathVariable String vipPhone) {
        this.hsAccumulationService.deleteByVipPhone(vipPhone);
        return this.hsVipService.deleteVip(vipPhone);
    }

    /**
     * 充值余额
     *
     * @param
     * @return 实例对象
     */
    @PostMapping("/recharge")
    public String recharge(@RequestBody HsVip hsVip) {
        this.hsVipService.recharge(hsVip);
        this.hsAccumulationService.updateB(hsVip);
        String no = this.hsGrouporderService.addNewGroup(hsVip);
        this.hsGrouporderDiscountService.addNew(hsVip,no);
        return "ok";
    }

    /**
     * 充值优惠卡
     *
     * @param
     * @return 实例对象
     */
    @PostMapping("/group")
    public String group(@RequestBody HsVip hsVip) {
        this.hsVipService.group(hsVip);
        this.hsAccumulationService.updateC(hsVip);
        String no = this.hsGrouporderService.addNewGroup(hsVip);
        this.hsGrouporderDiscountService.addNew(hsVip,no);
        return "ok";
    }

}