package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsAccumulation;
import edu.scau.misp.hs.service.HsAccumulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsAccumulation)表控制层
 *
 */
@RestController
@RequestMapping("hsAccumulation")
public class HsAccumulationController {
    /**
     * 服务对象
     */
    @Autowired
    private HsAccumulationService hsAccumulationService;

    /**
     * 查询列表
     *
     * @param hsAccumulation 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsAccumulation> list(HsAccumulation hsAccumulation) {
        return this.hsAccumulationService.queryAll(hsAccumulation);
    }

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsAccumulation 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsAccumulation hsAccumulation) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsAccumulation> list = hsAccumulationService.queryAll(hsAccumulation);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param accumulationId 主键
     * @return 单条数据
     */
    @GetMapping("{accumulationId}")
    public HsAccumulation getById(@PathVariable("accumulationId") Integer accumulationId) {
        return this.hsAccumulationService.queryById(accumulationId);
    }

    /**
     * 新增数据
     *
     * @param hsAccumulation 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsAccumulation hsAccumulation) {
        this.hsAccumulationService.insert(hsAccumulation);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsAccumulation 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsAccumulation hsAccumulation) {
        this.hsAccumulationService.update(hsAccumulation);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param accumulationId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{accumulationId}")
    public Boolean deleteById(@PathVariable Integer accumulationId) {
        return this.hsAccumulationService.deleteById(accumulationId);
    }



}