package edu.scau.misp.web.controller;


import edu.scau.misp.hs.entity.HsUser;
import edu.scau.misp.hs.entity.HsUserRole;
import edu.scau.misp.hs.service.HsUserRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsUserRole)表控制层
 *
 * @author makejava
 * @since 2022-03-20 02:13:46
 */
@RestController
@RequestMapping("hsUserRole")
public class HsUserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private HsUserRoleService hsUserRoleService;

    /**
     * 查询列表
     *
     * @param hsUserRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsUserRole> list(HsUserRole hsUserRole) {
        return this.hsUserRoleService.queryAll(hsUserRole);
    }


//    /**
//     * 分页查询
//     *
//     * @param hsUserRole 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @GetMapping
//    public ResponseEntity<Page<HsUserRole>> queryByPage(HsUserRole hsUserRole, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.hsUserRoleService.queryByPage(hsUserRole, pageRequest));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<HsUserRole> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hsUserRoleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hsUserRole 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HsUserRole> add(HsUserRole hsUserRole) {
        return ResponseEntity.ok(this.hsUserRoleService.insert(hsUserRole));
    }

    /**
     * 编辑数据
     *
     * @param hsUserRole 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<HsUserRole> edit(HsUserRole hsUserRole) {
        return ResponseEntity.ok(this.hsUserRoleService.update(hsUserRole));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.hsUserRoleService.deleteById(id));
    }

}

