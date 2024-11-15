package com.softcostEstimator.generator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcostEstimator.generator.mapper.SoftwareProjectMapper;
import com.softcostEstimator.generator.domain.SoftwareProject;
import com.softcostEstimator.generator.service.ISoftwareProjectService;

/**
 * Function_point_analysisService业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@Service
public class SoftwareProjectServiceImpl implements ISoftwareProjectService 
{
    @Autowired
    private SoftwareProjectMapper softwareProjectMapper;

    /**
     * 查询Function_point_analysis
     * 
     * @param projectId Function_point_analysis主键
     * @return Function_point_analysis
     */
    @Override
    public SoftwareProject selectSoftwareProjectByProjectId(String projectId)
    {
        return softwareProjectMapper.selectSoftwareProjectByProjectId(projectId);
    }

    /**
     * 查询Function_point_analysis列表
     * 
     * @param softwareProject Function_point_analysis
     * @return Function_point_analysis
     */
    @Override
    public List<SoftwareProject> selectSoftwareProjectList(SoftwareProject softwareProject)
    {
        return softwareProjectMapper.selectSoftwareProjectList(softwareProject);
    }

    /**
     * 新增Function_point_analysis
     * 
     * @param softwareProject Function_point_analysis
     * @return 结果
     */
    @Override
    public int insertSoftwareProject(SoftwareProject softwareProject)
    {
        return softwareProjectMapper.insertSoftwareProject(softwareProject);
    }

    /**
     * 修改Function_point_analysis
     * 
     * @param softwareProject Function_point_analysis
     * @return 结果
     */
    @Override
    public int updateSoftwareProject(SoftwareProject softwareProject)
    {
        return softwareProjectMapper.updateSoftwareProject(softwareProject);
    }

    /**
     * 批量删除Function_point_analysis
     * 
     * @param projectIds 需要删除的Function_point_analysis主键
     * @return 结果
     */
    @Override
    public int deleteSoftwareProjectByProjectIds(String[] projectIds)
    {
        return softwareProjectMapper.deleteSoftwareProjectByProjectIds(projectIds);
    }

    /**
     * 删除Function_point_analysis信息
     * 
     * @param projectId Function_point_analysis主键
     * @return 结果
     */
    @Override
    public int deleteSoftwareProjectByProjectId(String projectId)
    {
        return softwareProjectMapper.deleteSoftwareProjectByProjectId(projectId);
    }
}
