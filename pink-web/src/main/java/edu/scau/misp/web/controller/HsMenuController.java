package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsMenu;
import edu.scau.misp.hs.service.HsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsMenu)表控制层
 *
 */
@RestController
@RequestMapping("hsMenu")
public class HsMenuController {
    /**
     * 服务对象
     */
    @Autowired
    private HsMenuService hsMenuService;

    /**
     * 查询列表
     *
     * @param hsMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsMenu> list(HsMenu hsMenu) {
        return this.hsMenuService.queryAll(hsMenu);
    }

//    /**
//     * 分页查询
//     * @param pageNum 起始页
//     * @param pageSize 每页数据大小
//     * @param hsMenu 筛选条件
//     * @return 查询结果
//     */
//    @GetMapping("/page")
//    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsMenu hsMenu) {
//        PageHelper.startPage(pageNum,pageSize);
//        List<HsMenu> list = hsMenuService.queryAll(hsMenu);
//        PageInfo<List> pageInfo = new PageInfo(list);
//        return pageInfo;
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param menuId 主键
     * @return 单条数据
     */
    @GetMapping("{menuId}")
    public HsMenu getById(@PathVariable("menuId") Integer menuId) {
        return this.hsMenuService.queryById(menuId);
    }

    /**
     * 新增数据
     *
     * @param hsMenu 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsMenu hsMenu) {
        this.hsMenuService.insert(hsMenu);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsMenu 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsMenu hsMenu) {
        this.hsMenuService.update(hsMenu);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param menuId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{menuId}")
    public Boolean deleteById(@PathVariable Integer menuId) {
        return this.hsMenuService.deleteById(menuId);
    }

}