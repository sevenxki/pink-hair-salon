package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.hs.service.HsPaymentOrderService;
import edu.scau.misp.hs.util.DateUtils;
import edu.scau.misp.hs.util.ServiceException;
import edu.scau.misp.hs.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * (HsPaymentOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-04-16 17:04:10
 */
@Service("hsPaymentOrderService")
public class HsPaymentOrderServiceImpl implements HsPaymentOrderService {
    @Resource
    private HsPaymentOrderMapper hsPaymentOrderMapper;
    @Resource
    private HsOrderMapper hsOrderMapper;
    @Resource
    private HsPaymentDiscountMapper hsPaymentDiscountMapper;
    @Resource
    private HsPaymentVipMapper hsPaymentVipMapper;
    @Resource
    private HsStaffMapper hsStaffMapper;
    @Resource
    private HsProjectMapper hsProjectMapper;
    @Resource
    private HsUserMapper hsUserMapper;
    @Resource
    private HsDiscountMapper hsDiscountMapper;
    @Resource
    private HsGrouporderMapper hsGrouporderMapper;
    @Resource
    private HsVipMapper hsVipMapper;
    @Resource
    private HsKindMapper hsKindMapper;
    @Resource
    private HsSaleCommissionMapper hsSaleCommissionMapper;
    @Resource
    private HsSalaryMapper hsSalaryMapper;
    @Resource
    private HsWageRulesMapper hsWageRulesMapper;
    @Resource
    private HsSalaryComMapper hsSalaryComMapper;


    public HsOrder change(HsOrder hsOrder){
        HsOrder hsOrder1 = hsOrderMapper.queryById(hsOrder.getOrderId());
        hsOrder1.setHaircareName(hsProjectMapper.queryById(hsOrder1.getHaircare()).getProjectName());
        hsOrder1.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder1.getStaffHairstylistId()).getStaffName());
        HsUser hsUser = hsUserMapper.queryById(hsOrder1.getUserId());
        HsStaff hsStaff = hsStaffMapper.queryByuserName(hsUser.getUsername());
        hsOrder1.setUserStaffName(hsStaff.getStaffName());
        return hsOrder1;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param paymentOrderId 主键
     * @return 实例对象
     */
    @Override
    public HsPaymentOrder queryById(Integer paymentOrderId) {
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryById(paymentOrderId);
        HsOrder hsOrder1 = change(hsOrderMapper.queryById(hsPaymentOrder.getOrderId()));
        hsPaymentOrder.setHsOrder(hsOrder1);
        if (hsPaymentOrder.getUseDiscount()!=0){
            hsPaymentOrder.setDiscountName(hsDiscountMapper.queryById(hsPaymentOrder.getUseDiscount()).getDiscountName());
        }else {
            hsPaymentOrder.setDiscountName("");
        }
        hsPaymentOrderMapper.update(hsPaymentOrder);
        return hsPaymentOrder;
    }

    @Override
    public List<HsPaymentOrder> queryByPhone(String phonePayment) {
        List<HsPaymentOrder> hsPaymentOrders = this.hsPaymentOrderMapper.queryByPhone(phonePayment);
        for (HsPaymentOrder hsPaymentOrder : hsPaymentOrders){
            HsOrder hsOrder1 = change(hsOrderMapper.queryById(hsPaymentOrder.getOrderId()));
            hsPaymentOrder.setHsOrder(hsOrder1);
            if (hsPaymentOrder.getUseDiscount()!=0){
                hsPaymentOrder.setDiscountName(hsDiscountMapper.queryById(hsPaymentOrder.getUseDiscount()).getDiscountName());
            }else {
                hsPaymentOrder.setDiscountName("");
            }
            hsPaymentOrderMapper.update(hsPaymentOrder);
        }
        return hsPaymentOrders;
    }

    @Override
    public HsPaymentOrder queryByOrder(Integer orderId) {
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryByOrder(orderId);
        if (hsPaymentOrder!=null) {
            HsOrder hsOrder1 = hsOrderMapper.queryById(orderId);
            hsOrder1.setHaircareName(hsProjectMapper.queryById(hsOrder1.getHaircare()).getProjectName());
            hsOrder1.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder1.getStaffHairstylistId()).getStaffName());
            HsUser hsUser = hsUserMapper.queryById(hsOrder1.getUserId());
            HsStaff hsStaff = hsStaffMapper.queryByuserName(hsUser.getUsername());
            hsOrder1.setUserStaffName(hsStaff.getStaffName());
            hsPaymentOrder.setHsOrder(hsOrder1);
            if (hsPaymentOrder.getUseDiscount()!=0){
                hsPaymentOrder.setDiscountName(hsDiscountMapper.queryById(hsPaymentOrder.getUseDiscount()).getDiscountName());
            }else {
                hsPaymentOrder.setDiscountName("");
            }
            hsPaymentOrderMapper.update(hsPaymentOrder);
        }
        return hsPaymentOrder;
    }

    /**
     *
     * @param hsPaymentOrder 筛选条件
     * @return
     */
    @Override
    public List<HsPaymentOrder> queryAll(HsPaymentOrder hsPaymentOrder) {
        List<HsPaymentOrder> list = this.hsPaymentOrderMapper.queryOver(hsPaymentOrder);
        List<HsPaymentOrder> list1 = getHsPaymentOrders(list);
        return list1;
    }

    private List<HsPaymentOrder> getHsPaymentOrders(List<HsPaymentOrder> list) {
//        List<HsPaymentOrder> list1 = new ArrayList<>();
        for (HsPaymentOrder hsPaymentOrder1 : list){
            HsOrder hsOrder1 = hsOrderMapper.queryById(hsPaymentOrder1.getOrderId());
            hsOrder1.setHaircareName(hsProjectMapper.queryById(hsOrder1.getHaircare()).getProjectName());
            hsOrder1.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder1.getStaffHairstylistId()).getStaffName());
            HsUser hsUser = hsUserMapper.queryById(hsOrder1.getUserId());
            HsStaff hsStaff = hsStaffMapper.queryByuserName(hsUser.getUsername());
            hsOrder1.setUserStaffName(hsStaff.getStaffName());
            hsPaymentOrder1.setHsOrder(hsOrder1);
            hsPaymentOrderMapper.update(hsPaymentOrder1);
        }
        return list;
    }

    @Override
    public List<HsPaymentOrder> queryOver(HsPaymentOrder hsPaymentOrder) {
        List<HsPaymentOrder> list = this.hsPaymentOrderMapper.queryOver(hsPaymentOrder);
        return getHsPaymentOrders(list);
    }

    /**
     * 新增数据
     *
     * @param hsPaymentOrder 实例对象
     * @return 实例对象
     */
    @Override
    public HsPaymentOrder insert(HsPaymentOrder hsPaymentOrder) {
        this.hsPaymentOrderMapper.insert(hsPaymentOrder);
        return hsPaymentOrder;
    }

    /**
     * 修改数据
     *
     * @param hsPaymentOrder 实例对象
     * @return 实例对象
     */
    @Override
    public HsPaymentOrder update(HsPaymentOrder hsPaymentOrder) {
        this.hsPaymentOrderMapper.update(hsPaymentOrder);
        return this.queryById(hsPaymentOrder.getPaymentOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param paymentOrderId 主键
     * @return 是否成功
     */

    boolean a,b,c;
    @Override
    public boolean deleteById(Integer paymentOrderId) {
        if (hsPaymentDiscountMapper.queryByPayment(paymentOrderId)!=null){  //优惠卡消费
            a = hsPaymentDiscountMapper.deleteById(hsPaymentDiscountMapper.queryByPayment(paymentOrderId).getPaymentDiscountId())>0;
            if (a && hsPaymentVipMapper.queryByPayment(paymentOrderId)!=null){
                b = hsPaymentVipMapper.deleteById(hsPaymentVipMapper.queryByPayment(paymentOrderId).getPaymentVipId())>0;
                if (b){
                   c = this.hsPaymentOrderMapper.deleteById(paymentOrderId) > 0;
                }else {
                    throw new ServiceException("删除失败");
                }
            }else if (a && hsPaymentVipMapper.queryByPayment(paymentOrderId)==null){
               c = this.hsPaymentOrderMapper.deleteById(paymentOrderId) > 0;
            }
        }else if (hsPaymentDiscountMapper.queryByPayment(paymentOrderId)==null){
            if (hsPaymentVipMapper.queryByPayment(paymentOrderId)!=null){
                b = hsPaymentVipMapper.deleteById(hsPaymentVipMapper.queryByPayment(paymentOrderId).getPaymentVipId())>0;
                if (b){
                    c = this.hsPaymentOrderMapper.deleteById(paymentOrderId) > 0;
                }else {
                    throw new ServiceException("删除失败");
                }
            }else if (hsPaymentVipMapper.queryByPayment(paymentOrderId)==null){  //会员卡消费
                c = this.hsPaymentOrderMapper.deleteById(paymentOrderId) > 0;
            }
        }
        return c;
    }

    /**
     * 新增付款的优惠数据
     *
     * @param paymentVo 实体
     * @return 新增结果
     */
    @Override
    public Double addDt(PaymentVo paymentVo) {
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryByOrder(paymentVo.getOrderId()); //付款订单
        HsOrder hsOrder = hsOrderMapper.queryById(paymentVo.getOrderId());    //订单
        hsPaymentOrder.setHsOrder(hsOrder);
        HsProject hsProject = hsProjectMapper.queryById(hsOrder.getProjectId());
        Integer kindId = hsProject.getKindId();  //种类
        String level = hsProject.getProjectLevel();  //等级
        TtoVo ttoVo = new TtoVo();
        Integer discountId = paymentVo.getDiscountId();  //优惠卡方式ID
        HsVip hsVip = hsVipMapper.queryAllByPhone(hsPaymentOrder.getPhonePayment());  //会员表
        Double b;
        if(discountId==1){     //会员卡
            ttoVo.setYy(hsOrder.getTotal());
            if(hsVip.getVipBalance()<hsPaymentOrder.getTotalPayment()){
                ttoVo.setYy(hsOrder.getTotal());
                throw new ServiceException("会员卡余额不足");
            }
        }
        if (discountId==2 && kindId==1 && level.equals("1")){  //判断是否为基础剪发
            ttoVo.setPaymentOrder(hsPaymentOrder);
            if (hsOrder.getHaircare()==4){
                ttoVo.setYy(0.00);
            }else if (hsOrder.getHaircare()==5){ //若是包括高级护理则只需要交高级护理的钱
                ttoVo.setYy(hsProjectMapper.queryById(5).getProjectPrice());
            }
        }else if (discountId==3){  //高级护理所有都可以用
            ttoVo.setPaymentOrder(hsPaymentOrder);
            ttoVo.setYy(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectPrice());//直接算项目的钱，不外加选的护理的钱
        }else if(discountId==0){
            ttoVo.setYy(hsOrder.getTotal());
        }
        b = ttoVo.getYy();
        return b;
    }

    /**
     * 查询某客户手机号返回所拥有的优惠券结果
     *
     * @param chooseDiscountVO 筛选条件
     * @return 查询结果
     */
    @Override
    public List<VipDiscountVo> queryAllByPhone(ChooseDiscountVO chooseDiscountVO) {
        System.out.println(chooseDiscountVO);
        String vipPhone = chooseDiscountVO.getTelephone();
        Integer orderId = chooseDiscountVO.getOrderId();
        HsVip hsVip = hsVipMapper.queryAllByPhone(vipPhone);
        System.out.println(vipPhone);
        System.out.println(orderId);
        HsOrder hsOrder = hsOrderMapper.queryById(orderId);
        HsProject hsProject = hsProjectMapper.queryById(hsOrder.getProjectId());
        Integer kindId = hsProject.getKindId();  //种类
        String level = hsProject.getProjectLevel();  //等级
        List<VipDiscountVo> vipDiscountVos = new ArrayList<>();//优惠集
        if (hsVip!=null){  //是否在会员表中，如果没有充值过直接返回空
            if (hsVip.getVipBalance()>0){   //会员储值
                VipDiscountVo v3 = new VipDiscountVo();
                v3.setDiscountId(1);
                v3.setDiscountName("会员卡");
                v3.setVipBalance(hsVip.getVipBalance());
                vipDiscountVos.add(v3);
            }
            if(hsVip.getVipHaircut()>0 && kindId==1 && level.equals("1")){  //理发卡  //大于0才放进数列
                VipDiscountVo v1 = new VipDiscountVo();
                v1.setDiscountId(2);
                v1.setDiscountName("理发卡");
                v1.setVipHaircut(hsVip.getVipHaircut());
                vipDiscountVos.add(v1);
            }
            if(hsVip.getVipHaircare()>0 && hsOrder.getHaircare()==5){   //护理卡  //大于0才放进数列
                VipDiscountVo v2 = new VipDiscountVo();
                v2.setDiscountId(3);
                v2.setDiscountName("护理卡");
                v2.setVipHaircare(hsVip.getVipHaircare());
                vipDiscountVos.add(v2);
            }
        }
        return vipDiscountVos;
    }

    /**
     * 确定支付，补充支付方式
     *
     * @param paymentMethodVo 实体
     * @return 新增结果
     */
    @Override
    public HsPaymentOrder addPaymentMethod(PaymentMethodVo paymentMethodVo) {
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryByOrder(paymentMethodVo.getOrderId());
        HsOrder hsOrder = hsOrderMapper.queryById(hsPaymentOrder.getOrderId());
        HsVip hsVip = hsVipMapper.queryAllByPhone(paymentMethodVo.getPhonePayment());
        HsProject hsProject = hsProjectMapper.queryById(hsOrder.getProjectId());
        //技师
        HsStaff tech = hsStaffMapper.queryById(hsOrder.getStaffTechnicianId());
        //发型师
        HsStaff hair = hsStaffMapper.queryById(hsOrder.getStaffHairstylistId());
        if (hsVip!=null){  //有没有充值过
            if (paymentMethodVo.getDiscountId()==1){   //没有使用优惠卡，使用了会员卡
                if (paymentMethodVo.getPaymentMethod()==4){   //使用了会员卡
                    if(hsVip.getVipBalance()>=hsPaymentOrder.getTotalPayment()){
                        //会员卡余额
                        BigDecimal t11 = new BigDecimal(Double.toString(hsVip.getVipBalance()));
                        BigDecimal t22 = new BigDecimal(Double.toString(hsPaymentOrder.getTotalPayment()));
                        Double total = t11.subtract(t22).doubleValue();
                        hsVip.setVipBalance(total);
                        hsVipMapper.update(hsVip);
                        //付款支付表
                        hsPaymentOrder.setPaymentMethod(paymentMethodVo.getPaymentMethod());
                        hsPaymentOrder.setPhonePayment(paymentMethodVo.getPhonePayment());
                        hsPaymentOrder.setTime(new Date());
                        hsPaymentOrder.setUseVip(1);//使用了会员卡
                        hsPaymentOrder.setStatus(1);//付款订单已经结账
                        hsPaymentOrder.setTotalPayment(paymentMethodVo.getPrice());
                        hsPaymentOrderMapper.update(hsPaymentOrder);
                        //付款会员卡记录
                        HsPaymentVip hsPaymentVip = new HsPaymentVip();
                        hsPaymentVip.setHsPaymentOrder(hsPaymentOrder);
                        hsPaymentVip.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                        hsPaymentVip.setVipId(hsVip.getVipId());
                        hsPaymentVip.setUseNumber(hsPaymentOrder.getTotalPayment());
                        hsPaymentVipMapper.insert(hsPaymentVip);
                        //订单表
                        hsOrder.setStatus(1);//已经结账了的订单表要改变状态
                        hsOrderMapper.update(hsOrder);
                        /**
                         * 员工业绩表
                         */
                        Long hbasic;//发型师的基本工资
                        //日期格式
                        DateFormat fmt = new SimpleDateFormat("yyyy-MM");
                        //技师基本工资表里面的相关查询和操作
                        HsWageRules hst = new HsWageRules();
                        hst.setRoleId(tech.getRoleId());
                        hst.setLevel(hsOrder.getHaircare());
                        List<HsWageRules> t1 = hsWageRulesMapper.queryAll(hst);
                        for (HsWageRules t2 : t1){
                            //技师，护理的钱
                            BigDecimal a = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getHaircare()).getProjectPrice()));
                            BigDecimal b = new BigDecimal(String.valueOf(t2.getPercentage()));
                            Long c = a.multiply(b).longValue()/100;
                            HsSaleCommission tsc = new HsSaleCommission();
                            tsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            tsc.setStaffId(tech.getStaffId());
                            tsc.setCommission(c);
                            hsSaleCommissionMapper.insert(tsc);  //业绩表的插入
                            /**
                             * 技师工资表
                             */
                            //技师工资表
                            HsSalary hsSalary = new HsSalary();
                            hsSalary.setStaffId(tech.getStaffId());
                            hsSalary.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                            List<HsSalary> hsSalary1 = hsSalaryMapper.queryAll(hsSalary);
                            if (hsSalary1!=null && !hsSalary1.isEmpty()){ //技师,这年月已存在
                                //某年月的工资
                                for (HsSalary hsSalary2 : hsSalary1){
                                    Long totalCom = hsSalary2.getTotalCommission();//总提成
                                    Long totalWage = hsSalary2.getTotalWages();//总工资
                                    BigDecimal tc = new BigDecimal(String.valueOf((totalCom)));//总提成
                                    BigDecimal tw = new BigDecimal(String.valueOf((totalWage)));//总工资
                                    //查询那一单的业绩
                                    HsSaleCommission hsSaleCommission = new HsSaleCommission();
                                    hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                                    hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                                    List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                                    for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1){
                                        Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                        BigDecimal tm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                                        Long td = tm.add(tc).longValue();//总提成的加法
                                        Long te = tm.add(tw).longValue();//总工资的加法
                                        hsSalary2.setTotalCommission(td);//更新总提成
                                        hsSalary2.setTotalWages(te);//更新总工资
                                        hsSalary2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                        hsSalaryMapper.update(hsSalary2);//更新工资表
                                        HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                        hsSalaryCom.setSalaryId(hsSalary2.getSalaryId());//工资ID
                                        hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                        hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                                    }
                                }
                            }else { //技师，这年月不存在
                                //查询那一单的业绩
                                HsSaleCommission hsSaleCommission = new HsSaleCommission();
                                hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                                hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                                List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                                for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                                    Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                    BigDecimal hm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                                    //技师基本工资表里面的相关查询和操作
                                    HsWageRules hw = new HsWageRules();
                                    hw.setRoleId(tech.getRoleId());
                                    hw.setLevel(hsOrder.getHaircare());
                                    List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hw);
                                    for (HsWageRules hw2 : hw1){
                                        //发型师,项目的钱
                                        hbasic = hw2.getBasicWage();//基本工资
                                        BigDecimal ba = new BigDecimal(String.valueOf(hbasic));//基本工资
                                        Long bc = ba.add(hm).longValue();//总工资的加法
                                        HsSalary hsSalary10 = new HsSalary();
                                        hsSalary10.setTotalWages(bc);//总工资
                                        hsSalary10.setTotalCommission(com);//总提成
                                        hsSalary10.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                        hsSalary10.setStatus("0");//未发放状态
                                        hsSalary10.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                        hsSalary10.setStaffId(tech.getStaffId());//员工
                                        hsSalaryMapper.insert(hsSalary10);//新增数据
                                        HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                        hsSalaryCom.setSalaryId(hsSalary10.getSalaryId());//工资ID
                                        hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                        hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                                    }
                                }
                            }
                        }
                        //发型师基本工资表里面的相关查询和操作
                        HsWageRules hsh = new HsWageRules();
                        hsh.setRoleId(hair.getRoleId());
                        hsh.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                        List<HsWageRules> h1 = hsWageRulesMapper.queryAll(hsh);
                        for (HsWageRules h2 : h1){
                            //发型师,项目的钱
                            BigDecimal ba = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectPrice()));
                            BigDecimal bb = new BigDecimal(String.valueOf(h2.getPercentage()));
                            Long bc = ba.multiply(bb).longValue()/100;
                            HsSaleCommission hsc = new HsSaleCommission();
                            hsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            hsc.setStaffId(hair.getStaffId());
                            hsc.setCommission(bc);
                            hsSaleCommissionMapper.insert(hsc);
                            /**
                             * 发型师工资表
                             */
                            //发型师工资表
                            HsSalary h = new HsSalary();
                            h.setStaffId(hair.getStaffId());
                            h.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                            List<HsSalary> hh1 = hsSalaryMapper.queryAll(h);
                            if (hh1!=null && !hh1.isEmpty()){ //发型师，这年月已存在
                                //某年月的工资
                                for (HsSalary  hh2: hh1) {
                                    Long totalCom = hh2.getTotalCommission();//总提成
                                    Long totalWage = hh2.getTotalWages();//总工资
                                    BigDecimal hc = new BigDecimal(String.valueOf(totalCom));//总提成
                                    BigDecimal hw = new BigDecimal(String.valueOf(totalWage));//总工资
                                    //查询那一单的业绩
                                    HsSaleCommission hsSaleCommission = new HsSaleCommission();
                                    hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                                    hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                                    List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                                    for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                                        Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                        BigDecimal hm = new BigDecimal(String.valueOf(com));//输出那一单的业绩
                                        Long hd = hm.add(hc).longValue();//总提成的加法
                                        Long he = hm.add(hw).longValue();//总工资的加法
                                        hh2.setTotalCommission(hd);//更新总提成
                                        hh2.setTotalWages(he);//更新总工资
                                        hh2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                        hsSalaryMapper.update(hh2);//更新工资表
                                        HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                        hsSalaryCom.setSalaryId(hh2.getSalaryId());//工资ID
                                        hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                        hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                                    }
                                }
                            }else { //发型师，这年月不存在
                                //查询那一单的业绩
                                HsSaleCommission hsSaleCommission = new HsSaleCommission();
                                hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                                hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                                List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                                for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                                    Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                    BigDecimal hm = new BigDecimal(com);//输出那一单的业绩
                                    //发型师基本工资表里面的相关查询和操作
                                    HsWageRules hw = new HsWageRules();
                                    hw.setRoleId(hair.getRoleId());
                                    hw.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                                    List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hsh);
                                    for (HsWageRules hw2 : hw1){
                                        //发型师,项目的钱
                                        hbasic = hw2.getBasicWage();//基本工资
                                        BigDecimal ba1 = new BigDecimal(String.valueOf(hbasic));//基本工资
                                        Long bc1 = ba1.add(hm).longValue();//总工资的加法
                                        HsSalary hs1 = new HsSalary();
                                        hs1.setTotalCommission(com);//总提成
                                        hs1.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                        hs1.setStatus("0");//未发放状态
                                        hs1.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                        hs1.setTotalWages(bc1);//总工资
                                        hs1.setStaffId(hair.getStaffId());//员工ID
                                        hsSalaryMapper.insert(hs1);//新增数据
                                        HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                        hsSalaryCom.setSalaryId(hs1.getSalaryId());//工资ID
                                        hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                        hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                                    }
                                }
                            }
                        }

                    }else if (hsVip.getVipBalance()<hsPaymentOrder.getTotalPayment()){
                        throw new ServiceException("会员卡余额不足");
                    }
                }
            }else if (paymentMethodVo.getDiscountId()==2){  //选择了理发卡
                //会员表
                hsVip.setVipHaircut(hsVip.getVipHaircut()-1);
                hsVipMapper.update(hsVip);
                //付款支付表
                hsPaymentOrder.setPaymentMethod(paymentMethodVo.getPaymentMethod());
                hsPaymentOrder.setPhonePayment(paymentMethodVo.getPhonePayment());
                hsPaymentOrder.setTime(new Date());
                hsPaymentOrder.setUseDiscount(2);
                hsPaymentOrder.setStatus(1);
                hsPaymentOrder.setTotalPayment(paymentMethodVo.getPrice());
                hsPaymentOrderMapper.update(hsPaymentOrder);
                //付款优惠卡记录
                HsPaymentDiscount hsPaymentDiscount = new HsPaymentDiscount();
                hsPaymentDiscount.setHsPaymentOrder(hsPaymentOrder);
                hsPaymentDiscount.setDiscountId(2);
                hsPaymentDiscount.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                hsPaymentDiscount.setCount(1);
                hsPaymentDiscountMapper.insert(hsPaymentDiscount);
                //订单表
                hsOrder.setStatus(1);//已经结账了的订单表要改变状态
                hsOrderMapper.update(hsOrder);
                /**
                 * 员工业绩表
                 */
                Long hbasic;//发型师的基本工资
                //日期格式
                DateFormat fmt = new SimpleDateFormat("yyyy-MM");
                //技师基本工资表里面的相关查询和操作
                HsWageRules hst = new HsWageRules();
                hst.setRoleId(tech.getRoleId());
                hst.setLevel(hsOrder.getHaircare());
                List<HsWageRules> t1 = hsWageRulesMapper.queryAll(hst);
                for (HsWageRules t2 : t1){
                    //技师，护理的钱
                    BigDecimal a = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getHaircare()).getProjectPrice()));
                    BigDecimal b = new BigDecimal(String.valueOf(t2.getPercentage()));
                    Long c = a.multiply(b).longValue()/100;
                    HsSaleCommission tsc = new HsSaleCommission();
                    tsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                    tsc.setStaffId(tech.getStaffId());
                    tsc.setCommission(c);
                    hsSaleCommissionMapper.insert(tsc);
                    /**
                     * 技师工资表
                     */
                    //技师工资表
                    HsSalary hsSalary = new HsSalary();
                    hsSalary.setStaffId(tech.getStaffId());
                    hsSalary.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                    List<HsSalary> hsSalary1 = hsSalaryMapper.queryAll(hsSalary);
                    if (hsSalary1!=null && !hsSalary1.isEmpty()){ //技师,这年月已存在
                        //某年月的工资
                        for (HsSalary hsSalary2 : hsSalary1){
                            Long totalCom = hsSalary2.getTotalCommission();//总提成
                            Long totalWage = hsSalary2.getTotalWages();//总工资
                            BigDecimal tc = new BigDecimal(String.valueOf((totalCom)));//总提成
                            BigDecimal tw = new BigDecimal(String.valueOf((totalWage)));//总工资
                            //查询那一单的业绩
                            HsSaleCommission hsSaleCommission = new HsSaleCommission();
                            hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                            hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                            for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1){
                                Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                BigDecimal tm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                                Long td = tm.add(tc).longValue();//总提成的加法
                                Long te = tm.add(tw).longValue();//总工资的加法
                                hsSalary2.setTotalCommission(td);//更新总提成
                                hsSalary2.setTotalWages(te);//更新总工资
                                hsSalary2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                hsSalaryMapper.update(hsSalary2);//更新工资表
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hsSalary2.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }else { //技师，这年月不存在
                        //查询那一单的业绩
                        HsSaleCommission hsSaleCommission = new HsSaleCommission();
                        hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                        hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                        List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                        for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                            Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                            BigDecimal hm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                            //技师基本工资表里面的相关查询和操作
                            HsWageRules hw = new HsWageRules();
                            hw.setRoleId(tech.getRoleId());
                            hw.setLevel(hsOrder.getHaircare());
                            List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hw);
                            for (HsWageRules hw2 : hw1){
                                //发型师,项目的钱
                                hbasic = hw2.getBasicWage();//基本工资
                                BigDecimal ba = new BigDecimal(String.valueOf(hbasic));//基本工资
                                Long bc = ba.add(hm).longValue();//总工资的加法
                                HsSalary hsSalary10 = new HsSalary();
                                hsSalary10.setTotalWages(bc);//总工资
                                hsSalary10.setTotalCommission(com);//总提成
                                hsSalary10.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                hsSalary10.setStatus("0");//未发放状态
                                hsSalary10.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                hsSalary10.setStaffId(tech.getStaffId());//员工
                                hsSalaryMapper.insert(hsSalary10);//新增数据
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hsSalary10.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }
                }
                //发型师基本工资表里面的相关查询和操作
                HsWageRules hsh = new HsWageRules();
                hsh.setRoleId(hair.getRoleId());
                hsh.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                List<HsWageRules> h1 = hsWageRulesMapper.queryAll(hsh);
                for (HsWageRules h2 : h1){
                    //发型师,项目的钱
                    BigDecimal ba = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectPrice()));
                    BigDecimal bb = new BigDecimal(String.valueOf(h2.getPercentage()));
                    Long bc = ba.multiply(bb).longValue()/100;
                    HsSaleCommission hsc = new HsSaleCommission();
                    hsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                    hsc.setStaffId(hair.getStaffId());
                    hsc.setCommission(bc);
                    hsSaleCommissionMapper.insert(hsc);
                    /**
                     * 发型师工资表
                     */
                    //发型师工资表
                    HsSalary h = new HsSalary();
                    h.setStaffId(hair.getStaffId());
                    h.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                    List<HsSalary> hh1 = hsSalaryMapper.queryAll(h);
                    if (hh1!=null && !hh1.isEmpty()){ //发型师，这年月已存在
                        //某年月的工资
                        for (HsSalary  hh2: hh1) {
                            Long totalCom = hh2.getTotalCommission();//总提成
                            Long totalWage = hh2.getTotalWages();//总工资
                            BigDecimal hc = new BigDecimal(String.valueOf(totalCom));//总提成
                            BigDecimal hw = new BigDecimal(String.valueOf(totalWage));//总工资
                            //查询那一单的业绩
                            HsSaleCommission hsSaleCommission = new HsSaleCommission();
                            hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                            hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                            for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                                Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                BigDecimal hm = new BigDecimal(String.valueOf(com));//输出那一单的业绩
                                Long hd = hm.add(hc).longValue();//总提成的加法
                                Long he = hm.add(hw).longValue();//总工资的加法
                                hh2.setTotalCommission(hd);//更新总提成
                                hh2.setTotalWages(he);//更新总工资
                                hh2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                hsSalaryMapper.update(hh2);//更新工资表
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hh2.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }else { //发型师，这年月不存在
                        //查询那一单的业绩
                        HsSaleCommission hsSaleCommission = new HsSaleCommission();
                        hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                        hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                        List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                        for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                            Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                            BigDecimal hm = new BigDecimal(com);//输出那一单的业绩
                            //发型师基本工资表里面的相关查询和操作
                            HsWageRules hw = new HsWageRules();
                            hw.setRoleId(hair.getRoleId());
                            hw.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                            List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hsh);
                            for (HsWageRules hw2 : hw1){
                                //发型师,项目的钱
                                hbasic = hw2.getBasicWage();//基本工资
                                BigDecimal ba1 = new BigDecimal(String.valueOf(hbasic));//基本工资
                                Long bc1 = ba1.add(hm).longValue();//总工资的加法
                                HsSalary hs1 = new HsSalary();
                                hs1.setTotalCommission(com);//总提成
                                hs1.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                hs1.setStatus("0");//未发放状态
                                hs1.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                hs1.setTotalWages(bc1);//总工资
                                hs1.setStaffId(hair.getStaffId());//员工ID
                                hsSalaryMapper.insert(hs1);//新增数据
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hs1.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }
                }

            }else if (paymentMethodVo.getDiscountId()==3){  //选择了护理卡
                //会员表
                hsVip.setVipHaircare(hsVip.getVipHaircare()-1);
                hsVipMapper.update(hsVip);
                //付款支付表
                hsPaymentOrder.setPaymentMethod(paymentMethodVo.getPaymentMethod());
                hsPaymentOrder.setPhonePayment(paymentMethodVo.getPhonePayment());
                hsPaymentOrder.setTime(new Date());
                hsPaymentOrder.setUseDiscount(3);
                hsPaymentOrder.setStatus(1);
                hsPaymentOrder.setTotalPayment(paymentMethodVo.getPrice());
                hsPaymentOrderMapper.update(hsPaymentOrder);
                //付款优惠卡记录
                HsPaymentDiscount hsPaymentDiscount = new HsPaymentDiscount();
                hsPaymentDiscount.setHsPaymentOrder(hsPaymentOrder);
                hsPaymentDiscount.setDiscountId(3);
                hsPaymentDiscount.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                hsPaymentDiscount.setCount(1);
                hsPaymentDiscountMapper.insert(hsPaymentDiscount);
                //订单表
                hsOrder.setStatus(1);//已经结账了的订单表要改变状态
                hsOrderMapper.update(hsOrder);
                /**
                 * 员工业绩表
                 */
                Long hbasic;//发型师的基本工资
                //日期格式
                DateFormat fmt = new SimpleDateFormat("yyyy-MM");
                //技师基本工资表里面的相关查询和操作
                HsWageRules hst = new HsWageRules();
                hst.setRoleId(tech.getRoleId());
                hst.setLevel(hsOrder.getHaircare());
                List<HsWageRules> t1 = hsWageRulesMapper.queryAll(hst);
                for (HsWageRules t2 : t1){
                    //技师，护理的钱
                    BigDecimal a = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getHaircare()).getProjectPrice()));
                    BigDecimal b = new BigDecimal(String.valueOf(t2.getPercentage()));
                    Long c = a.multiply(b).longValue()/100;
                    HsSaleCommission tsc = new HsSaleCommission();
                    tsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                    tsc.setStaffId(tech.getStaffId());
                    tsc.setCommission(c);
                    hsSaleCommissionMapper.insert(tsc);
                    /**
                     * 技师工资表
                     */
                    //技师工资表
                    HsSalary hsSalary = new HsSalary();
                    hsSalary.setStaffId(tech.getStaffId());
                    hsSalary.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                    List<HsSalary> hsSalary1 = hsSalaryMapper.queryAll(hsSalary);
                    if (hsSalary1!=null && !hsSalary1.isEmpty()){ //技师,这年月已存在
                        //某年月的工资
                        for (HsSalary hsSalary2 : hsSalary1){
                            Long totalCom = hsSalary2.getTotalCommission();//总提成
                            Long totalWage = hsSalary2.getTotalWages();//总工资
                            BigDecimal tc = new BigDecimal(String.valueOf((totalCom)));//总提成
                            BigDecimal tw = new BigDecimal(String.valueOf((totalWage)));//总工资
                            //查询那一单的业绩
                            HsSaleCommission hsSaleCommission = new HsSaleCommission();
                            hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                            hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                            for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1){
                                Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                BigDecimal tm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                                Long td = tm.add(tc).longValue();//总提成的加法
                                Long te = tm.add(tw).longValue();//总工资的加法
                                hsSalary2.setTotalCommission(td);//更新总提成
                                hsSalary2.setTotalWages(te);//更新总工资
                                hsSalary2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                hsSalaryMapper.update(hsSalary2);//更新工资表
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hsSalary2.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }else { //技师，这年月不存在
                        //查询那一单的业绩
                        HsSaleCommission hsSaleCommission = new HsSaleCommission();
                        hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                        hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                        List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                        for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                            Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                            BigDecimal hm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                            //技师基本工资表里面的相关查询和操作
                            HsWageRules hw = new HsWageRules();
                            hw.setRoleId(tech.getRoleId());
                            hw.setLevel(hsOrder.getHaircare());
                            List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hw);
                            for (HsWageRules hw2 : hw1){
                                //发型师,项目的钱
                                hbasic = hw2.getBasicWage();//基本工资
                                BigDecimal ba = new BigDecimal(String.valueOf(hbasic));//基本工资
                                Long bc = ba.add(hm).longValue();//总工资的加法
                                HsSalary hsSalary10 = new HsSalary();
                                hsSalary10.setTotalWages(bc);//总工资
                                hsSalary10.setTotalCommission(com);//总提成
                                hsSalary10.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                hsSalary10.setStatus("0");//未发放状态
                                hsSalary10.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                hsSalary10.setStaffId(tech.getStaffId());//员工
                                hsSalaryMapper.insert(hsSalary10);//新增数据
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hsSalary10.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }
                }
                //发型师基本工资表里面的相关查询和操作
                HsWageRules hsh = new HsWageRules();
                hsh.setRoleId(hair.getRoleId());
                hsh.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                List<HsWageRules> h1 = hsWageRulesMapper.queryAll(hsh);
                for (HsWageRules h2 : h1){
                    //发型师,项目的钱
                    BigDecimal ba = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectPrice()));
                    BigDecimal bb = new BigDecimal(String.valueOf(h2.getPercentage()));
                    Long bc = ba.multiply(bb).longValue()/100;
                    HsSaleCommission hsc = new HsSaleCommission();
                    hsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                    hsc.setStaffId(hair.getStaffId());
                    hsc.setCommission(bc);
                    hsSaleCommissionMapper.insert(hsc);
                    /**
                     * 发型师工资表
                     */
                    //发型师工资表
                    HsSalary h = new HsSalary();
                    h.setStaffId(hair.getStaffId());
                    h.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                    List<HsSalary> hh1 = hsSalaryMapper.queryAll(h);
                    if (hh1!=null && !hh1.isEmpty()){ //发型师，这年月已存在
                        //某年月的工资
                        for (HsSalary  hh2: hh1) {
                            Long totalCom = hh2.getTotalCommission();//总提成
                            Long totalWage = hh2.getTotalWages();//总工资
                            BigDecimal hc = new BigDecimal(String.valueOf(totalCom));//总提成
                            BigDecimal hw = new BigDecimal(String.valueOf(totalWage));//总工资
                            //查询那一单的业绩
                            HsSaleCommission hsSaleCommission = new HsSaleCommission();
                            hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                            hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                            for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                                Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                BigDecimal hm = new BigDecimal(String.valueOf(com));//输出那一单的业绩
                                Long hd = hm.add(hc).longValue();//总提成的加法
                                Long he = hm.add(hw).longValue();//总工资的加法
                                hh2.setTotalCommission(hd);//更新总提成
                                hh2.setTotalWages(he);//更新总工资
                                hh2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                hsSalaryMapper.update(hh2);//更新工资表
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hh2.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }else { //发型师，这年月不存在
                        //查询那一单的业绩
                        HsSaleCommission hsSaleCommission = new HsSaleCommission();
                        hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                        hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                        List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                        for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                            Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                            BigDecimal hm = new BigDecimal(com);//输出那一单的业绩
                            //发型师基本工资表里面的相关查询和操作
                            HsWageRules hw = new HsWageRules();
                            hw.setRoleId(hair.getRoleId());
                            hw.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                            List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hsh);
                            for (HsWageRules hw2 : hw1){
                                //发型师,项目的钱
                                hbasic = hw2.getBasicWage();//基本工资
                                BigDecimal ba1 = new BigDecimal(String.valueOf(hbasic));//基本工资
                                Long bc1 = ba1.add(hm).longValue();//总工资的加法
                                HsSalary hs1 = new HsSalary();
                                hs1.setTotalCommission(com);//总提成
                                hs1.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                hs1.setStatus("0");//未发放状态
                                hs1.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                hs1.setTotalWages(bc1);//总工资
                                hs1.setStaffId(hair.getStaffId());//员工ID
                                hsSalaryMapper.insert(hs1);//新增数据
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hs1.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }
                }
            }
        }else if(paymentMethodVo.getDiscountId()==0){  //没有使用优惠卡，没有使用会员卡
            if (paymentMethodVo.getPaymentMethod()!=4){
                //付款支付表
                hsPaymentOrder.setPaymentMethod(paymentMethodVo.getPaymentMethod());
                hsPaymentOrder.setPhonePayment(paymentMethodVo.getPhonePayment());
                hsPaymentOrder.setTime(new Date());
                hsPaymentOrder.setStatus(1);
                hsPaymentOrder.setTotalPayment(paymentMethodVo.getPrice());
                hsPaymentOrderMapper.update(hsPaymentOrder);
                //订单表
                hsOrder.setStatus(1);//已经结账了的订单表要改变状态
                hsOrderMapper.update(hsOrder);
                /**
                 * 员工业绩表
                 */
                Long hbasic;//发型师的基本工资
                //日期格式
                DateFormat fmt = new SimpleDateFormat("yyyy-MM");
                //技师基本工资表里面的相关查询和操作
                HsWageRules hst = new HsWageRules();
                hst.setRoleId(tech.getRoleId());
                hst.setLevel(hsOrder.getHaircare());
                List<HsWageRules> t1 = hsWageRulesMapper.queryAll(hst);
                for (HsWageRules t2 : t1){
                    //技师，护理的钱
                    BigDecimal a = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getHaircare()).getProjectPrice()));
                    BigDecimal b = new BigDecimal(String.valueOf(t2.getPercentage()));
                    Long c = a.multiply(b).longValue()/100;
                    HsSaleCommission tsc = new HsSaleCommission();
                    tsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                    tsc.setStaffId(tech.getStaffId());
                    tsc.setCommission(c);
                    hsSaleCommissionMapper.insert(tsc);
                    /**
                     * 技师工资表
                     */
                    //技师工资表
                    HsSalary hsSalary = new HsSalary();
                    hsSalary.setStaffId(tech.getStaffId());
                    hsSalary.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                    List<HsSalary> hsSalary1 = hsSalaryMapper.queryAll(hsSalary);
                    if (hsSalary1!=null && !hsSalary1.isEmpty()){ //技师,这年月已存在
                        //某年月的工资
                        for (HsSalary hsSalary2 : hsSalary1){
                            Long totalCom = hsSalary2.getTotalCommission();//总提成
                            Long totalWage = hsSalary2.getTotalWages();//总工资
                            BigDecimal tc = new BigDecimal(String.valueOf((totalCom)));//总提成
                            BigDecimal tw = new BigDecimal(String.valueOf((totalWage)));//总工资
                            //查询那一单的业绩
                            HsSaleCommission hsSaleCommission = new HsSaleCommission();
                            hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                            hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                            for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1){
                                Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                BigDecimal tm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                                Long td = tm.add(tc).longValue();//总提成的加法
                                Long te = tm.add(tw).longValue();//总工资的加法
                                hsSalary2.setTotalCommission(td);//更新总提成
                                hsSalary2.setTotalWages(te);//更新总工资
                                hsSalary2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                hsSalaryMapper.update(hsSalary2);//更新工资表
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hsSalary2.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }else { //技师，这年月不存在
                        //查询那一单的业绩
                        HsSaleCommission hsSaleCommission = new HsSaleCommission();
                        hsSaleCommission.setStaffId(tech.getStaffId()); //技师
                        hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                        List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                        for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                            Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                            BigDecimal hm = new BigDecimal(String.valueOf((com)));//输出那一单的业绩
                            //技师基本工资表里面的相关查询和操作
                            HsWageRules hw = new HsWageRules();
                            hw.setRoleId(tech.getRoleId());
                            hw.setLevel(hsOrder.getHaircare());
                            List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hw);
                            for (HsWageRules hw2 : hw1){
                                //发型师,项目的钱
                                hbasic = hw2.getBasicWage();//基本工资
                                BigDecimal ba = new BigDecimal(String.valueOf(hbasic));//基本工资
                                Long bc = ba.add(hm).longValue();//总工资的加法
                                HsSalary hsSalary10 = new HsSalary();
                                hsSalary10.setTotalWages(bc);//总工资
                                hsSalary10.setTotalCommission(com);//总提成
                                hsSalary10.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                hsSalary10.setStatus("0");//未发放状态
                                hsSalary10.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                hsSalary10.setStaffId(tech.getStaffId());//员工
                                hsSalaryMapper.insert(hsSalary10);//新增数据
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hsSalary10.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(tsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }
                }
                //发型师基本工资表里面的相关查询和操作
                HsWageRules hsh = new HsWageRules();
                hsh.setRoleId(hair.getRoleId());
                hsh.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                List<HsWageRules> h1 = hsWageRulesMapper.queryAll(hsh);
                for (HsWageRules h2 : h1){
                    //发型师,项目的钱
                    BigDecimal ba = new BigDecimal(String.valueOf(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectPrice()));
                    BigDecimal bb = new BigDecimal(String.valueOf(h2.getPercentage()));
                    Long bc = ba.multiply(bb).longValue()/100;
                    HsSaleCommission hsc = new HsSaleCommission();
                    hsc.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                    hsc.setStaffId(hair.getStaffId());
                    hsc.setCommission(bc);
                    hsSaleCommissionMapper.insert(hsc);
                    /**
                     * 发型师工资表
                     */
                    //发型师工资表
                    HsSalary h = new HsSalary();
                    h.setStaffId(hair.getStaffId());
                    h.setPayTime(fmt.format(hsPaymentOrder.getTime()));
                    List<HsSalary> hh1 = hsSalaryMapper.queryAll(h);
                    if (hh1!=null && !hh1.isEmpty()){ //发型师，这年月已存在
                        //某年月的工资
                        for (HsSalary  hh2: hh1) {
                            Long totalCom = hh2.getTotalCommission();//总提成
                            Long totalWage = hh2.getTotalWages();//总工资
                            BigDecimal hc = new BigDecimal(String.valueOf(totalCom));//总提成
                            BigDecimal hw = new BigDecimal(String.valueOf(totalWage));//总工资
                            //查询那一单的业绩
                            HsSaleCommission hsSaleCommission = new HsSaleCommission();
                            hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                            hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                            List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                            for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                                Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                                BigDecimal hm = new BigDecimal(String.valueOf(com));//输出那一单的业绩
                                Long hd = hm.add(hc).longValue();//总提成的加法
                                Long he = hm.add(hw).longValue();//总工资的加法
                                hh2.setTotalCommission(hd);//更新总提成
                                hh2.setTotalWages(he);//更新总工资
                                hh2.setPayTime(fmt.format(hsPaymentOrder.getTime()));//更新计算时间
                                hsSalaryMapper.update(hh2);//更新工资表
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hh2.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }else { //发型师，这年月不存在
                        //查询那一单的业绩
                        HsSaleCommission hsSaleCommission = new HsSaleCommission();
                        hsSaleCommission.setStaffId(hair.getStaffId()); //发型师
                        hsSaleCommission.setPaymentOrderId(hsPaymentOrder.getPaymentOrderId());
                        List<HsSaleCommission> hsSaleCommission1 = hsSaleCommissionMapper.queryAll(hsSaleCommission);
                        for (HsSaleCommission hsSaleCommission2 : hsSaleCommission1) {
                            Long com = hsSaleCommission2.getCommission(); //输出那一单的业绩
                            BigDecimal hm = new BigDecimal(com);//输出那一单的业绩
                            //发型师基本工资表里面的相关查询和操作
                            HsWageRules hw = new HsWageRules();
                            hw.setRoleId(hair.getRoleId());
                            hw.setLevel(Integer.parseInt(hsProjectMapper.queryById(hsOrder.getProjectId()).getProjectLevel()));
                            List<HsWageRules> hw1 = hsWageRulesMapper.queryAll(hsh);
                            for (HsWageRules hw2 : hw1){
                                //发型师,项目的钱
                                hbasic = hw2.getBasicWage();//基本工资
                                BigDecimal ba1 = new BigDecimal(String.valueOf(hbasic));//基本工资
                                Long bc1 = ba1.add(hm).longValue();//总工资的加法
                                HsSalary hs1 = new HsSalary();
                                hs1.setTotalCommission(com);//总提成
                                hs1.setPayTime(fmt.format(hsPaymentOrder.getTime()));//计算工资时间
                                hs1.setStatus("0");//未发放状态
                                hs1.setWageRulesId(hsWageRulesMapper.queryByRoleId(Integer.parseInt(hsProject.getProjectLevel())).getWageRulesId());//工资规则
                                hs1.setTotalWages(bc1);//总工资
                                hs1.setStaffId(hair.getStaffId());//员工ID
                                hsSalaryMapper.insert(hs1);//新增数据
                                HsSalaryCom hsSalaryCom = new HsSalaryCom();
                                hsSalaryCom.setSalaryId(hs1.getSalaryId());//工资ID
                                hsSalaryCom.setSaleCommissionId(hsc.getSaleCommissionId());//业绩ID
                                hsSalaryComMapper.insert(hsSalaryCom);//工资业绩表的插入
                            }
                        }
                    }
                }
            }else if (paymentMethodVo.getPaymentMethod()==4){
                throw new ServiceException("无法使用会员卡");
            }
        }
//        else if (hsVip==null){
//            throw new ServiceException("无法使用会员卡");
//        }
        return hsPaymentOrder;
    }
}
