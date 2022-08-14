package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsAccumulation;
import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.mapper.HsAccumulationMapper;
import edu.scau.misp.hs.mapper.HsVipMapper;
import edu.scau.misp.hs.service.HsAccumulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (HsAccumulation)表服务实现类
 *
 */
@Service("hsAccumulation")
public class HsAccumulationServiceImpl implements HsAccumulationService {
    @Autowired
    private HsAccumulationMapper hsAccumulationMapper;
    @Autowired
    private HsVipMapper hsVipMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param accumulationId 主键
     * @return 实例对象
     */
    @Override
    public HsAccumulation queryById(Integer accumulationId) {
        return this.hsAccumulationMapper.queryById(accumulationId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsAccumulation 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsAccumulation> queryAll(HsAccumulation hsAccumulation) {
        return this.hsAccumulationMapper.queryAll(hsAccumulation);
    }

    /**
     * 新增数据
     *
     * @param hsAccumulation 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsAccumulation hsAccumulation) {
        this.hsAccumulationMapper.insert(hsAccumulation);
    }

    /**
     * 修改数据
     *
     * @param hsAccumulation 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsAccumulation hsAccumulation) {
        this.hsAccumulationMapper.update(hsAccumulation);
    }

    /**
     * 通过主键删除数据
     *
     * @param accumulationId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer accumulationId) {
        return this.hsAccumulationMapper.deleteById(accumulationId) > 0;
    }

    /**
     * 通过vipPhone删除数据
     *
     * @param
     * @return 是否成功
     */
    @Override
    public void deleteByVipPhone(String vipPhone) {
        Integer vipId = this.hsVipMapper.queryAllByPhone(vipPhone).getVipId();
        this.hsAccumulationMapper.deleteById(this.hsAccumulationMapper.queryByVipId(vipId).getAccumulationId());
    }

    /**
     * 新增累积
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public void addNewAccumulation(HsVip hsVip){
        HsAccumulation hsAccumulation = new HsAccumulation();
        hsAccumulation.setVipId(this.hsVipMapper.queryAllByPhone(hsVip.getVipPhone()).getVipId());
        Integer haircut = 0;
        Integer haircare = 0;
        Double balance = 0.0;
        if(hsVip.getVipHaircut()!=null){
            haircut=hsVip.getVipHaircut();
        }
        if(hsVip.getVipHaircare()!=null){
            haircare=hsVip.getVipHaircare();
        }
        if(hsVip.getVipBalance()!=null){
            balance=hsVip.getVipBalance();
        }
        Double money = haircut*150+haircare*200+balance;
        hsAccumulation.setAccumulationTotal(money);
        this.hsAccumulationMapper.insert(hsAccumulation);
    }

    /**
     * 更新累积(充值余额）
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public void updateB(HsVip hsVip){
        HsAccumulation hsAccumulation = new HsAccumulation();
        Integer id = this.hsVipMapper.queryAllByPhone(hsVip.getVipPhone()).getVipId();
        System.out.println(id);
        hsAccumulation.setVipId(id);
        hsAccumulation.setAccumulationId(this.hsAccumulationMapper.queryByVipId(id).getAccumulationId());
        Double money = this.hsAccumulationMapper.queryByVipId(id).getAccumulationTotal()+hsVip.getVipBalance();
        hsAccumulation.setAccumulationTotal(money);
        this.hsAccumulationMapper.update(hsAccumulation);
    }

    /**
     * 更新累积（买卡）
     *
     * @param hsVip 实例对象
     * @return 实例对象
     */
    @Override
    public void updateC(HsVip hsVip){
        HsAccumulation hsAccumulation = new HsAccumulation();
        Integer id = this.hsVipMapper.queryAllByPhone(hsVip.getVipPhone()).getVipId();
        hsAccumulation.setVipId(id);
        Integer haircut = 0;
        Integer haircare = 0;
        if(hsVip.getVipHaircut()!=null){
            haircut=hsVip.getVipHaircut();
        }
        if(hsVip.getVipHaircare()!=null){
            haircare=hsVip.getVipHaircare();
        }
        Double money = this.hsAccumulationMapper.queryByVipId(id).getAccumulationTotal()+haircut*150+haircare*200;
        hsAccumulation.setAccumulationTotal(money);
        this.hsAccumulationMapper.update(hsAccumulation);
    }



}