package edu.scau.misp.web.controller;


import edu.scau.misp.sys.security.config.service.HsUserService;
import edu.scau.misp.sys.Vo.LoginUserVo;
import edu.scau.misp.hs.vo.RegisterVo;
import edu.scau.misp.sys.entity.ResultEntity;
import edu.scau.misp.sys.security.config.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("auth")
public class AuthenticationController extends BaseController {
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    HsUserService hsUserService;


    /**
     * 登录login
     *
     * @param loginUserVo
     * @return
     */
    @RequestMapping("/login")
    public ResultEntity login(@RequestBody LoginUserVo loginUserVo) {
//        System.out.println(loginUserVo.getUsername()+loginUserVo.getPassword());
        String token = authenticationService.login(loginUserVo.getUsername(), loginUserVo.getPassword());
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);
        return ResultEntity.success("登录成功", data);
    }

    /**
     * 获取用户登录信息
     * @param token 仅用于测试。实际有后端项目，不需要设置token参数
     * @return
     */
    @GetMapping(value = "/getInfo")
    public ResultEntity getInfo(@RequestParam("token") String token) {
//        System.out.println(token);
        return ResultEntity.success(authenticationService.getUserInfo());
    }

    /**
     * logout
     *
     * @param
     * @return
     */
    @RequestMapping("/logout")
    public ResultEntity logout() {
        return ResultEntity.success("退出成功");
    }

    /**
     * 注册判断用户名
     *
     * @param
     * @return
     */
     @GetMapping("username/{username}")
     public ResultEntity username(@PathVariable("username") String username) {
         if(this.hsUserService.username(username)) {
             return ResultEntity.success("用户名可用");
         }else{
             return ResultEntity.success("用户名重复");
         }
     }

    /**
     * 注册
     *
     * @param
     * @return
     */
    @RequestMapping("/register")
    public ResultEntity register(RegisterVo registerVo) {
        if(this.hsUserService.register(registerVo)) {
            return ResultEntity.success("注册成功");
        }else{
            return ResultEntity.success("请输入正确的手机号");
        }
    }


}