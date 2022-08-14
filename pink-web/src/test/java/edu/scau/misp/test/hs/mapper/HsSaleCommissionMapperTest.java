package edu.scau.misp.test.hs.mapper;

import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.entity.HsSaleCommission;
import edu.scau.misp.hs.mapper.HsSaleCommissionMapper;
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

public class HsSaleCommissionMapperTest {
    @Autowired
    private HsSaleCommissionMapper hsSaleCommissionMapper;
    @org.junit.Test
    public void queryByStaff() {
        List<HsSaleCommission> hsSaleCommissionList = hsSaleCommissionMapper.queryByStaff(5);
        for (HsSaleCommission hsSaleCommission : hsSaleCommissionList){
            System.out.println(hsSaleCommission.getCommission());
        }
    }
}