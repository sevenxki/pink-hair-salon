package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsSaleCommission;
import edu.scau.misp.hs.service.HsSaleCommissionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsSaleCommission)表控制层
 *
 * @author makejava
 * @since 2022-05-01 01:52:02
 */
@RestController
@RequestMapping("hsSaleCommission")
public class HsSaleCommissionController {
    /**
     * 服务对象
     */
    @Resource
    private HsSaleCommissionService hsSaleCommissionService;

    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsSaleCommission 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsSaleCommission hsSaleCommission) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsSaleCommission> list = hsSaleCommissionService.queryAll(hsSaleCommission);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询列表
     *
     * @param hsSaleCommission 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsSaleCommission> list(HsSaleCommission hsSaleCommission) {
        return this.hsSaleCommissionService.queryAll(hsSaleCommission);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param saleCommissionId 主键
     * @return 单条数据
     */
    @GetMapping("{saleCommissionId}")
    public HsSaleCommission queryById(@PathVariable("saleCommissionId") Integer saleCommissionId) {
        return this.hsSaleCommissionService.queryById(saleCommissionId);
    }

    /**
     * 新增数据
     *
     * @param hsSaleCommission 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsSaleCommission hsSaleCommission) {
       this.hsSaleCommissionService.insert(hsSaleCommission);
        return "ok";
    }

    /**
     * 编辑数据
     *
     * @param hsSaleCommission 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsSaleCommission hsSaleCommission) {
        this.hsSaleCommissionService.update(hsSaleCommission);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param saleCommissionId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/saleCommissionId")
    public Boolean deleteById(Integer saleCommissionId) {
        return this.hsSaleCommissionService.deleteById(saleCommissionId);
    }

}

