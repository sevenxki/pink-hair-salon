package edu.scau.misp.sys.security.config.service.impl;


import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.hs.service.HsSalaryService;
import edu.scau.misp.hs.vo.StaffWagesVo;
import edu.scau.misp.sys.security.config.service.HsUserService;
import edu.scau.misp.hs.util.ServiceException;
import edu.scau.misp.hs.vo.RegisterVo;
import edu.scau.misp.hs.vo.UserVO;
import edu.scau.misp.sys.security.config.service.IAuthenticationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (HsUser)表服务实现类
 *
 * @author makejava
 * @since 2022-03-17 15:21:56
 */
@Service("hsUserService")
public class HsUserServiceImpl implements HsUserService {
    @Resource
    private HsUserMapper hsUserMapper;

    @Resource
    private HsStaffMapper hsStaffMapper;

    @Resource
    private HsUserRoleMapper hsUserRoleMapper;

    @Resource
    private IAuthenticationService authenticationService;

    @Resource
    private HsRoleMapper hsRoleMapper;

    @Resource
    private HsSalaryMapper hsSalaryMapper;

    @Resource
    private HsSalaryService hsSalaryService;

    @Resource
    private HsWageRulesMapper hsWageRulesMapper;

    @Resource
    private HsUserService hsUserService;



    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public HsUser queryById(Integer userId) {
        return this.hsUserMapper.queryById(userId);
    }


    /**
     * 查询指定行数据
     *
     * @param hsUser 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsUser> queryAll(HsUser hsUser) {
        return this.hsUserMapper.queryAll(hsUser);
    }

    /**
     * 获取个人信息
     *
     * @return 对象列表
     */
    @Override
    public UserVO queryByToken(String username) {
        UserVO userVO = new UserVO();
//        Map<String, Object> user = authenticationService.getUserInfo();
//        String username = String.valueOf(user.get("name"));
//        System.out.println(username);
        HsUser hsUser = hsUserMapper.queryByUsername(username);
        HsStaff hsStaff = hsStaffMapper.queryByuserName(username);
        HsRole hsRole = hsRoleMapper.queryById(hsStaff.getRoleId());
        //用户表部分
        userVO.setUserId(hsUser.getUserId());
        userVO.setUsername(hsUser.getUsername());
        userVO.setAvatar(hsUser.getAvatar());
        userVO.setPassword(hsUser.getPassword());
//        System.out.println(SecurityUtils.getLoginUser().getPassword());
        //员工表部分
        userVO.setPhoneNumber(hsStaff.getPhoneNumber());
        userVO.setBirthday(hsStaff.getBirthday());
        userVO.setGender(hsStaff.getGender());
        if (hsStaff.getGender()==0){
            userVO.setGenderName("男");
        }else if (hsStaff.getGender()==1){
            userVO.setGenderName("女");
        }
        userVO.setStaffId(hsStaff.getStaffId());
        userVO.setStaffName(hsStaff.getStaffName());
        userVO.setStaffNumber(hsStaff.getStaffNumber());
        userVO.setLevel(Integer.parseInt(hsStaff.getLevel()));
        //等级（0为技师，1为高级设计师、2为资深设计师、3为技术总监、4为前台、5为店长）
        if (Integer.parseInt(hsStaff.getLevel())==0){
            userVO.setLevelName("技师");
        }else  if (Integer.parseInt(hsStaff.getLevel())==1){
            userVO.setLevelName("高级设计师");
        }else  if (Integer.parseInt(hsStaff.getLevel())==2){
            userVO.setLevelName("资深设计师");
        }else  if (Integer.parseInt(hsStaff.getLevel())==3){
            userVO.setLevelName("技术总监");
        }else  if (Integer.parseInt(hsStaff.getLevel())==4){
            userVO.setLevelName("前台");
        }else  if (Integer.parseInt(hsStaff.getLevel())==5){
            userVO.setLevelName("店长");
        }
        //角色表部分
        userVO.setRoleId(hsRole.getRoleId());
        userVO.setRoleName(hsRole.getRoleName());
        //工资表部分
        Integer staffId = hsStaff.getStaffId();
        System.out.println("staffId:"+staffId);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String s = formatter.format(date);   //当前日期
        hsSalaryService.moAdd(new HsSalary());//更新一下本月
        HsSalary h = new HsSalary();
        h.setStaffId(staffId);
        h.setPayTime(s);
        List<HsSalary> hsSalaries = hsSalaryMapper.queryAll(h);
        List<StaffWagesVo> staffWagesVos = new ArrayList<>();
        for(HsSalary hsSalary:hsSalaries){
            StaffWagesVo staffWagesVo = new StaffWagesVo();
            staffWagesVo.setBasicWage(hsWageRulesMapper.queryById(hsSalary.getWageRulesId()).getBasicWage());
            staffWagesVo.setBonus(hsSalary.getBonus());
            staffWagesVo.setDeduction(hsSalary.getDeduction());
            staffWagesVo.setTotalCommission(hsSalary.getTotalCommission());
            staffWagesVo.setTotalWages(hsSalary.getTotalWages());
            staffWagesVo.setYear(hsSalary.getPayTime().substring(0, hsSalary.getPayTime().indexOf("-")));
            staffWagesVo.setMonth(hsSalary.getPayTime().substring(5, 7));
            staffWagesVos.add(staffWagesVo);
        }
        System.out.println("staffWagesVos:"+staffWagesVos.get(0).getBasicWage());
        userVO.setBasicWage(staffWagesVos.get(0).getBasicWage());
        userVO.setTotalCommission(staffWagesVos.get(0).getTotalCommission());
        userVO.setTotalWages(staffWagesVos.get(0).getTotalWages());
        userVO.setMonth(staffWagesVos.get(0).getMonth());
        userVO.setYear(staffWagesVos.get(0).getYear());
        userVO.setBonus(staffWagesVos.get(0).getBonus());
        userVO.setDeduction(staffWagesVos.get(0).getDeduction());
        return userVO;
    }

    /**
     * 新增数据
     *
     * @param hsUser 实例对象
     * @return 实例对象
     */
    @Override
    public HsUser insert(HsUser hsUser) {
        if (hsUserMapper.queryByUsername(hsUser.getUsername())!=null){
            throw new ServiceException("该用户名已存在");
        }else {
            this.hsUserMapper.insert(hsUser);
        }
        return hsUser;
    }

    /**
     * 修改数据
     *
     * @param userVO 实例对象
     * @return 实例对象
     */
    @Override
    public UserVO update(UserVO userVO) {
        if (hsUserMapper.queryByUsername(userVO.getUsername())!=null
                && hsStaffMapper.queryByuserName(userVO.getUsername()).getStaffId()!=userVO.getStaffId()){
//            userVO.setUsername(hsUserMapper.queryById(userVO.getUserId()).getUsername());
            throw new ServiceException("该用户名已存在");
        }else {
            if (hsStaffMapper.queryByNumber(userVO.getStaffNumber())!=null
                    && hsStaffMapper.queryByNumber(userVO.getStaffNumber()).getStaffId()!=userVO.getStaffId()){
//                userVO.setStaffNumber(hsStaffMapper.queryById(userVO.getStaffId()).getStaffNumber());
                throw new ServiceException("该员工编号已存在");
            }else {
                if (hsStaffMapper.queryByPhoneNumber(userVO.getPhoneNumber())!=null
                        && hsStaffMapper.queryByPhoneNumber(userVO.getPhoneNumber()).getStaffId()!=userVO.getStaffId()){
//                    userVO.setPhoneNumber(hsStaffMapper.queryById(userVO.getStaffId()).getPhoneNumber());
                    throw new ServiceException("该电话号码已存在");
                }else {
                    //用户表
                    HsUser hsUser = hsUserMapper.queryById(userVO.getUserId());
                    hsUser.setUsername(userVO.getUsername());
                    hsUserMapper.update(hsUser);
                    //员工表
                    HsStaff hsStaff = hsStaffMapper.queryById(userVO.getStaffId());
                    hsStaff.setUsername(userVO.getUsername());
                    hsStaff.setStaffName(userVO.getStaffName());
                    hsStaff.setStaffNumber(userVO.getStaffNumber());
                    hsStaff.setLevel(userVO.getLevel()+"");
                    hsStaff.setPhoneNumber(userVO.getPhoneNumber());
                    hsStaff.setGender(userVO.getGender());
                    hsStaff.setRoleId(userVO.getRoleId());
                    hsStaff.setBirthday(userVO.getBirthday());
                    hsStaffMapper.update(hsStaff);
                    //员工角色表
                    HsUserRole hsUserRole = hsUserRoleMapper.queryByUserId(userVO.getUserId());
                    hsUserRole.setRoleId(userVO.getRoleId());
                    hsUserRoleMapper.update(hsUserRole);
                }
            }
        }
        return userVO;
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.hsUserMapper.deleteById(userId) > 0;
    }

    /**
     * 通过用户名查找全部
     *
     * @param username
     * @return
     */
    @Override
    public HsUser queryByUsername(String username) {
        return this.hsUserMapper.queryByUsername(username);
    }

    /**
     * 注册判断用户名
     *
     * @param username
     * @return 是否成功
     */
    public boolean username(String username){
        boolean a = true;
        HsUser hsUser = new HsUser();
        List<HsUser> hsUsers = this.hsUserMapper.queryAll(hsUser);
        for(HsUser hsUser1:hsUsers){
            String username1 = hsUser1.getUsername();
            if(username.equals(username1)){
                a = false;
                break;
            }
        }
        return a;
    }

    /**
     * 注册判断
     *
     * @param
     * @return 是否成功
     */
    public boolean register(RegisterVo registerVo) {
        boolean a = true;
        HsStaff hsStaff = new HsStaff();
        hsStaff.setPhoneNumber(registerVo.getPhone());
        List<HsStaff> hsStaffs = this.hsStaffMapper.queryAll(hsStaff);
        if (hsStaffs == null){
            a = false;
        }else{
            HsUser hsUser = new HsUser();
            hsUser.setUsername(registerVo.getUsername());
            String b= registerVo.getPassword();
//            System.out.println(b);
//            System.out.println(hsUser.getUsername());
//            String c = "111111";
            String password =  new BCryptPasswordEncoder().encode(b);
//            Integer d = 1234;
//            Long e = d.longValue();
            hsUser.setPassword(password);
            hsUser.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            this.hsUserMapper.insert(hsUser);
            HsStaff hsStaff1 = new HsStaff();
            hsStaff1.setStaffId(this.hsStaffMapper.queryStaffByTelephone(registerVo.getPhone()).getStaffId());
            hsStaff1.setPhoneNumber(registerVo.getPhone());
            hsStaff1.setUsername(registerVo.getUsername());
            this.hsStaffMapper.update(hsStaff1);
            HsUserRole hsUserRole = new HsUserRole();
            hsUserRole.setRoleId(this.hsStaffMapper.queryStaffByTelephone(registerVo.getPhone()).getRoleId());
            hsUserRole.setUserId(this.hsUserMapper.queryByUsername(registerVo.getUsername()).getUserId());
            this.hsUserRoleMapper.insert(hsUserRole);
        }
//        HsStaff hsStaff = new HsStaff();
//        List<HsStaff> hsStaffs = this.hsStaffMapper.queryAll(hsStaff);
//        for(HsStaff hsStaff1:hsStaffs){
//            Long telephone = hsStaff1.getPhoneNumber();
//            boolean m = registerVo.getPhoneNumber().equals(telephone);
//            if(!m || hsStaff1.getUsername() == null){
//                a = false;
//                break;
//            }
//        }
//        if(a == true){
//            HsUser hsUser = new HsUser();
//            hsUser.setUsername(registerVo.getUsername());
//            String b= registerVo.getPassword();
//            String password = new BCryptPasswordEncoder().encode(b);
//            hsUser.setPassword(password);
//            this.hsUserMapper.insert(hsUser);
//            HsStaff hsStaff1 = new HsStaff();
//            hsStaff1.setStaffId(this.hsStaffMapper.queryStaffByTelephone(registerVo.getPhoneNumber()).getStaffId());
//            hsStaff1.setPhoneNumber(registerVo.getPhoneNumber());
//            hsStaff1.setUsername(registerVo.getUsername());
//            this.hsStaffMapper.update(hsStaff1);
//        }
        return a;
    }

    @Override
    public boolean oldPassword(String oldPassword,String username) {
        System.out.println(oldPassword);
        UserVO userVO = queryByToken(username);
        Integer userId = userVO.getUserId();
        String now = hsUserMapper.queryById(userId).getPassword();
        boolean match = new BCryptPasswordEncoder().matches(oldPassword, now);
        return match;
    }

    @Override
    public String newPassword(String newPassword,String username) {
        UserVO userVO = queryByToken(username);
        Integer userId = userVO.getUserId();
        String now = hsUserMapper.queryById(userId).getPassword();
        System.out.println(now);
        HsUser hsUser = hsUserMapper.queryByUsername(username);
        String password = new BCryptPasswordEncoder().encode(newPassword);
        hsUser.setPassword(password);
        hsUserMapper.update(hsUser);
        return "修改密码成功";
    }


}
