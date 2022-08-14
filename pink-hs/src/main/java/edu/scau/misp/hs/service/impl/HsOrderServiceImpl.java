package edu.scau.misp.hs.service.impl;



import com.alibaba.fastjson.JSONArray;
import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.hs.service.HsOrderService;
import edu.scau.misp.hs.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-03-29 22:17:29
 */
@Service("hsOrderService")
public class HsOrderServiceImpl implements HsOrderService{
    @Resource
    private HsOrderMapper hsOrderMapper;
    @Resource
    private HsProjectMapper hsProjectMapper;
    @Resource
    private HsKindMapper hsKindMapper;
    @Resource
    private HsStaffMapper hsStaffMapper;
    @Resource
    private HsUserMapper hsUserMapper;
    @Resource
    private HsPaymentOrderMapper hsPaymentOrderMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param  orderId
     * @return 实例对象
     */
    @Override
    public HsOrder queryById(Integer orderId) {
        HsOrder hsOrder1 = hsOrderMapper.queryById(orderId);
        hsOrder1.setHaircareName(hsProjectMapper.queryById(hsOrder1.getHaircare()).getProjectName());
        hsOrder1.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder1.getStaffHairstylistId()).getStaffName());
        HsUser hsUser = hsUserMapper.queryById(hsOrder1.getUserId());
        HsStaff hsStaff = hsStaffMapper.queryByuserName(hsUser.getUsername());
        hsOrder1.setUserStaffName(hsStaff.getStaffName());
        hsOrderMapper.update(hsOrder1);
        return  hsOrder1;
    }

    /***
     *
     * @param projectName
     * @return
     */
    @Override
    public List<HsOrder> queryByProjectName(String projectName) {
        List<HsOrder> list = this.hsOrderMapper.queryByProjectName(projectName);
        for (HsOrder hsOrder1:list){
            hsOrder1.setHaircareName(hsProjectMapper.queryById(hsOrder1.getHaircare()).getProjectName());
            hsOrder1.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder1.getStaffHairstylistId()).getStaffName());
            hsOrderMapper.update(hsOrder1);
        }
        return list;
    }

    /**
     *
     * @param hsOrder 筛选条件
     * @return
     */
    @Override
    public List<HsOrder> queryAll(HsOrder hsOrder) {
        List<HsOrder> list = this.hsOrderMapper.queryAll(hsOrder);
        for (HsOrder hsOrder1:list){
            hsOrder1.setHaircareName(hsProjectMapper.queryById(hsOrder1.getHaircare()).getProjectName());
            hsOrder1.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder1.getStaffHairstylistId()).getStaffName());
            HsUser hsUser = hsUserMapper.queryById(hsOrder1.getUserId());
            HsStaff hsStaff = hsStaffMapper.queryByuserName(hsUser.getUsername());
            hsOrder1.setUserStaffName(hsStaff.getStaffName());
            hsOrderMapper.update(hsOrder1);
        }
        return list;
    }

    /**
     * 新增数据
     *
     * @param hsOrder 实例对象
     * @return 实例对象
     */
    @Override
    public HsOrder insert(HsOrder hsOrder) {
        this.hsOrderMapper.insert(hsOrder);
        return hsOrder;
    }

    /**
     * 修改数据
     *
     * @param hsOrder 实例对象
     * @return 实例对象
     */
    @Override
    public HsOrder update(HsOrder hsOrder) {
        this.hsOrderMapper.update(hsOrder);
        return this.queryById(hsOrder.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer orderId) {
        if (hsPaymentOrderMapper.queryByOrder(orderId)!=null){
            hsPaymentOrderMapper.deleteById(hsPaymentOrderMapper.queryByOrder(orderId).getPaymentOrderId());
        }
        return this.hsOrderMapper.deleteById(orderId) > 0;
    }

    /**
     * 查询所有项目类型名字
     *
     * @return 查询结果
     */
    @Override
    public List<String> queryAllKind(){
        return this.hsKindMapper.queryAllKind();
    }

    /**
     * 用项目类型名字查询项目名字
     *
     * @return 查询结果
     */
    @Override
    public List<String> queryAllProjectNameByKind(String kindName) {
        return this.hsProjectMapper.queryAllProjectNameByKind(kindName);
    }
    /**
     * 用项目类型名字查询项目all
     *
     * @return 查询结果
     */
    @Override
    public List<HsProject> queryAllProjectAllByKind(String kindName) {
        return this.hsProjectMapper.queryAllProjectAllByKind(kindName);
    }

    /**
     *计算金额
     * @param
     * @return
     */
    @Override
    public String countTotal(@RequestParam("array") JSONArray order){
        //前端将数组传入,数组为JSONArray类型
        //JSONArray是由[{JSONObject}{JSONObject}{JSONObject}...]这样的
        //所以需要转成String[]
        String[] order1=new String[5];
        for(int i=0;i<5;i++) {
           System.out.println(order.get(i));
           order1[i] = String.valueOf(order.get(i));
            System.out.println(order1[i]);
        }
        //先按照项目的价钱来定义总价格
        Double total = this.hsProjectMapper.queryPriceByProjectName(order1[1]).getProjectPrice();
        //再通过匹配查看是否需要高级护理
        String h="高级护理";
        if(order1[2].equals(h)){
            total=total+100.0;
        }
        System.out.println(total);
        String a= String.valueOf(total);
        System.out.println(a);
        return a;
    }

    /**
     *护理项目name
     * @param
     * @return
     */
    @Override
     public List<String> queryAllHairCare() {
         return this.hsProjectMapper.queryAllHairCare();
     }

    /**
     *护理项目all
     * @param
     * @return
     */
    @Override
    public List<HsProject> queryAllHairCareAll() {
        return this.hsProjectMapper.queryAllHairCareAll();
    }

    /**
     *查找技师name
     * @param
     * @return
     */
    @Override
    public List<String> queryTechnician() {
        return this.hsStaffMapper.queryTechnician();
    }

    /**
     * 查找技师ALL
     * @param
     * @return
     */
    @Override
    public List<HsStaff> queryTechnicianAll() {
        return this.hsStaffMapper.queryTechnicianAll();
    }

    /**
     * 查找对应等级的员工name
     *
     * @return 查询结果
     */
    @Override
    public List<String> queryStaffByLevel(String projectName){
        return this.hsProjectMapper.queryStaffByLevel(projectName);
    }
    /**
     * 查找对应等级的员工all
     *
     * @return 查询结果
     */
    @Override
    public List<HsStaff> queryStaffByLevelAll(String projectName){
        return this.hsProjectMapper.queryStaffByLevelAll(projectName);
    }


    /**
     *下单
     * @param
     * @return
     */
    @Override
    public void placeOrder(@RequestParam("array") JSONArray placeOrder) {
        //和计算金额一样，前端将数组传入,数组为JSONArray类型
        //JSONArray是由[{JSONObject}{JSONObject}{JSONObject}...]这样的
        //所以需要转成String[]
        String[] placeOrder1 = new String[6];
        for (int i = 0; i < 6; i++) {
            System.out.println(placeOrder.get(i));
            placeOrder1[i] = String.valueOf(placeOrder.get(i));
            System.out.println(placeOrder1[i]);
        }
        //定义新HsOrder，将转换了的数据再放入，最后插入数据
        HsOrder hsOrder = new HsOrder();
        hsOrder.setProjectId(hsProjectMapper.queryProjectIdByName(placeOrder1[0]).getProjectId());
        String h="高级护理";
        int n;
        if(placeOrder1[1].equals(h)){
            n=5;
        }else n=4;
        hsOrder.setHaircare(n);
        hsOrder.setStaffHairstylistId(hsStaffMapper.queryStaffIdByName(placeOrder1[2]).getStaffId());
        hsOrder.setStaffTechnicianId(hsStaffMapper.queryStaffIdByName(placeOrder1[3]).getStaffId());
        int m=0;
        hsOrder.setStatus(m);
        double x=Double.valueOf(placeOrder1[4]);
        hsOrder.setTotal(x);
        hsOrder.setOrderCreatetime(DateUtils.getNowDate());
        HsStaff hsStaff = this.hsStaffMapper.queryStaffIdByName(placeOrder1[5]);
        HsUser hsUser = this.hsUserMapper.queryByUsername(hsStaff.getUsername());
        hsOrder.setUserId(hsUser.getUserId());

        this.hsOrderMapper.insert(hsOrder);
    }

    /**
     * 查询未支付订单
     * @param hsOrder
     * @return
     */
    @Override
    public List<HsOrder> queryNoPayment(HsOrder hsOrder) {
        List<HsOrder> list = hsOrderMapper.queryNoPayment(hsOrder);
        for (HsOrder hsOrder1:list){
            hsOrder1.setHaircareName(hsProjectMapper.queryById(hsOrder1.getHaircare()).getProjectName());
            hsOrder1.setStaffHairstylistName(hsStaffMapper.queryById(hsOrder1.getStaffHairstylistId()).getStaffName());
            hsOrder1.setUserStaffName(hsStaffMapper.queryByuserName(hsUserMapper.queryById(hsOrder1.getUserId()).getUsername()).getStaffName());
            hsOrderMapper.update(hsOrder1);
        }
        return list;
    }
}
