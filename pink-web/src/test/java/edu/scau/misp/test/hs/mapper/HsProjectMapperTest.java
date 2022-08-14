package edu.scau.misp.test.hs.mapper;

import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.mapper.HsKindMapper;
import edu.scau.misp.hs.mapper.HsProjectMapper;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@MybatisTest    //启动MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //这个是启用自己配置的数据源

public class HsProjectMapperTest {
    @Autowired
    private HsProjectMapper hsProjectMapper;
    @Autowired
    private HsKindMapper hsKindMapper;
    //根据项目编号查询
    @org.junit.Test
    public void queryById() {
        HsProject hsProject = hsProjectMapper.queryById(1);
        System.out.println(hsProject.getProjectName());
    }
    //查询所有项目
    @org.junit.Test
    @Rollback(false)
    public void queryAll() {
        List<HsProject> list = hsProjectMapper.queryAll(new HsProject());
        for(HsProject hsProject:list){
            System.out.println(hsProject.getProjectName());
        }
    }
    //查询数据库的数据量
    @org.junit.Test
    public void count() {
        System.out.println(hsProjectMapper.count(new HsProject()));
    }

    //插入新的项目
    @org.junit.Test
    public void insert() {
        HsProject hsProject = new HsProject();
        hsProject.setProjectName("洗刷刷");//添加一个叫洗刷刷的项目
        hsProject.setProjectNo(11120);
        hsProject.setProjectPrice(120.00);
        hsProject.setProjectLevel("0");
        hsProject.setKindId(1);
        hsProjectMapper.update(hsProject);
    }
    //修改项目
    @org.junit.Test
    public void update() {
        HsProject hsProject = hsProjectMapper.queryById(1);//修改第一个项目的编号
        hsProject.setProjectNo(120);//原本是1001
        hsProjectMapper.update(hsProject);
    }
    //删除项目
    @org.junit.Test
    public void deleteById() {
        hsProjectMapper.deleteById(1);//删除第一个项目类别
    }
    //获取项目名字通过项目类别
    @org.junit.Test
    public void queryAllProjectNameByKind() {
        List<String> list = hsProjectMapper.queryAllProjectNameByKind("染发");//染发类别的项目
        for(Object str:list){
            System.out.println(str);
        }
    }
    //获取所有护理
    @org.junit.Test
    public void queryAllHairCare() {
        List<String> list = hsProjectMapper.queryAllHairCare();
        for(Object str:list){
            System.out.println(str);
        }
    }
    //查找对应等级的员工
    @org.junit.Test
    public void queryStaffByLevel() {
        List<String> list = hsProjectMapper.queryStaffByLevel("品质呵护染");
        for(Object str:list){
            System.out.println(str);
        }
    }
    //通过项目名找价格
    @org.junit.Test
    public void queryPriceByProjectName() {
        HsProject hsProject = hsProjectMapper.queryPriceByProjectName("品质呵护染");
        System.out.println(hsProject);
    }
    //通过项目ID查询项目名称
    @org.junit.Test
    public void queryProjectIdByName() {
        HsProject hsProject = hsProjectMapper.queryProjectIdByName("品质呵护染");
        System.out.println(hsProject);
    }
}