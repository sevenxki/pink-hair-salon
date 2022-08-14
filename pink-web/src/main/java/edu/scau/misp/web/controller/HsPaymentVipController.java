package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsPaymentVip;
import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.mapper.HsPaymentOrderMapper;
import edu.scau.misp.hs.mapper.HsVipMapper;
import edu.scau.misp.hs.service.HsPaymentVipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsPaymentVip)表控制层
 *
 * @author makejava
 * @since 2022-04-16 17:12:05
 */
@RestController
@RequestMapping("hsPaymentVip")
public class HsPaymentVipController {
    /**
     * 服务对象
     */
    @Resource
    private HsPaymentVipService hsPaymentVipService;
    @Resource
    private HsPaymentOrderMapper hsPaymentOrderMapper;
    @Resource
    private HsVipMapper hsVipMapper;

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsPaymentVip 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsPaymentVip hsPaymentVip) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsPaymentVip> list = hsPaymentVipService.queryAll(hsPaymentVip);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param paymentVipId 主键
     * @return 单条数据
     */
    @GetMapping("{paymentVipId}")
    public HsPaymentVip queryById(@PathVariable("paymentVipId") Integer paymentVipId) {

        return this.hsPaymentVipService.queryById(paymentVipId);
    }

    /**
     * 通过查询单条数据
     *
     * @param paymentOrderId 主键
     * @return 单条数据
     */
    @GetMapping("pay/{paymentOrderId}")
    public HsPaymentVip queryByPayment(@PathVariable("paymentOrderId") Integer paymentOrderId) {
        return this.hsPaymentVipService.queryByPayment(paymentOrderId);
    }

    /**
     * 查询列表
     *
     * @param hsPaymentVip 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsPaymentVip> queryAll(HsPaymentVip hsPaymentVip) {
        List<HsPaymentVip> hsPaymentVips = hsPaymentVipService.queryAll(hsPaymentVip);
        for (HsPaymentVip hsPaymentVip1 : hsPaymentVips){
            HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryById(hsPaymentVip1.getPaymentOrderId());
            hsPaymentVip1.setHsPaymentOrder(hsPaymentOrder);
            hsPaymentVip1.setHsVip(hsVipMapper.queryById(hsPaymentVip.getVipId()));
            hsPaymentVipService.update(hsPaymentVip1);
        }
        return hsPaymentVips;
    }



    /**
     * 新增数据
     *
     * @param hsPaymentVip 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsPaymentVip hsPaymentVip) {
        this.hsPaymentVipService.insert(hsPaymentVip);
        return "ok";
    }

    /**
     * 编辑数据
     *
     * @param hsPaymentVip 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsPaymentVip hsPaymentVip) {
        this.hsPaymentVipService.update(hsPaymentVip);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param paymentVipId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{paymentVipId}")
    public Boolean deleteById(@PathVariable Integer paymentVipId) {
        return this.hsPaymentVipService.deleteById(paymentVipId);
    }

//    /**
//     * 删除数据
//     *
//     * @param paymentOrderId 主键
//     * @return 删除是否成功
//     */
//    @DeleteMapping("/depo/{paymentOrderId}")
//    public Boolean deleteByPaymentOrderId(@PathVariable Integer paymentOrderId) {
//        return this.hsPaymentVipService.deleteByPaymentOrderId(paymentOrderId);
//    }
}

