package edu.scau.misp.test.hs.mapper;

import edu.scau.misp.hs.entity.HsStaff;
import edu.scau.misp.hs.mapper.HsStaffMapper;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@MybatisTest    //启动MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //这个是启用自己配置的数据源

public class HsStaffMapperTest {
    @Autowired
    private HsStaffMapper hsStaffMapper;
    //查询技师
    @org.junit.Test
    public void queryTechnician() {
        List<String> hsStaff = hsStaffMapper.queryTechnician();
        for (Object s:hsStaff){
            System.out.println(s);
        }
    }
    //查询技师所有信息
    @org.junit.Test
    public void queryTechnicianAll() {
        List<HsStaff> hsStaff = hsStaffMapper.queryTechnicianAll();
        for (HsStaff s:hsStaff){
            System.out.println(s);
        }
    }
    //查询员工ID通过员工姓名
    @org.junit.Test
    public void queryStaffIdByName() {
        HsStaff hsStaff = hsStaffMapper.queryStaffIdByName("李四");
        System.out.println(hsStaff.getStaffId());
    }
}