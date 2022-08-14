package edu.scau.misp.test.hs.mapper;

import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.mapper.HsKindMapper;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@MybatisTest    //启动MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    //这个是启用自己配置的数据源
public class HsKindMapperTest {
    @Autowired
    private HsKindMapper hsKindMapper;
    //根据项目类别编号查询
    @org.junit.Test
    public void queryById() {
        HsKind hsKind = hsKindMapper.queryById(1);
        System.out.println(hsKind.getKindName());
    }
    //查询所有项目类别
    @org.junit.Test
    @Rollback(false)
    public void queryAll() {
        List<HsKind> list = hsKindMapper.queryAll(new HsKind());
        for(HsKind hsKind:list){
            System.out.println(hsKind.getKindName());
        }
    }
    //查询数据库的数据量
    @org.junit.Test
    public void count() {
        System.out.println(hsKindMapper.count(new HsKind()));
    }
    //插入新的项目类别
    @org.junit.Test
    public void insert() {
        HsKind hsKind = new HsKind();
        hsKind.setKindName("洗刷刷");//添加一个叫洗刷刷的项目类别
        hsKind.setKindNo("11120");
        hsKindMapper.update(hsKind);
    }
    //修改项目类别
    @org.junit.Test
    public void update() {
        HsKind hsKind = hsKindMapper.queryById(1);//修改第一个项目类别的编号
        hsKind.setKindNo("120");//原本是100
        hsKindMapper.update(hsKind);
    }
    //删除项目类别
    @org.junit.Test
    public void deleteById() {
        hsKindMapper.deleteById(1);//删除第一个项目类别
    }
    //查询项目类别的名称
    @org.junit.Test
    public void queryAllKind() {
        List<String> list = hsKindMapper.queryAllKind();
        for (Object str:list){
            System.out.println(str);
        }
    }
}