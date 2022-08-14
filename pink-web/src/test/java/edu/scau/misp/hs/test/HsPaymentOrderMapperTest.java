package edu.scau.misp.hs.test;

import edu.scau.misp.hs.entity.HsPaymentOrder;
import edu.scau.misp.hs.mapper.HsPaymentOrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest    //启动MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //这个是启用自己配置的数据源

public class HsPaymentOrderMapperTest {
    @Autowired
    private HsPaymentOrderMapper hsPaymentOrderMapper;

    @org.junit.Test
    public void queryById() {
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryById(1);
        System.out.println(hsPaymentOrder);
    }

    @org.junit.Test
    public void queryByPhone() {
        List<HsPaymentOrder> hsPaymentOrder = hsPaymentOrderMapper.queryByPhone("123486465");
        for (HsPaymentOrder hsPaymentOrder1 : hsPaymentOrder){
            System.out.println(hsPaymentOrder1);
        }
    }

    @org.junit.Test
    public void queryByOrder() {
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryByOrder(3);
        System.out.println(hsPaymentOrder);
    }

    @org.junit.Test
    public void queryOver() {
        List<HsPaymentOrder> hsPaymentOrder = hsPaymentOrderMapper.queryOver(new HsPaymentOrder());
        for (HsPaymentOrder hsPaymentOrder1 : hsPaymentOrder){
            System.out.println(hsPaymentOrder1);
        }
    }

    @org.junit.Test
    public void insert() {
        HsPaymentOrder hsPaymentOrder = new HsPaymentOrder();
        hsPaymentOrder.setOrderId(4);
        hsPaymentOrder.setTotalPayment(100.00);
        hsPaymentOrderMapper.insert(hsPaymentOrder);
    }

    @org.junit.Test
    public void update() {
        HsPaymentOrder hsPaymentOrder = hsPaymentOrderMapper.queryById(1);
        hsPaymentOrder.setOrderId(4);
        hsPaymentOrder.setTotalPayment(100.00);
        hsPaymentOrderMapper.update(hsPaymentOrder);
    }

    @org.junit.Test
    public void deleteById() {
        hsPaymentOrderMapper.deleteById(1);
    }
}