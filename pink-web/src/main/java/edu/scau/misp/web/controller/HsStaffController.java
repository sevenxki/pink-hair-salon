package edu.scau.misp.web.controller;

import edu.scau.misp.hs.entity.HsRole;
import edu.scau.misp.hs.entity.HsSalary;
import edu.scau.misp.hs.entity.HsStaff;
import edu.scau.misp.hs.service.HsRoleService;
import edu.scau.misp.hs.service.HsSalaryService;
import edu.scau.misp.hs.service.HsWageRulesService;
import edu.scau.misp.hs.service.IHsStaffService;
import edu.scau.misp.hs.vo.LevelVo;
import edu.scau.misp.hs.vo.StaffWagesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (HsStaff)表控制层
 *
 * @author makejava
 * @since 2022-03-20 00:19:21
 */
@RestController
@RequestMapping("hsStaff")
@Api(value = "staffApi" , tags = "员工")
public class HsStaffController {
    /**
     * 服务对象
     */
    @Resource
    private IHsStaffService hsStaffService;
    @Resource
    private HsWageRulesService hsWageRulesService;
    @Resource
    private HsSalaryService hsSalaryService;
    @Resource
    private HsRoleService hsRoleService;

    /**
     * 分页查询
     *
     * @param hsStaff 筛选条件
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @return 查询结果
     */
    @GetMapping("/page")
    @ApiOperation("查询员工分页")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsStaff hsStaff) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsStaff> list = hsStaffService.queryAll(hsStaff);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

//    /**
//     * 分页查询
//     *
//     * @param staffWagesVo 筛选条件
//     * @param pageNum 起始页
//     * @param pageSize 每页数据大小
//     * @return 查询结果
//     */
//    @GetMapping("/swv")
//    @ApiOperation("查询员工分页")
//    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, StaffWagesVo staffWagesVo) {
//        PageHelper.startPage(pageNum,pageSize);
//        List<HsStaff> list = hsStaffService.queryAll(new HsStaff());
//        List<StaffWagesVo> staffWagesVos = new ArrayList<>();
//        for (HsStaff hsStaff : list){
//            staffWagesVo.setStaffId(hsStaff.getStaffId());
//            staffWagesVo.setStaffName(hsStaff.getStaffName());
//            staffWagesVo.setStaffNumber(hsStaff.getStaffNumber());
//            staffWagesVo.setBasicWage(hsWageRulesService.queryByRoleId(Integer.parseInt(hsStaff.getLevel())).getBasicWage());
//
//        }
//        PageInfo<List> pageInfo = new PageInfo(staffWagesVos);
//        return pageInfo;
//    }

    /**
     * 查所有
     *
     * @param hsStaff 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    @ApiOperation("查询员工列表")
    public List<HsStaff> list(HsStaff hsStaff) {
        return hsStaffService.queryAll(hsStaff);
    }

    /**
     * 查所有角色
     *
     * @param hsRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/role")
    @ApiOperation("查询员工所有角色的序号")
    public List<HsRole> roleList(HsRole hsRole) {
        List<HsRole> hsRoles = hsRoleService.queryAll(hsRole);
        List<HsRole> hsRoleList = new ArrayList<>();
        for (HsRole hsRole1 : hsRoles){
            if (hsRole1.getRoleId()!=5){
                HsRole hsRole2 = new HsRole();
                hsRole2.setRoleId(hsRole1.getRoleId());
                hsRole2.setRoleName(hsRole1.getRoleName());
                hsRoleList.add(hsRole2);
            }
        }
        return hsRoleList;
    }

    /**
     * 查角色的所有等级
     *
     * @param roleId 筛选条件
     * @return 查询结果
     */
    @GetMapping("/level/{roleId}")
    @ApiOperation("查询员工角色所有级别的序号")
    public List<LevelVo> level(@PathVariable("roleId") Integer roleId) {
        List<LevelVo> levelVos = new ArrayList<>();
        if (roleId == 2){   //发型师
            LevelVo levelVo = new LevelVo();
            levelVo.setLevel("1");
            levelVo.setLevelName("高级设计师");
            LevelVo levelVo1 = new LevelVo();
            levelVo1.setLevel("2");
            levelVo1.setLevelName("资深设计师");
            LevelVo levelVo2 = new LevelVo();
            levelVo2.setLevel("3");
            levelVo2.setLevelName("技术总监");
            levelVos.add(levelVo);
            levelVos.add(levelVo1);
            levelVos.add(levelVo2);
        }
        return levelVos;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param staffId 主键
     * @return 单条数据
     */
    @GetMapping("{staffId}")
    public HsStaff getById(@PathVariable("staffId") Integer staffId) {
        return this.hsStaffService.queryById(staffId);
    }

    /**
     * 新增数据
     *
     * @param hsStaff 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation("添加员工")
    public String add(@RequestBody HsStaff hsStaff) {
        this.hsStaffService.insert(hsStaff);
        return "ok";
    }

    /**
     * 编辑数据
     *
     * @param hsStaff 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("修改员工信息")
    public String edit(@RequestBody HsStaff hsStaff) {
       this.hsStaffService.update(hsStaff);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param staffId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{staffId}")
    @ApiOperation("删除员工信息")
    public Boolean deleteById(@PathVariable Integer staffId) {
        return this.hsStaffService.deleteById(staffId);
    }

}

