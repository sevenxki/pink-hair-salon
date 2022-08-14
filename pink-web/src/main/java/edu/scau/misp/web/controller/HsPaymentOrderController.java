package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.hs.service.HsPaymentOrderService;
import edu.scau.misp.hs.util.ServiceException;
import edu.scau.misp.hs.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (HsPaymentOrder)表控制层
 *
 * @author makejava
 * @since 2022-04-16 17:04:10
 */
@RestController
@RequestMapping("hsPaymentOrder")
public class HsPaymentOrderController {
    /**
     * 服务对象
     */
    @Resource
    private HsPaymentOrderService hsPaymentOrderService;
    @Resource
    private HsOrderMapper hsOrderMapper;
    @Resource
    private HsStaffMapper hsStaffMapper;
    @Resource
    private HsProjectMapper hsProjectMapper;
    @Resource
    private HsUserMapper hsUserMapper;

    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsPaymentOrder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsPaymentOrder hsPaymentOrder) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsPaymentOrder> list = hsPaymentOrderService.queryAll(hsPaymentOrder);//已经付款的
//        List<HsPaymentOrder> list1 = new ArrayList<>();
//        for (HsPaymentOrder hsPaymentOrder1 : list){
//            if (hsPaymentOrder1.getStatus()==1){
//                list1.add(hsPaymentOrder1);
//            }
//        }
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param paymentOrderId 主键
     * @return 单条数据
     */
    @GetMapping("{paymentOrderId}")
    public HsPaymentOrder getById(@PathVariable("paymentOrderId") Integer paymentOrderId) {
        return this.hsPaymentOrderService.queryById(paymentOrderId);
    }



    /**
     * 查询付款表列表通过手机号码
     *
     * @param phonePayment 筛选条件
     * @return 查询结果
     */
    @GetMapping("/phone/{phonePayment}")
    public List<HsPaymentOrder> queryByPhone(@PathVariable("phonePayment") String phonePayment) {
        List<HsPaymentOrder> hsPaymentOrders = hsPaymentOrderService.queryByPhone(phonePayment);
        return hsPaymentOrders;
    }

    /**
     * 查询付款表列表
     *
     * @param hsPaymentOrder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsPaymentOrder> queryAll(HsPaymentOrder hsPaymentOrder) {
        List<HsPaymentOrder> hsPaymentOrders = hsPaymentOrderService.queryAll(hsPaymentOrder);
        return hsPaymentOrders;
    }

    /**
     * 查询已经付款表列表
     *
     * @param hsPaymentOrder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/over")
    public List<HsPaymentOrder> queryOver(HsPaymentOrder hsPaymentOrder) {
        List<HsPaymentOrder> hsPaymentOrders = hsPaymentOrderService.queryOver(hsPaymentOrder);
        return hsPaymentOrders;
    }

    /**
     * 查询订单号返回历史订单
     *
     * @param orderId 筛选条件
     * @return 结果
     */
    @GetMapping("/account/{orderId}")
    public HsPaymentOrder queryByOrder(@PathVariable("orderId") Integer orderId){
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderService.queryByOrder(orderId);
        return hsPaymentOrder;
    }

    /**
     * 查询某客户手机号返回所拥有的优惠券结果
     *
     * @param chooseDiscountVO 筛选条件
     * @return 查询结果
     */
    @GetMapping("/vo")
    public List<VipDiscountVo> queryAllByPhone(ChooseDiscountVO chooseDiscountVO){
        return this.hsPaymentOrderService.queryAllByPhone(chooseDiscountVO);
    }

    /**
     * 结账新增付款订单
     *
     * @param orderId 实体
     * @return 新增结果
     */
    @GetMapping("/add/{orderId}")
    public TtoVo addByOrderId(@PathVariable("orderId") Integer orderId){
        HsOrder hsOrder = hsOrderMapper.queryById(orderId);
        hsOrder.setHaircareName(hsProjectMapper.queryById(hsOrder.getHaircare()).getProjectName());
        hsOrder.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder.getStaffHairstylistId()).getStaffName());
        HsUser hsUser = hsUserMapper.queryById(hsOrder.getUserId());
        HsStaff hsStaff = hsStaffMapper.queryByuserName(hsUser.getUsername());
        hsOrder.setUserStaffName(hsStaff.getStaffName());
        TtoVo ttoVo = new TtoVo();
        HsPaymentOrder hsPaymentOrder = new HsPaymentOrder();
        if (hsPaymentOrderService.queryByOrder(orderId)==null){
            hsPaymentOrder.setHsOrder(hsOrder);

            hsPaymentOrder.setOrderId(hsOrder.getOrderId());
            hsPaymentOrder.setStatus(0);
//            hsPaymentOrder.setTto(hsOrder.getTotal());//模拟金额
            hsPaymentOrder.setTotalPayment(hsOrder.getTotal());//付款实际记录金额
            hsPaymentOrder.setUseDiscount(0);
            hsPaymentOrder.setUseVip(0);
//            BigDecimal t1 = new BigDecimal(Double.toString(hsOrder.getTotal()));
//            BigDecimal t2 = new BigDecimal(Double.toString(hsOrder.getTotal()));
//            double total = t1.subtract(t2).doubleValue();;
            ttoVo.setPaymentOrder(hsPaymentOrder);
            ttoVo.setYy(hsOrder.getTotal());
            hsPaymentOrderService.insert(hsPaymentOrder);
        }else if (hsPaymentOrderService.queryByOrder(orderId).getStatus()==0){
            HsPaymentOrder hsPaymentOrder1 = hsPaymentOrderService.queryByOrder(orderId);
            hsPaymentOrder.setHsOrder(hsPaymentOrder1.getHsOrder());
            hsPaymentOrder.setPaymentOrderId(hsPaymentOrder1.getPaymentOrderId());
            hsPaymentOrder.setOrderId(hsPaymentOrder1.getOrderId());
//            hsPaymentOrder.setTto(hsPaymentOrder.getTto());//模拟金额
            hsPaymentOrder.setTotalPayment(hsPaymentOrder1.getTotalPayment());//付款实际记录金额
            hsPaymentOrder.setPaymentMethod(hsPaymentOrder1.getPaymentMethod());
            hsPaymentOrder.setUseDiscount(hsPaymentOrder1.getUseDiscount());
            hsPaymentOrder.setUseVip(hsPaymentOrder1.getUseVip());
            hsPaymentOrder.setDiscountName(hsPaymentOrder1.getDiscountName());
            hsPaymentOrder.setTime(hsPaymentOrder1.getTime());
            hsPaymentOrder.setStatus(hsPaymentOrder1.getStatus());
            hsPaymentOrder.setPhonePayment(hsPaymentOrder1.getPhonePayment());
//            BigDecimal t1 = new BigDecimal(Double.toString(hsOrder.getTotal()));
//            BigDecimal t2 = new BigDecimal(Double.toString(hsOrder.getTotal()));
//            double total = t1.subtract(t2).doubleValue();;
            ttoVo.setPaymentOrder(hsPaymentOrder);
            ttoVo.setYy(hsPaymentOrder.getTotalPayment());
        }else if (hsPaymentOrderService.queryByOrder(orderId).getStatus()==1){
            throw new ServiceException("已经结账了");
        }else{
            throw new ServiceException("添加付款账单失败");
        }
        return ttoVo;
    }

    /**
     * 新增付款的优惠数据
     *
     * @param paymentVo 实体
     * @return 新增结果
     */
    @GetMapping("/dt")
    public Double addDt(PaymentVo paymentVo){
       return this.hsPaymentOrderService.addDt(paymentVo);
    }

    /**
     * 确定支付，补充支付方式
     *
     * @param paymentMethodVo 实体
     * @return 新增结果
     */
    @GetMapping("/payMethod")
    public HsPaymentOrder addPaymentMethod(PaymentMethodVo paymentMethodVo){
       return this.hsPaymentOrderService.addPaymentMethod(paymentMethodVo);
    }


    /**
     * 新增数据
     *
     * @param hsPaymentOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsPaymentOrder hsPaymentOrder) {
        this.hsPaymentOrderService.insert(hsPaymentOrder);
        return "ok";
    }


    /**
     * 编辑数据
     *
     * @param hsPaymentOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsPaymentOrder hsPaymentOrder) {
        this.hsPaymentOrderService.update(hsPaymentOrder);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param paymentOrderId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{paymentOrderId}")
    public Boolean deleteById(@PathVariable Integer paymentOrderId) {
        return this.hsPaymentOrderService.deleteById(paymentOrderId);
    }

}

