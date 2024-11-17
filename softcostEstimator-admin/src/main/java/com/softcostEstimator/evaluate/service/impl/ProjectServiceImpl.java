package com.softcostEstimator.evaluate.service.impl;

import java.util.List;
import com.softcostEstimator.common.utils.DateUtils;
import com.softcostEstimator.evaluate.domain.Productbudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcostEstimator.evaluate.mapper.ProjectMapper;
import com.softcostEstimator.evaluate.domain.Project;
import com.softcostEstimator.evaluate.service.IProjectService;

/**
 * 项目信息Service业务层处理
 * 
 * @author zzq
 * @date 2024-11-14
 */
@Service
public class ProjectServiceImpl implements IProjectService 
{
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 查询项目信息
     * 
     * @param projectID 项目信息主键
     * @return 项目信息
     */
    @Override
    public Project selectProjectByProjectID(Long projectID)
    {
        return projectMapper.selectProjectByProjectID(projectID);
    }

    /**
     * 查询项目信息列表
     * 
     * @param project 项目信息
     * @return 项目信息
     */
    @Override
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public int insertProject(Project project)
    {
        project.setCreateTime(DateUtils.getNowDate());
        return projectMapper.insertProject(project);
    }

    /**
     * 修改项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public int updateProject(Project project)
    {
        return projectMapper.updateProject(project);
    }

    /**
     * 批量删除项目信息
     * 
     * @param projectIDs 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectByProjectIDs(Long[] projectIDs)
    {
        return projectMapper.deleteProjectByProjectIDs(projectIDs);
    }

    /**
     * 删除项目信息信息
     * 
     * @param projectID 项目信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectByProjectID(Long projectID)
    {
        return projectMapper.deleteProjectByProjectID(projectID);
    }


}
