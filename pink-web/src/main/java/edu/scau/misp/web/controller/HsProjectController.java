package edu.scau.misp.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.service.HsProjectService;
import edu.scau.misp.hs.service.HsProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsProject)表控制层
 *
 * @author makejava
 * @since 2022-03-16 13:04:29
 */
@RestController
@RequestMapping("hsProject")
public class HsProjectController {
    /**
     * 服务对象
     */
    @Resource
    private HsProjectService hsProjectService;

    /**
     * 查询列表
     *
     * @param hsProject 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsProject> list(HsProject hsProject) {

        return this.hsProjectService.queryAll(hsProject);
    }


    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsProject 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsProject hsProject) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsProject> list = hsProjectService.queryAll(hsProject);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param hsProject 主键
     * @return 单条数据
     */
    @GetMapping("{hsProject}")
    public HsProject getById(@PathVariable("hsProject") Integer hsProject) {
        return this.hsProjectService.queryById(hsProject);
    }


    /**
     * 新增数据
     *
     * @param hsProject 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HsProject> add(HsProject hsProject) {
        return ResponseEntity.ok(this.hsProjectService.insert(hsProject));
    }

    /**
     * 编辑数据
     * @param hsProject 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsProject hsProject) {
        this.hsProjectService.update(hsProject);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param hsProject 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{hsProject}")
    public Boolean deleteById(@PathVariable Integer hsProject) {
        return this.hsProjectService.deleteById(hsProject);
    }

    /**
     * 通过主键查询价格
     *
     * @param price 主键
     * @return 单条数据
     */
    @GetMapping("price/{price}")
    public List<HsProject> getById(@PathVariable("price") String price) {
        return this.hsProjectService.hsPrice(price);
    }

}



