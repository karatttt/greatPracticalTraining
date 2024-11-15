package com.softcostEstimator.generator.mapper;

import java.util.List;
import com.softcostEstimator.generator.domain.SoftwareProject;

/**
 * Function_point_analysisMapper接口
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
public interface SoftwareProjectMapper 
{
    /**
     * 查询Function_point_analysis
     * 
     * @param projectId Function_point_analysis主键
     * @return Function_point_analysis
     */
    public SoftwareProject selectSoftwareProjectByProjectId(String projectId);

    /**
     * 查询Function_point_analysis列表
     * 
     * @param softwareProject Function_point_analysis
     * @return Function_point_analysis集合
     */
    public List<SoftwareProject> selectSoftwareProjectList(SoftwareProject softwareProject);

    /**
     * 新增Function_point_analysis
     * 
     * @param softwareProject Function_point_analysis
     * @return 结果
     */
    public int insertSoftwareProject(SoftwareProject softwareProject);

    /**
     * 修改Function_point_analysis
     * 
     * @param softwareProject Function_point_analysis
     * @return 结果
     */
    public int updateSoftwareProject(SoftwareProject softwareProject);

    /**
     * 删除Function_point_analysis
     * 
     * @param projectId Function_point_analysis主键
     * @return 结果
     */
    public int deleteSoftwareProjectByProjectId(String projectId);

    /**
     * 批量删除Function_point_analysis
     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSoftwareProjectByProjectIds(String[] projectIds);
}
