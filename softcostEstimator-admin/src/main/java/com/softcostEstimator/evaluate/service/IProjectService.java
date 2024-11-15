package com.softcostEstimator.evaluate.service;

import java.util.List;

import com.softcostEstimator.evaluate.domain.Productbudget;
import com.softcostEstimator.evaluate.domain.Project;

/**
 * 项目信息Service接口
 * 
 * @author zzq
 * @date 2024-11-14
 */
public interface IProjectService 
{
    /**
     * 查询项目信息
     * 
     * @param projectID 项目信息主键
     * @return 项目信息
     */
    public Project selectProjectByProjectID(Long projectID);

    /**
     * 查询项目信息列表
     * 
     * @param project 项目信息
     * @return 项目信息集合
     */
    public List<Project> selectProjectList(Project project);

    /**
     * 新增项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(Project project);

    /**
     * 修改项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(Project project);

    /**
     * 批量删除项目信息
     * 
     * @param projectIDs 需要删除的项目信息主键集合
     * @return 结果
     */
    public int deleteProjectByProjectIDs(Long[] projectIDs);

    /**
     * 删除项目信息信息
     * 
     * @param projectID 项目信息主键
     * @return 结果
     */
    public int deleteProjectByProjectID(Long projectID);

}
