package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsPaymentDiscount;
import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.mapper.HsDiscountMapper;
import edu.scau.misp.hs.mapper.HsPaymentOrderMapper;
import edu.scau.misp.hs.service.HsPaymentDiscountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsPaymentDiscount)表控制层
 *
 * @author makejava
 * @since 2022-04-16 17:12:16
 */
@RestController
@RequestMapping("hsPaymentDiscount")
public class HsPaymentDiscountController {
    /**
     * 服务对象
     */
    @Resource
    private HsPaymentDiscountService hsPaymentDiscountService;
    @Resource
    private HsPaymentOrderMapper hsPaymentOrderMapper;
    @Resource
    private HsDiscountMapper hsDiscountMapper;

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsPaymentDiscount 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsPaymentDiscount hsPaymentDiscount) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsPaymentDiscount> list = hsPaymentDiscountService.queryAll(hsPaymentDiscount);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param paymentDiscountId 主键
     * @return 单条数据
     */
    @GetMapping("{paymentDiscountId}")
    public HsPaymentDiscount queryById(@PathVariable("paymentDiscountId") Integer paymentDiscountId) {
        return this.hsPaymentDiscountService.queryById(paymentDiscountId);
    }

    /**
     * 通过查询单条数据
     *
     * @param paymentOrderId 主键
     * @return 单条数据
     */
    @GetMapping("pay/{paymentOrderId}")
    public HsPaymentDiscount queryByPayment(@PathVariable("paymentOrderId") Integer paymentOrderId) {
        return this.hsPaymentDiscountService.queryByPayment(paymentOrderId);
    }

    /**
     * 查询列表
     *
     * @param hsPaymentDiscount 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsPaymentDiscount> queryAll(HsPaymentDiscount hsPaymentDiscount) {
        List<HsPaymentDiscount> hsPaymentDiscounts = hsPaymentDiscountService.queryAll(hsPaymentDiscount);
        for (HsPaymentDiscount hsPaymentDiscount1 : hsPaymentDiscounts){
            HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryById(hsPaymentDiscount1.getPaymentOrderId());
            hsPaymentDiscount1.setHsPaymentOrder(hsPaymentOrder);
            hsPaymentDiscount1.setHsDiscount(hsDiscountMapper.queryById(hsPaymentDiscount1.getDiscountId()));
            hsPaymentDiscountService.update(hsPaymentDiscount1);
        }
        return hsPaymentDiscounts;
    }

    /**
     * 新增数据
     *
     * @param hsPaymentDiscount 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsPaymentDiscount hsPaymentDiscount) {
        this.hsPaymentDiscountService.insert(hsPaymentDiscount);
        return "ok";
    }

    /**
     * 编辑数据
     *
     * @param hsPaymentDiscount 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsPaymentDiscount hsPaymentDiscount) {
        this.hsPaymentDiscountService.update(hsPaymentDiscount);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param paymentDiscountId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{paymentDiscountId}")
    public Boolean deleteById(@PathVariable Integer paymentDiscountId) {
        return this.hsPaymentDiscountService.deleteById(paymentDiscountId);
    }

//    /**
//     * 删除数据
//     *
//     * @param paymentOrderId 主键
//     * @return 删除是否成功
//     */
//    @DeleteMapping("/depo/{paymentOrderId}")
//    public Boolean deleteByPaymentOrderId(@PathVariable Integer paymentOrderId) {
//        return this.hsPaymentDiscountService.deleteByPaymentOrderId(paymentOrderId);
//    }

}

