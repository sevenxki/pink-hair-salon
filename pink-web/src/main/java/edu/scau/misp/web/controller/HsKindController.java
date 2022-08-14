package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.service.HsKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsKind)表控制层
 *
 */
@RestController
@RequestMapping("hsKind")
public class HsKindController {
    /**
     * 服务对象
     */
    @Autowired
    private HsKindService hsKindService;

    /**
     * 查询列表
     *
     * @param hsKind 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsKind> list(HsKind hsKind) {
        return this.hsKindService.queryAll(hsKind);
    }

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsKind 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsKind hsKind) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsKind> list = hsKindService.queryAll(hsKind);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param kindId 主键
     * @return 单条数据
     */
    @GetMapping("{kindId}")
    public HsKind getById(@PathVariable("kindId") Integer kindId) {
        return this.hsKindService.queryById(kindId);
    }

    /**
     * 新增数据
     *
     * @param hsKind 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsKind hsKind) {
        this.hsKindService.insert(hsKind);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsKind 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsKind hsKind) {
        this.hsKindService.update(hsKind);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param kindId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{kindId}")
    public Boolean deleteById(@PathVariable Integer kindId) {
        return this.hsKindService.deleteById(kindId);
    }

}