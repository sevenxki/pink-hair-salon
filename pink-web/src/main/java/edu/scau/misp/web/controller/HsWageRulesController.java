package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsWageRules;
import edu.scau.misp.hs.service.HsWageRulesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsWageRules)表控制层
 *
 * @author makejava
 * @since 2022-04-29 13:26:17
 */
@RestController
@RequestMapping("hsWageRules")
public class HsWageRulesController {
    /**
     * 服务对象
     */
    @Resource
    private HsWageRulesService hsWageRulesService;

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsWageRules 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsWageRules hsWageRules) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsWageRules> list = hsWageRulesService.queryAll(hsWageRules);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询列表
     *
     * @param hsWageRules 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsWageRules> queryAll(HsWageRules hsWageRules) {
        return this.hsWageRulesService.queryAll(hsWageRules);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param wageRulesId 主键
     * @return 单条数据
     */
    @GetMapping("{wageRulesId}")
    public HsWageRules queryById(@PathVariable("wageRulesId") Integer wageRulesId) {
        return this.hsWageRulesService.queryById(wageRulesId);
    }

    /**
     * 新增数据
     *
     * @param hsWageRules 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsWageRules hsWageRules) {
        this.hsWageRulesService.insert(hsWageRules);
        return "ok";
    }

    /**
     * 编辑数据
     *
     * @param hsWageRules 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsWageRules hsWageRules) {
        this.hsWageRulesService.update(hsWageRules);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param wageRulesId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{wageRulesId}")
    public Boolean deleteById(@PathVariable Integer wageRulesId) {
        return this.hsWageRulesService.deleteById(wageRulesId);
    }

}

