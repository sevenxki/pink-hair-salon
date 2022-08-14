package edu.scau.misp.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsRole;
import edu.scau.misp.hs.mapper.HsSalaryMapper;
import edu.scau.misp.hs.service.HsRoleService;
import edu.scau.misp.hs.service.HsSalaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsRole)表控制层
 *
 * @author makejava
 * @since 2022-03-20 01:57:24
 */
@RestController
@RequestMapping("hsRole")
public class HsRoleController {
    /**
     * 服务对象
     */
    @Resource
    private HsRoleService hsRoleService;


    /**
     * 查询列表
     *
     * @param hsRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsRole> list(HsRole hsRole) {
        return this.hsRoleService.queryAll(hsRole);
    }


    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsRole hsRole) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsRole> list = hsRoleService.queryAll(hsRole);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param roleId 主键
     * @return 单条数据
     */
    @GetMapping("{roleId}")
    public HsRole queryById(@PathVariable("roleId") Integer roleId) {
        return this.hsRoleService.queryById(roleId);
    }

    /**
     * 新增数据
     *
     * @param hsRole 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsRole hsRole) {
        this.hsRoleService.insert(hsRole);
        return "ok";
    }

    /**
     * 编辑数据
     *
     * @param hsRole 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsRole hsRole) {
       this.hsRoleService.update(hsRole);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param roleId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{roleId}")
    public Boolean deleteById(@PathVariable("roleId") Integer roleId) {
        return this.hsRoleService.deleteById(roleId);
    }
}