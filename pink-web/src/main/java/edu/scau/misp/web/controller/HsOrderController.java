package edu.scau.misp.web.controller;



import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsOrder;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsStaff;
import edu.scau.misp.hs.mapper.HsProjectMapper;
import edu.scau.misp.hs.mapper.HsStaffMapper;
import edu.scau.misp.hs.service.HsOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * (HsOrder)表控制层
 *
 * @author makejava
 * @since 2022-03-29 22:17:16
 */
@RestController
@RequestMapping("hsOrder")
public class HsOrderController {
    /**
     * 服务对象
     */
    @Resource
    private HsOrderService hsOrderService;
    @Resource
    private HsStaffMapper hsStaffMapper;
    @Resource
    private HsProjectMapper hsProjectMapper;

    /**
     * 查询列表
     *
     * @param hsOrder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsOrder> queryAll(HsOrder hsOrder) { //重新算总金额
        List<HsOrder> hsOrder1 = hsOrderService.queryAll(hsOrder);
        for (HsOrder hsOrder2:hsOrder1){
            HsProject hsProject1 = hsProjectMapper.queryPriceByProjectName(hsOrder2.getHaircareName());
            HsProject hsProject2 = hsProjectMapper.queryById(hsOrder2.getProjectId());
            Double v = hsProject1.getProjectPrice();
            Double vv = hsProject2.getProjectPrice();
            Double v3 = v+vv;
            hsOrder2.setTotal(v3);
            hsOrderService.update(hsOrder2);
        }
        return hsOrder1;
    }

    /**
     * 查询未支付订单
     *
     * @param hsOrder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/noPayment")
    public List<HsOrder> queryNoPayment(HsOrder hsOrder) {
        List<HsOrder> hsOrder1 = hsOrderService.queryNoPayment(hsOrder);
        for (HsOrder hsOrder2:hsOrder1){
//            System.out.println(hsOrder2.getHaircareName());
//            System.out.println(hsOrder2.getStaffHairstylistName());
            HsProject hsProject1 = hsProjectMapper.queryPriceByProjectName(hsOrder2.getHaircareName());
            HsProject hsProject2 = hsProjectMapper.queryById(hsOrder2.getProjectId());
            Double v = hsProject1.getProjectPrice();
            Double vv = hsProject2.getProjectPrice();
            Double v3 = v+vv;
//            System.out.println(v);
//            System.out.println(vv);
//            System.out.println(v3);
            hsOrder2.setTotal(v3);
            hsOrderService.update(hsOrder2);
        }
        return hsOrder1;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param orderId 主键
     * @return 单条数据
     */
    @GetMapping("find/{orderId}")
    public HsOrder getById(@PathVariable("orderId") String orderId) {
        return this.hsOrderService.queryById(parseInt(orderId));
    }

    /**
     * 通过项目名称查询数据
     *
     * @param projectName
     * @return 查询结果
     */
    @GetMapping("{projectName}")
    public List<HsOrder> getByProjectName(@PathVariable("projectName") String projectName){
        return this.hsOrderService.queryByProjectName(projectName);
    }

    /**
     * 分页查询
     *
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @param hsOrder 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsOrder hsOrder) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsOrder> list = hsOrderService.queryAll(hsOrder);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param hsOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<HsOrder> add(HsOrder hsOrder) {
        return ResponseEntity.ok(this.hsOrderService.insert(hsOrder));
    }

    /**
     * 编辑数据
     *
     * @param hsOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody HsOrder hsOrder) {
        this.hsOrderService.update(hsOrder);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param orderId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{orderId}")
    public Boolean deleteById(@PathVariable Integer orderId) {
        return this.hsOrderService.deleteById(orderId);
    }

    /**
     * 查询所有项目类型
     *
     * @return 查询结果
     */
    @GetMapping("/allKind")
    public List<String> queryAllKind() {
        return this.hsOrderService.queryAllKind();
    }

    /**
     * 用项目类型名字查询项目名字
     *
     * @return 查询结果
     */
    @GetMapping("/queryProject/{kindName}")
    public List<String> queryAllProjectNameByKind(@PathVariable String kindName) {
        return this.hsOrderService.queryAllProjectNameByKind(kindName);
    }
    /**
     * 用项目类型名字查询项目all
     *
     * @return 查询结果
     */
    @GetMapping("/qPA/{kindName}")
    public List<HsProject> queryAllProjectAllByKind(@PathVariable String kindName) {
        return this.hsOrderService.queryAllProjectAllByKind(kindName);
    }

    /**
     *护理项目name
     * @param
     * @return
     */
    @GetMapping("/hairCare")
    public List<String> queryAllHairCare() {
        return this.hsOrderService.queryAllHairCare();
    }

    /**
     *护理项目all
     * @param
     * @return
     */
    @GetMapping("/aHairCare")
    public List<HsProject> queryAllHairCareAll() {
        return this.hsOrderService.queryAllHairCareAll();
    }

    /**
     *查找技师name
     * @param
     * @return
     */
    @GetMapping("/queryTechnician")
    public List<String> queryTechnician() {
        return this.hsOrderService.queryTechnician();
    }

    /**
     *查找技师all
     * @param
     * @return
     */
    @GetMapping("/allQueryTechnician")
    public List<HsStaff> queryTechnicianAll() {
        return this.hsOrderService.queryTechnicianAll();
    }

    /**
     *查找对应等级的员工name
     * @param
     * @return
     */
    @GetMapping("/queryStaffByLevel/{projectName}")
    public List<String> queryStaffByLevel(@PathVariable String projectName){
        return this.hsOrderService.queryStaffByLevel(projectName);
    }

    /**
     *查找对应等级的员工all
     * @param
     * @return
     */
    @GetMapping("/qb/{projectName}")
    public List<HsStaff> queryStaffByLevelAll(@PathVariable String projectName){
        return this.hsOrderService.queryStaffByLevelAll(projectName);
    }

    /**
     * 用项目类型名字查询项目名字
     *
     * @return 查询结果
     */
    @PostMapping("/countTotal")
    public String countTotal(@RequestBody JSONArray order) {
        return this.hsOrderService.countTotal(order);
    }

    /**
     * 下单
     *
     * @return 新增结果
     */
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody JSONArray placeOrder) {
        this.hsOrderService.placeOrder(placeOrder);
        return "ok";
    }
}

