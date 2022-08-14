package edu.scau.misp.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsCustomer;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.service.HsCustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsCustomer)表控制层
 *
 * @author makejava
 * @since 2022-03-20 01:04:07
 */
@RestController
@RequestMapping("hsCustomer")
public class HsCustomerController {
    /**
     * 服务对象
     */
    @Resource
    private HsCustomerService hsCustomerService;

    /**
     * 查询列表
     *
     * @param hsCustomer 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsCustomer> list(HsCustomer hsCustomer) {
        return this.hsCustomerService.queryAll(hsCustomer);
    }


    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsCustomer 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsCustomer hsCustomer) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsCustomer> list = hsCustomerService.queryAll(hsCustomer);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HsCustomer> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hsCustomerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hsCustomer 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HsCustomer> add(HsCustomer hsCustomer) {
        return ResponseEntity.ok(this.hsCustomerService.insert(hsCustomer));
    }

    /**
     * 编辑数据
     *
     * @param hsCustomer 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HsCustomer> edit(HsCustomer hsCustomer) {
        return ResponseEntity.ok(this.hsCustomerService.update(hsCustomer));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.hsCustomerService.deleteById(id));
    }

}

