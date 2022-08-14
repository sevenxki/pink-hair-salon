package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsGrouporder;
import edu.scau.misp.hs.service.HsGrouporderService;
import edu.scau.misp.hs.vo.DashboardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsGrouporder)表控制层
 *
 */
@RestController
@RequestMapping("hsGrouporder")
public class HsGrouporderController {
    /**
     * 服务对象
     */
    @Autowired
    private HsGrouporderService hsGrouporderService;

    /**
     * 查询列表
     *
     * @param hsGrouporder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsGrouporder> list(HsGrouporder hsGrouporder) {
        return this.hsGrouporderService.queryAll(hsGrouporder);
    }

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsGrouporder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsGrouporder hsGrouporder) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsGrouporder> list = hsGrouporderService.queryAll(hsGrouporder);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param grouporderId 主键
     * @return 单条数据
     */
    @GetMapping("{grouporderId}")
    public HsGrouporder getById(@PathVariable("grouporderId") Integer grouporderId) {
        return this.hsGrouporderService.queryById(grouporderId);
    }

    /**
     * 新增数据
     *
     * @param hsGrouporder 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsGrouporder hsGrouporder) {
        this.hsGrouporderService.insert(hsGrouporder);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsGrouporder 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsGrouporder hsGrouporder) {
        this.hsGrouporderService.update(hsGrouporder);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param grouporderId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{grouporderId}")
    public Boolean deleteById(@PathVariable Integer grouporderId) {
        return this.hsGrouporderService.deleteById(grouporderId);
    }



}