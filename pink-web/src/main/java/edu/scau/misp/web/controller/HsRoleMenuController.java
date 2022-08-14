package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsRoleMenu;
import edu.scau.misp.hs.service.HsRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * (HsRoleMenu)表控制层
 *
 */
@RestController
@RequestMapping("hsRoleMenu")
public class HsRoleMenuController {
    /**
     * 服务对象
     */
    @Autowired
    private HsRoleMenuService hsRoleMenuService;

    /**
     * 查询列表
     *
     * @param hsRoleMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsRoleMenu> list(HsRoleMenu hsRoleMenu) {
        return this.hsRoleMenuService.queryAll(hsRoleMenu);
    }

    /**
     * 分页查询
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsRoleMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsRoleMenu hsRoleMenu) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsRoleMenu> list = hsRoleMenuService.queryAll(hsRoleMenu);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param roleMenuId 主键
     * @return 单条数据
     */
    @GetMapping("{roleMenuId}")
    public HsRoleMenu getById(@PathVariable("roleMenuId") Integer roleMenuId) {
        return this.hsRoleMenuService.queryById(roleMenuId);
    }

    /**
     * 新增数据
     *
     * @param hsRoleMenu 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsRoleMenu hsRoleMenu) {
        this.hsRoleMenuService.insert(hsRoleMenu);
        return "ok";
    }

    /**
     * 编辑数据
     * @param hsRoleMenu 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsRoleMenu hsRoleMenu) {
        this.hsRoleMenuService.update(hsRoleMenu);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param roleMenuId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{roleMenuId}")
    public Boolean deleteById(@PathVariable Integer roleMenuId) {
        return this.hsRoleMenuService.deleteById(roleMenuId);
    }

}