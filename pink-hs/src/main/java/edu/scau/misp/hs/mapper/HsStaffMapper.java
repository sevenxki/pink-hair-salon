package edu.scau.misp.hs.mapper;

import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.entity.HsStaff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (HsStaff)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-20 00:19:21
 */
@Mapper
@Repository
public interface HsStaffMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param staffId 主键
     * @return 实例对象
     */
    HsStaff queryById(Integer staffId);

    HsStaff queryByuserName(String userName);

    HsStaff queryBystaffName(String staffName);

    HsStaff queryByNumber(Integer staffNumber);

    HsStaff queryByPhoneNumber(Long phoneNumber);

    /**
     * 查询指定行数据
     *
     * @param hsStaff 查询条件
     * @return 对象列表
     */
    List<HsStaff> queryAll(HsStaff hsStaff);

    List<HsStaff> queryByRoleId(Integer roleId);

    /**
     * 统计总行数
     *
     * @param hsStaff 查询条件
     * @return 总行数
     */
    long count(HsStaff hsStaff);

    /**
     * 新增数据
     *
     * @param hsStaff 实例对象
     * @return 影响行数
     */
    int insert(HsStaff hsStaff);

//    /**
//     * 批量新增数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsStaff> 实例对象列表
//     * @return 影响行数
//     */
//    int insertBatch(@Param("entities") List<HsStaff> entities);
//
//    /**
//     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
//     *
//     * @param entities List<HsStaff> 实例对象列表
//     * @return 影响行数
//     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
//     */
//    int insertOrUpdateBatch(@Param("entities") List<HsStaff> entities);

    /**
     * 修改数据
     *
     * @param hsStaff 实例对象
     * @return 影响行数
     */
    int update(HsStaff hsStaff);

    /**
     * 通过主键删除数据
     *
     * @param staffId 主键
     * @return 影响行数
     */
    int deleteById(Integer staffId);

    /**
     * 查找技师name
     * @param
     * @return
     */
    List<String> queryTechnician();

    /**
     * 查找技师all
     * @param
     * @return
     */
    List<HsStaff> queryTechnicianAll();

    /**
     *用员工姓名查找Id
     * @param
     * @return
     */
    HsStaff queryStaffIdByName(String staffName);

    /**
     * 查询指定行数据
     *
     * @param
     * @return 对象列表
     */
    List<HsStaff> allStaff();

    /**
     *用员工电话查找
     * @param
     * @return
     */
    HsStaff queryStaffByTelephone(Long phoneNumber);

    /**
     *查询（用username）
     * @param
     * @return
     */
    HsStaff queryByUsername(String username);
}

