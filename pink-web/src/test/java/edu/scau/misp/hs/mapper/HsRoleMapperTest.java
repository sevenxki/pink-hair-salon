package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsRole;
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

public class HsRoleMapperTest {
    @Autowired
    private HsRoleMapper hsRoleMapper;
    @org.junit.Test
    public void queryByName() {
        HsRole hsRole = hsRoleMapper.queryByName("店长");
        System.out.println(hsRole.getRoleNo());
    }
}