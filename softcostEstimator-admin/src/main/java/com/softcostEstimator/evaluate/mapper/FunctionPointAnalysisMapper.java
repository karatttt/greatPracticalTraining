package com.softcostEstimator.evaluate.mapper;

import java.util.List;
import com.softcostEstimator.evaluate.domain.FunctionPointAnalysis;

/**
 * 功能点分析Mapper接口
 * 
 * @author zzq
 * @date 2024-11-14
 */
public interface FunctionPointAnalysisMapper 
{
    /**
     * 查询功能点分析
     * 
     * @param projectId 功能点分析主键
     * @return 功能点分析
     */
    public FunctionPointAnalysis selectFunctionPointAnalysisByProjectId(Long projectId);

    /**
     * 查询功能点分析列表
     * 
     * @param functionPointAnalysis 功能点分析
     * @return 功能点分析集合
     */
    public List<FunctionPointAnalysis> selectFunctionPointAnalysisList(FunctionPointAnalysis functionPointAnalysis);

    /**
     * 新增功能点分析
     * 
     * @param functionPointAnalysis 功能点分析
     * @return 结果
     */
    public int insertFunctionPointAnalysis(FunctionPointAnalysis functionPointAnalysis);

    /**
     * 修改功能点分析
     * 
     * @param functionPointAnalysis 功能点分析
     * @return 结果
     */
    public int updateFunctionPointAnalysis(FunctionPointAnalysis functionPointAnalysis);

    /**
     * 删除功能点分析
     * 
     * @param projectId 功能点分析主键
     * @return 结果
     */
    public int deleteFunctionPointAnalysisByProjectId(Long projectId);

    /**
     * 批量删除功能点分析
     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFunctionPointAnalysisByProjectIds(Long[] projectIds);
    List<FunctionPointAnalysis> searchFunctionPointAnalysisList(FunctionPointAnalysis functionPointAnalysis);

}
