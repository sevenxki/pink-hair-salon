package edu.scau.misp.hs.test;

import edu.scau.misp.hs.entity.HsVip;
import edu.scau.misp.hs.mapper.HsVipMapper;
import edu.scau.misp.hs.util.DateUtils;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest    //启动MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //这个是启用自己配置的数据源

public class HsVipMapperTest {
    @Autowired
    private HsVipMapper hsVipMapper;

    //通过会员id找到会员全部信息
    @org.junit.Test
    public void queryById() {
        HsVip hsVip = hsVipMapper.queryById(16);
        System.out.println(hsVip.getVipName());
    }

    //通过会员id找到会员全部信息
    @org.junit.Test
    public void queryAllByPhone() {
        HsVip hsVip = hsVipMapper.queryAllByPhone("542343");
        System.out.println(hsVip.getVipName());
    }

    //查找全部的会员的信息
    @org.junit.Test
    @Rollback(false)
    public void queryAll() {
        List<HsVip> list = hsVipMapper.queryAll(new HsVip());
        for(HsVip hsVip:list){
            System.out.println(hsVip.getVipName());
        }
    }

    //插入新的Vip
    @org.junit.Test
    public void insert() {
        HsVip hsVip = new HsVip();
        hsVip.setVipName("ll");
        hsVip.setVipGender(1);
        hsVip.setVipPhone("1231244");
        hsVip.setVipBirthday(DateUtils.getNowDate());
        hsVip.setVipBalance(500.00);
        hsVip.setVipHaircut(3);
        hsVip.setVipHaircare(3);
        hsVipMapper.insert(hsVip);
    }
    //修改Vip
    @org.junit.Test
    public void update() {
        HsVip hsVip = hsVipMapper.queryById(16);//修改第一个项目的编号
        hsVip.setVipBalance(200.0);//原本是1001
        hsVipMapper.update(hsVip);
    }
    //删除Vip
    @org.junit.Test
    public void deleteById() {
        hsVipMapper.deleteById(16);//删除第一个项目类别
    }

}