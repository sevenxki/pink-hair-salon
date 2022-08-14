package edu.scau.misp.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.entity.HsSalaryCom;
import edu.scau.misp.hs.entity.HsSaleCommission;
import edu.scau.misp.hs.service.HsSalaryComService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsSalaryCom)表控制层
 *
 * @author makejava
 * @since 2022-05-08 10:52:36
 */
@RestController
@RequestMapping("hsSalaryCom")
public class HsSalaryComController {
    /**
     * 服务对象
     */
    @Resource
    private HsSalaryComService hsSalaryComService;

//    /**
//     * 分页查询
//     *
//     * @param hsSalaryCom 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @GetMapping
//    public ResponseEntity<Page<HsSalaryCom>> queryByPage(HsSalaryCom hsSalaryCom, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.hsSalaryComService.queryByPage(hsSalaryCom, pageRequest));
//    }

    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsSalaryCom 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsSalaryCom hsSalaryCom) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsSalaryCom> list = hsSalaryComService.queryAll(hsSalaryCom);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询列表
     *
     * @param hsSalaryCom 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsSalaryCom> list(HsSalaryCom hsSalaryCom) {
        return this.hsSalaryComService.queryAll(hsSalaryCom);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HsSalaryCom> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hsSalaryComService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hsSalaryCom 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HsSalaryCom> add(HsSalaryCom hsSalaryCom) {
        return ResponseEntity.ok(this.hsSalaryComService.insert(hsSalaryCom));
    }

    /**
     * 编辑数据
     *
     * @param hsSalaryCom 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HsSalaryCom> edit(HsSalaryCom hsSalaryCom) {
        return ResponseEntity.ok(this.hsSalaryComService.update(hsSalaryCom));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.hsSalaryComService.deleteById(id));
    }

}

