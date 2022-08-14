package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.*;
import edu.scau.misp.hs.mapper.*;
import edu.scau.misp.hs.service.IHsStaffService;
import edu.scau.misp.hs.util.ServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (HsStaff)表服务实现类
 *
 * @author makejava
 * @since 2022-03-20 00:19:21
 */
@Service
//@Service("IHsStaffService")
public class HsStaffServiceImpl implements IHsStaffService {
    @Resource
    private HsStaffMapper hsStaffMapper;
    @Resource
    private HsSalaryMapper hsSalaryMapper;
    @Resource
    private HsSaleCommissionMapper hsSaleCommissionMapper;
    @Resource
    private HsSalaryComMapper hsSalaryComMapper;
    @Resource
    private HsUserRoleMapper hsUserRoleMapper;
    @Resource
    private HsUserMapper hsUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param staffId 主键
     * @return 实例对象
     */
    @Override
    public HsStaff queryById(Integer staffId) {

        return this.hsStaffMapper.queryById(staffId);
    }

    @Override
    public HsStaff queryByuserName(String userName) {
        return this.hsStaffMapper.queryByuserName(userName);
    }

    @Override
    public HsStaff queryBystaffName(String staffName) {
        return this.hsStaffMapper.queryBystaffName(staffName);
    }

    @Override
    public HsStaff queryByNumber(Integer staffNumber) {
        return this.hsStaffMapper.queryByNumber(staffNumber);
    }

    @Override
    public HsStaff queryByPhoneNumber(Long phoneNumber) {
        return this.hsStaffMapper.queryByPhoneNumber(phoneNumber);
    }

    /**
     * 查询指定行数据
     *
     * @param hsStaff 筛选条件
     * @return 查询结果
     */
    @Override
    public List<HsStaff> queryAll(HsStaff hsStaff) {
        return this.hsStaffMapper.queryAll(hsStaff);
    }

    @Override
    public List<HsStaff> queryByRoleId(Integer roleId) {
        return this.hsStaffMapper.queryByRoleId(roleId);
    }

    /**
     * 新增数据
     *
     * @param hsStaff 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(HsStaff hsStaff) {
        //员工表
        hsStaff.setCreationDay(new Date());
        if (hsStaff.getRoleId()==1){
            hsStaff.setLevel("5");
        }else if (hsStaff.getRoleId()==3){
            hsStaff.setLevel("0");
        }else if (hsStaff.getRoleId()==4){
            hsStaff.setLevel("4");
        }
        hsStaff.setUsername(hsStaff.getStaffName());//默认用户名字为真名
        this.hsStaffMapper.insert(hsStaff);
//        //用户表
//        HsUser hsUser = new HsUser();
//        hsUser.setUsername(hsStaff.getUsername());
//        hsUser.setPassword(new BCryptPasswordEncoder().encode("123456"));//默认密码为123456
//        hsUser.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        this.hsUserMapper.insert(hsUser);
//        //用户角色表
//        HsUserRole hsUserRole = new HsUserRole();
//        hsUserRole.setUserId(hsUser.getUserId());
//        hsUserRole.setRoleId(hsStaff.getRoleId());
//        this.hsUserRoleMapper.insert(hsUserRole);
    }

    /**
     * 修改数据
     *
     * @param hsStaff 实例对象
     * @return 实例对象
     */
    @Override
    public void update(HsStaff hsStaff) {
        this.hsStaffMapper.update(hsStaff);
    }

    /**
     * 通过主键删除数据
     *
     * @param staffId 主键
     * @return 是否成功
     */
    boolean a,b,c,d;
    @Override
    public boolean deleteById(Integer staffId) {
        List<HsSalary> hsSalaryList = hsSalaryMapper.queryByStaffAndTime(staffId);
        List<HsSaleCommission> hsSaleCommissionList = hsSaleCommissionMapper.queryByStaff(staffId);
        if (hsSaleCommissionList != null && !hsSaleCommissionList.isEmpty()){  //业绩表
            for (HsSaleCommission hsSaleCommission : hsSaleCommissionList){
                a = hsSaleCommissionMapper.deleteById(hsSaleCommission.getSaleCommissionId())>0;
            }
            if ( a && hsSalaryList!=null && ! hsSalaryList.isEmpty()){  //工资表
                for (HsSalary hsSalary : hsSalaryList){
                    List<HsSalaryCom> hsSalaryComList = hsSalaryComMapper.queryBySalary(hsSalary.getSalaryId());//工资业绩表
                    for (HsSalaryCom hsSalaryCom : hsSalaryComList){
                        d = hsSalaryComMapper.deleteById(hsSalaryCom.getSalaryComId())>0;
                    }
                    b = hsSalaryMapper.deleteById(hsSalary.getSalaryId())>0;
                }
                if (b){
                    c = hsStaffMapper.deleteById(staffId) > 0;
                }else {
                    throw new ServiceException("删除失败");
                }
            }else if (a && hsSalaryList == null){
                c = hsStaffMapper.deleteById(staffId) > 0;
            }
        }else{
            if ( hsSalaryList!=null && ! hsSalaryList.isEmpty()){
                for (HsSalary hsSalary : hsSalaryList){
                    b = hsSalaryMapper.deleteById(hsSalary.getSalaryId())>0;
                }
                if (b){
                    c = hsStaffMapper.deleteById(staffId) > 0;
                }else {
                    throw new ServiceException("删除失败");
                }
            }else if (hsSalaryList == null){
                c = hsStaffMapper.deleteById(staffId) > 0;
            }
        }
        return c;
    }
}
