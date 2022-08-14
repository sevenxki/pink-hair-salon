package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsPaymentVip;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@MybatisTest    //启动MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //这个是启用自己配置的数据源

public class HsPaymentVipMapperTest {
    @Autowired
    private HsPaymentVipMapper hsPaymentVipMapper;

    @org.junit.Test
    public void queryByPayment() {
        HsPaymentVip hsPaymentVip = hsPaymentVipMapper.queryByPayment(1);
        System.out.println(hsPaymentVip);
    }

}