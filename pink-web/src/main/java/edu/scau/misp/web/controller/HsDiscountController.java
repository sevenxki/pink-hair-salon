package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsDiscount;
import edu.scau.misp.hs.service.HsDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsDiscount)表控制层
 *
 */
@RestController
@RequestMapping("hsDiscount")
public class HsDiscountController {
    /**
     * 服务对象
     */
    @Autowired
    private HsDiscountService hsDiscountService;

    /**
     * 查询列表
     *
     * @param hsDiscount 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsDiscount> list(HsDiscount hsDiscount) {
        return this.hsDiscountService.queryAll(hsDiscount);
    }

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsDiscount 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsDiscount hsDiscount) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsDiscount> list = hsDiscountService.queryAll(hsDiscount);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param discountId 主键
     * @return 单条数据
     */
    @GetMapping("{discountId}")
    public HsDiscount getById(@PathVariable("discountId") Integer discountId) {
        return this.hsDiscountService.queryById(discountId);
    }

    /**
     * 新增数据
     *
     * @param hsDiscount 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsDiscount hsDiscount) {
        this.hsDiscountService.insert(hsDiscount);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsDiscount 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsDiscount hsDiscount) {
        this.hsDiscountService.update(hsDiscount);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param discountId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{discountId}")
    public Boolean deleteById(@PathVariable Integer discountId) {
        return this.hsDiscountService.deleteById(discountId);
    }

}