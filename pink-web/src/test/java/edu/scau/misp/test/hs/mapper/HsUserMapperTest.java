package edu.scau.misp.test.hs.mapper;

import edu.scau.misp.hs.entity.HsUser;
import edu.scau.misp.hs.mapper.HsUserMapper;
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

public class HsUserMapperTest {
    @Autowired
    private HsUserMapper hsUserMapper;
    @org.junit.Test
    public void queryByUsername() {
        HsUser hsUser = hsUserMapper.queryByUsername("admin");
        System.out.println(hsUser.getPassword());
    }
}