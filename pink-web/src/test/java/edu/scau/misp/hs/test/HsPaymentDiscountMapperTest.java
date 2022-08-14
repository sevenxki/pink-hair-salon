package edu.scau.misp.hs.test;

import edu.scau.misp.hs.entity.HsPaymentDiscount;
import edu.scau.misp.hs.mapper.HsPaymentDiscountMapper;
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

public class HsPaymentDiscountMapperTest {
    @Autowired
    private HsPaymentDiscountMapper hsPaymentDiscountMapper;

    @org.junit.Test
    public void queryByPayment() {
        HsPaymentDiscount hsPaymentDiscount = hsPaymentDiscountMapper.queryByPayment(1);
        System.out.println(hsPaymentDiscount);
    }

}