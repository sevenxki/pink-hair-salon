package edu.scau.misp.hs.service.impl;

import edu.scau.misp.hs.entity.HsWageRules;
import edu.scau.misp.hs.mapper.HsWageRulesMapper;
import edu.scau.misp.hs.service.HsWageRulesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsWageRules)表服务实现类
 *
 * @author makejava
 * @since 2022-04-29 13:26:26
 */
@Service("hsWageRulesService")
public class HsWageRulesServiceImpl implements HsWageRulesService {
    @Resource
    private HsWageRulesMapper hsWageRulesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param wageRulesId 主键
     * @return 实例对象
     */
    @Override
    public HsWageRules queryById(Integer wageRulesId) {
        return this.hsWageRulesMapper.queryById(wageRulesId);
    }

    @Override
    public HsWageRules queryByRoleId(Integer level) {
        return this.hsWageRulesMapper.queryByRoleId(level);
    }

//    /**
//     * 分页查询
//     *
//     * @param hsWageRules 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<HsWageRules> queryByPage(HsWageRules hsWageRules, PageRequest pageRequest) {
//        long total = this.hsWageRulesMapper.count(hsWageRules);
//        return new PageImpl<>(this.hsWageRulesMapper.queryAllByLimit(hsWageRules, pageRequest), pageRequest, total);
//    }

    /**
     * 查询指定行数据
     *
     * @param hsWageRules 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsWageRules> queryAll(HsWageRules hsWageRules) {
        return this.hsWageRulesMapper.queryAll(hsWageRules);
    }

    /**
     * 新增数据
     *
     * @param hsWageRules 实例对象
     * @return 实例对象
     */
    @Override
    public HsWageRules insert(HsWageRules hsWageRules) {
        this.hsWageRulesMapper.insert(hsWageRules);
        return hsWageRules;
    }

    /**
     * 修改数据
     *
     * @param hsWageRules 实例对象
     * @return 实例对象
     */
    @Override
    public HsWageRules update(HsWageRules hsWageRules) {
        this.hsWageRulesMapper.update(hsWageRules);
        return this.queryById(hsWageRules.getWageRulesId());
    }

    /**
     * 通过主键删除数据
     *
     * @param wageRulesId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer wageRulesId) {
        return this.hsWageRulesMapper.deleteById(wageRulesId) > 0;
    }
}
