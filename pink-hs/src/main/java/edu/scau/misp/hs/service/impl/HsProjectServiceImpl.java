package edu.scau.misp.hs.service.impl;


import edu.scau.misp.hs.entity.HsKind;
import edu.scau.misp.hs.entity.HsProject;
import edu.scau.misp.hs.mapper.HsProjectMapper;
import edu.scau.misp.hs.service.HsProjectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HsProject)表服务实现类
 *
 */
@Service("hsProjectService")
public class HsProjectServiceImpl implements HsProjectService {
    @Resource
    private HsProjectMapper hsProjectMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param projectId 主键
     * @return 实例对象
     */
    @Override
    public HsProject queryById(Integer projectId) {
        return this.hsProjectMapper.queryById(projectId);
    }

    /**
     * 查询指定行数据
     *
     * @param hsProject 筛选条件
     * @return 对象列表
     */
    @Override
    public List<HsProject> queryAll(HsProject hsProject) {
        return this.hsProjectMapper.queryAll(hsProject);
    }


    /**
     * 新增数据
     *
     * @param hsProject 实例对象
     * @return 实例对象
     */
    @Override
    public HsProject insert(HsProject hsProject) {
        this.hsProjectMapper.insert(hsProject);
        return hsProject;
    }

    /**
     * 修改数据
     *
     * @param hsProject 实例对象
     * @return 实例对象
     */
    @Override
    public HsProject update(HsProject hsProject) {
        this.hsProjectMapper.update(hsProject);
        return this.queryById(hsProject.getProjectId());
    }

    /**
     * 通过主键删除数据
     *
     * @param projectId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer projectId) {
        return this.hsProjectMapper.deleteById(projectId) > 0;
    }

    /**
     * 查找价格区间
     *
     * @param hsPrice
     */
    @Override
    public List<HsProject> hsPrice(String hsPrice){
        int price =Integer.parseInt(hsPrice);
        return this.hsProjectMapper.price(price);
    }


}
