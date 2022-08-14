package edu.scau.misp.hs.service.impl;

import com.alibaba.fastjson.annotation.JSONField;
import edu.scau.misp.hs.entity.HsGrouporder;
import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.mapper.HsGrouporderMapper;
import edu.scau.misp.hs.mapper.HsPaymentOrderMapper;
import edu.scau.misp.hs.service.HsGrouporderService;
import edu.scau.misp.hs.util.DateUtils;
import edu.scau.misp.hs.vo.DashboardVo;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (HsGrouporder)表服务实现类
 *
 */
@Service
public class HsGrouporderServiceImpl implements HsGrouporderService {
    @Autowired
    private HsGrouporderMapper hsGrouporderMapper;
    @Autowired
    private HsPaymentOrderMapper hsPaymentOrderMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param grouporderId 主键
     * @return 实例对象
     */
    @Override
    public HsGrouporder queryById(Integer grouporderId) {
        return this.hsGrouporderMapper.queryById(grouporderId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsGrouporder 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsGrouporder> queryAll(HsGrouporder hsGrouporder) {
        return this.hsGrouporderMapper.queryAll(hsGrouporder);
    }

    /**
     * 新增数据
     *
     * @param hsGrouporder 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsGrouporder hsGrouporder) {
        this.hsGrouporderMapper.insert(hsGrouporder);
    }

    /**
     * 修改数据
     *
     * @param hsGrouporder 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsGrouporder hsGrouporder) {
        this.hsGrouporderMapper.update(hsGrouporder);
    }

    /**
     * 通过主键删除数据
     *
     * @param grouporderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer grouporderId) {
        return this.hsGrouporderMapper.deleteById(grouporderId) > 0;
    }

    /**
     * 新增vip订单
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public String addNewGroup(HsVip hsVip) {
        HsGrouporder hsGrouporder = new HsGrouporder();
        String no = DateUtils.dateTimeNow();
        hsGrouporder.setGrouporderNo(no);
        hsGrouporder.setGrouporderDatetime(DateUtils.getNowDate());
        Double a = 0.0;
        Integer b = 0;
        Integer c = 0;
        if(hsVip.getVipBalance()!=null){
            a=hsVip.getVipBalance();
        }
        if(hsVip.getVipHaircut()!=null){
            b=hsVip.getVipHaircut();
        }
        if(hsVip.getVipHaircare()!=null){
            c=hsVip.getVipHaircare();
        }

        Double money = b*150+c*200+a;
        hsGrouporder.setGrouporderTotal(money);
        this.hsGrouporderMapper.insert(hsGrouporder);
        return no;
    }



}