package edu.scau.misp.sys.security.config.service;


import edu.scau.misp.hs.entity.HsUser;

import edu.scau.misp.hs.vo.RegisterVo;
import edu.scau.misp.hs.vo.UserVO;

import java.util.List;

/**
 * (HsUser)表服务接口
 *
 * @author makejava
 * @since 2022-03-17 15:21:53
 */
public interface HsUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    HsUser queryById(Integer userId);

    /**
     * 查询多条数据
     *
     * @param hsUser 筛选条件
     * @return 查询结果
     */
    List<HsUser> queryAll(HsUser hsUser);

    UserVO queryByToken(String username);

    /**
     * 新增数据
     *
     * @param hsUser 实例对象
     * @return 实例对象
     */
    HsUser insert(HsUser hsUser);

    /**
     * 修改数据
     *
     * @param userVO 实例对象
     * @return 实例对象
     */
    UserVO update(UserVO userVO);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

    /**
     * 通过用户名查找全部
     *
     * @param username
     * @return
     */
    HsUser queryByUsername(String username);

    /**
     * 注册判断用户名
     *
     * @param username
     * @return 是否成功
     */
    boolean username(String username);

    /**
     * 注册判断
     *
     * @param
     * @return 是否成功
     */
    boolean register(RegisterVo registerVo);

    /**
     * 注册判断旧密码
     *
     * @param oldPassword
     * @return 是否成功
     */
    boolean oldPassword(String oldPassword,String username);

    String newPassword(String password,String username);
}
