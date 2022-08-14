package edu.scau.misp.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.misp.hs.entity.HsUser;
import edu.scau.misp.hs.vo.UserVO;
import edu.scau.misp.sys.security.config.service.HsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsUser)表控制层
 *
 * @author makejava
 * @since 2022-03-17 15:21:49
 */
@RestController
@RequestMapping("hsUser")
public class HsUserController {
    /**
     * 服务对象
     */
    @Resource
    private HsUserService hsUserService;

    /**
     * 分页查询
     *
     * @param hsUser 筛选条件
     * @param pageNum 起始页
     * @param pageSize 每页数据大小
     * @return 查询结果
     */
    @GetMapping
    public PageInfo page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HsUser hsUser) {
        PageHelper.startPage(pageNum,pageSize);
        List<HsUser> list = hsUserService.queryAll(hsUser);
        PageInfo<List> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询列表
     *
     * @param hsUser 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public List<HsUser> list(HsUser hsUser) {
        return this.hsUserService.queryAll(hsUser);
    }

    /**
     * username
     *
     * @return 查询结果
     */
    @GetMapping(value ="/tk")
    public UserVO queryByToken(@RequestParam("username") String username){
        return this.hsUserService.queryByToken(username);
    }

    /**
     * oldPassword 判断旧密码
     *
     * @return 是否正确
     */
    @GetMapping(value ="/validated")
    public boolean oldPassword(@RequestParam("password") String password,@RequestParam("username") String username){
        return this.hsUserService.oldPassword(password,username);
    }

    /**
     * newPassword 更新x新密码
     *
     * @return 新增结果
     */
    @GetMapping(value ="/up")
    public String newPassword(@RequestParam("password") String password,@RequestParam("username") String username){
        return this.hsUserService.newPassword(password,username);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param userId 主键
     * @return 单条数据
     */
    @GetMapping("{userId}")
    public HsUser queryById(@PathVariable("userId") Integer userId) {
        return this.hsUserService.queryById(userId);
    }

    /**
     * 新增数据
     *
     * @param hsUser 实体
     * @return 新增结果
     */
    @PostMapping
    public String add(@RequestBody HsUser hsUser) {
       this.hsUserService.insert(hsUser);
       return "ok";
    }

    /**
     * 编辑数据
     *
     * @param userVO 实体
     * @return 编辑结果
     */
    @PutMapping
    public String edit(@RequestBody UserVO userVO) {
        this.hsUserService.update(userVO);
        return "ok";
    }

    /**
     * 删除数据
     *
     * @param userId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{userId}")
    public Boolean deleteById(@PathVariable("userId") Integer userId) {
        return this.hsUserService.deleteById(userId);
    }

}

