package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsGrouporderDiscount;
import edu.scau.misp.hs.service.HsGrouporderDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsGrouporderDiscount)表控制层
 *
 */
@RestController
@RequestMapping("hsGrouporderDiscount")
public class HsGrouporderDiscountController {
    /**
     * 服务对象
     */
    @Autowired
    private HsGrouporderDiscountService hsGrouporderDiscountService;

    /**
     * 查询列表
     *
     * @param hsGrouporderDiscount 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsGrouporderDiscount> list(HsGrouporderDiscount hsGrouporderDiscount) {
        return this.hsGrouporderDiscountService.queryAll(hsGrouporderDiscount);
    }

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsGrouporderDiscount 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsGrouporderDiscount hsGrouporderDiscount) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsGrouporderDiscount> list = hsGrouporderDiscountService.queryAll(hsGrouporderDiscount);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param grouporderDiscountId 主键
     * @return 单条数据
     */
    @GetMapping("{grouporderDiscountId}")
    public HsGrouporderDiscount getById(@PathVariable("grouporderDiscountId") Integer grouporderDiscountId) {
        return this.hsGrouporderDiscountService.queryById(grouporderDiscountId);
    }

    /**
     * 新增数据
     *
     * @param hsGrouporderDiscount 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsGrouporderDiscount hsGrouporderDiscount) {
        this.hsGrouporderDiscountService.insert(hsGrouporderDiscount);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsGrouporderDiscount 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsGrouporderDiscount hsGrouporderDiscount) {
        this.hsGrouporderDiscountService.update(hsGrouporderDiscount);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param grouporderDiscountId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{grouporderDiscountId}")
    public Boolean deleteById(@PathVariable Integer grouporderDiscountId) {
        return this.hsGrouporderDiscountService.deleteById(grouporderDiscountId);
    }



}