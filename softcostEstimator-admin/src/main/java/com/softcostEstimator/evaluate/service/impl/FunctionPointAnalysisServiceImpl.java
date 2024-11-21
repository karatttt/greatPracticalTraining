package com.softcostEstimator.evaluate.service.impl;

import java.util.List;

import cn.hutool.json.JSONUtil;
import com.softcostEstimator.evaluate.domain.Productbudget;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softcostEstimator.evaluate.mapper.FunctionPointAnalysisMapper;
import com.softcostEstimator.evaluate.domain.FunctionPointAnalysis;
import com.softcostEstimator.evaluate.service.IFunctionPointAnalysisService;

import javax.annotation.Resource;

/**
 * 功能点分析Service业务层处理
 * 
 * @author zzq
 * @date 2024-11-14
 */
@Service
public class FunctionPointAnalysisServiceImpl implements IFunctionPointAnalysisService 
{
    @Resource
    private FunctionPointAnalysisMapper functionPointAnalysisMapper;

    /**
     * 查询功能点分析
     * 
     * @param projectId 功能点分析主键
     * @return 功能点分析
     */
    @Override
    public FunctionPointAnalysis selectFunctionPointAnalysisByProjectId(Long projectId)
    {
        return functionPointAnalysisMapper.selectFunctionPointAnalysisByProjectId(projectId);
    }

    /**
     * 查询功能点分析列表
     * 
     * @param functionPointAnalysis 功能点分析
     * @return 功能点分析
     */
    @Override
    public List<FunctionPointAnalysis> selectFunctionPointAnalysisList(FunctionPointAnalysis functionPointAnalysis)
    {
        return functionPointAnalysisMapper.selectFunctionPointAnalysisList(functionPointAnalysis);
    }
    /**
     * 根据条件查询功能点分析列表
     *
     * @param functionPointAnalysis 包含查询条件的对象
     * @return 功能点分析列表
     */
    @Override
    public List<FunctionPointAnalysis> searchFunctionPointAnalysisList(FunctionPointAnalysis functionPointAnalysis) {
        return functionPointAnalysisMapper.searchFunctionPointAnalysisList(functionPointAnalysis);
    }
    /**
     * 新增功能点分析
     * 
     * @param functionPointAnalysis 功能点分析
     * @return 结果
     */
    @Override
    public int insertFunctionPointAnalysis(FunctionPointAnalysis functionPointAnalysis)
    {
        return functionPointAnalysisMapper.insertFunctionPointAnalysis(functionPointAnalysis);
    }

    /**
     * 修改功能点分析
     * 
     * @param functionPointAnalysis 功能点分析
     * @return 结果
     */
    @Override
    public int updateFunctionPointAnalysis(FunctionPointAnalysis functionPointAnalysis)
    {
        return functionPointAnalysisMapper.updateFunctionPointAnalysis(functionPointAnalysis);
    }

    /**
     * 批量删除功能点分析
     * 
     * @param projectIds 需要删除的功能点分析主键
     * @return 结果
     */
    @Override
    public int deleteFunctionPointAnalysisByProjectIds(Long[] projectIds)
    {
        return functionPointAnalysisMapper.deleteFunctionPointAnalysisByProjectIds(projectIds);
    }

    /**
     * 删除功能点分析信息
     * 
     * @param projectId 功能点分析主键
     * @return 结果
     */
    @Override
    public int deleteFunctionPointAnalysisByProjectId(Long projectId)
    {
        return functionPointAnalysisMapper.deleteFunctionPointAnalysisByProjectId(projectId);
    }
    @Override
    public String getJson(FunctionPointAnalysis functionPointAnalysis) {

        String json = JSONUtil.toJsonStr(functionPointAnalysis);
        return json;
    }
    @Override
    public List<Map<String, Object>> loadFunctionPointAnalysis(FunctionPointAnalysis functionPointAnalysis) {
        List<Map<String, Object>> result = functionPointAnalysisMapper.loadFunctionPointAnalysis(functionPointAnalysis);
        return result;
    }
}
