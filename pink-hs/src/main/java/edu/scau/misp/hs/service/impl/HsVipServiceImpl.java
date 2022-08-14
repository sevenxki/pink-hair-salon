package edu.scau.misp.hs.service.impl;

import ch.qos.logback.classic.net.server.HardenedLoggingEventInputStream;
import com.alibaba.fastjson.JSONArray;
import edu.scau.misp.hs.entity.HsOrder;
import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.mapper.HsVipMapper;
import edu.scau.misp.hs.service.HsVipService;
import edu.scau.misp.hs.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * (HsVip)表服务实现类
 *
 */
@Service
public class HsVipServiceImpl implements HsVipService {
    @Autowired
    private HsVipMapper hsVipMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param vipId 主键
     * @return 实例对象
     */
    @Override
    public HsVip queryById(Integer vipId) {
        return this.hsVipMapper.queryById(vipId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsVip 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsVip> queryAll(HsVip hsVip) {
//        改数据类型
//        List<HsVip> hsVip1=this.hsVipMapper.queryAll(hsVip);
//        for (HsVip hsVip2:hsVip1){
//            if(hsVip2.getVipGender()==1){
//                hsVip2.setVipGender("男");
//            }else hsVip2.setVipGender("女");
//        }
//        return hsVip1;
        return this.hsVipMapper.queryAll(hsVip);
    }

    /**
     * 新增数据
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsVip hsVip) {
        this.hsVipMapper.insert(hsVip);
    }

    /**
     * 修改数据
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsVip hsVip) {
        this.hsVipMapper.update(hsVip);
    }

    /**
     * 通过主键删除数据
     *
     * @param vipId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer vipId) {
        return this.hsVipMapper.deleteById(vipId) > 0;
    }

    /**
     *充值服务之优惠计算
     * @param
     * @return
     */
    public String money(String money) {
        //充值余额数目
        //更改了优惠方式：充500以上送其百分之10 以此类推
        String money2;
        Double money1=Double.valueOf(money);
        if(money1>=500&&money1<1000){
            money1=money1*1.1;
        }else if(money1>=1000&&money1<2000){
            money1=money1*1.12;
        }else if(money1>=2000){
            money1=money1*1.15;
        }
        return money2=String.valueOf(money1);
    }

//    /**
//     * 计算优惠卡的钱
//     *
//     * @param
//     * @return 实例对象
//     */
////    @Override
//    public String discount(@RequestParam("array") JSONArray discount) {
//        //理发卡的数量+护理卡的数量
//        String[] discount1 = new String[2];
//        for (int i = 0; i < 2; i++) {
//            System.out.println(discount.get(i));
//            discount1[i] = String.valueOf(discount.get(i));
//            System.out.println(discount1[i]);
//        }
//        Double money = Double.valueOf(discount1[0])*150+Double.valueOf(discount1[2])*200;
//        return String.valueOf(money);
//    }
//
//    /**
//     * 优惠卡下单
//     *
//     * @param
//     * @return 实例对象
//     */
////    @Override
//    public void group(@RequestParam("array") JSONArray group) {
//        //手机号+多少张理发优惠卡+多少张护理优惠卡
//        String[] group1 = new String[3];
//        for (int i = 0; i < 3; i++) {
//            System.out.println(group.get(i));
//            group1[i] = String.valueOf(group.get(i));
//            System.out.println(group1[i]);
//        }
//        HsVip hsVip1 = new HsVip();
//        Integer haircut = Integer.valueOf(group1[1])*3;
//        hsVip1.setVipHaircut(haircut+this.hsVipMapper.queryAllByPhone(group1[1]).getVipHaircut());
//        Integer haircare = Integer.valueOf(group1[2])*3;
//        hsVip1.setVipHaircare(haircare+this.hsVipMapper.queryAllByPhone(group1[1]).getVipHaircare());
//        hsVip1.setVipId(this.hsVipMapper.queryAllByPhone(group1[0]).getVipId());
//        this.hsVipMapper.update(hsVip1);
//    }
//
//    /**
//     * 充值余额
//     *
//     * @param
//     * @return 实例对象
//     */
////    @Override
//    public void recharge(@RequestParam("array") JSONArray recharge) {
//        //手机号+充值的钱
//        String[] recharge1 = new String[2];
//        for (int i = 0; i < 2; i++) {
//            System.out.println(recharge.get(i));
//            recharge1[i] = String.valueOf(recharge.get(i));
//            System.out.println(recharge1[i]);
//        }
//        HsVip hsVip1 = new HsVip();
//        hsVip1.setVipId(this.hsVipMapper.queryAllByPhone(recharge1[0]).getVipId());
//        Double money = this.hsVipMapper.queryById(hsVip1.getVipId()).getVipBalance();
//        hsVip1.setVipBalance(Double.valueOf(money(recharge1[1]))+money);
//        this.hsVipMapper.update(hsVip1);
//    }

    /**
     * 充值优惠卡
     *
     * @param
     * @return 实例对象
     */
    @Override
    public void group(HsVip hsVip) {
        //手机号+多少张理发优惠卡+多少张护理优惠卡
        HsVip hsVip1 = new HsVip();
        hsVip1.setVipId(this.hsVipMapper.queryAllByPhone(hsVip.getVipPhone()).getVipId());
        Integer haircut = 0;
        Integer haircare = 0;
        if(hsVip.getVipHaircut()!=null){
            haircut=hsVip.getVipHaircut();
        }
        if(hsVip.getVipHaircare()!=null){
            haircare=hsVip.getVipHaircare();
        }
        hsVip1.setVipHaircut(this.hsVipMapper.queryAllByPhone(hsVip.getVipPhone()).getVipHaircut()+haircut*3);
        hsVip1.setVipHaircare(this.hsVipMapper.queryAllByPhone(hsVip.getVipPhone()).getVipHaircare()+haircare*3);
        this.hsVipMapper.update(hsVip1);
    }

    /**
     * 充值余额
     *
     * @param
     * @return 实例对象
     */
    @Override
    public void recharge(HsVip hsVip) {
        //手机号+充值的钱
        HsVip hsVip1 = new HsVip();
        Integer id = this.hsVipMapper.queryAllByPhone(hsVip.getVipPhone()).getVipId();
        hsVip1.setVipId(id);
        hsVip1.setVipBalance(this.hsVipMapper.queryById(id).getVipBalance()+Double.valueOf(money(String.valueOf(hsVip.getVipBalance()))));
        this.hsVipMapper.update(hsVip1);
    }


    /**
     * 通过电话找到全部
     *
     * @param
     * @return 实例对象
     */
    @Override
    public HsVip queryAllByPhone(String vipPhone){
        return this.hsVipMapper.queryAllByPhone(vipPhone);
    }

    /**
     * 新增vip
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public void addNewVip(HsVip hsVip) {
        HsVip hsVip1 = new HsVip();
        hsVip1.setVipId(hsVip.getVipId());
        hsVip1.setVipName(hsVip.getVipName());
        hsVip1.setVipGender(hsVip.getVipGender());
        hsVip1.setVipBirthday(hsVip.getVipBirthday());
        hsVip1.setVipPhone(hsVip.getVipPhone());
        Double balance =0.00;
        if(hsVip.getVipBalance()!=null){
            balance = hsVip.getVipBalance();
        }
        hsVip1.setVipBalance(Double.valueOf(money(String.valueOf(balance))));
        Integer haircut = 0;
        Integer haircare = 0;
        if(hsVip.getVipHaircut()!=null){
            haircut=hsVip.getVipHaircut();
        }
        if(hsVip.getVipHaircare()!=null){
            haircare=hsVip.getVipHaircare();
        }
        hsVip1.setVipHaircut(haircut*3);
        hsVip1.setVipHaircare(haircare*3);
        this.hsVipMapper.insert(hsVip1);
    }

    /**
     * 删除vip
     *
     * @param vipPhone
     * @return 删除是否成功
     */
    @Override
    public Boolean deleteVip(@PathVariable String vipPhone) {
        return this.hsVipMapper.deleteVip(vipPhone)> 0;
    }

}