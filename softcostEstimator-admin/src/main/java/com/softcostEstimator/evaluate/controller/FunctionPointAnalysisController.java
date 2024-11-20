package com.softcostEstimator.evaluate.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.softcostEstimator.common.annotation.Log;
import com.softcostEstimator.common.core.controller.BaseController;
import com.softcostEstimator.common.core.domain.AjaxResult;
import com.softcostEstimator.common.enums.BusinessType;
import com.softcostEstimator.evaluate.domain.FunctionPointAnalysis;
import com.softcostEstimator.evaluate.service.IFunctionPointAnalysisService;
import com.softcostEstimator.common.utils.poi.ExcelUtil;
import com.softcostEstimator.common.core.page.TableDataInfo;


/**
 * 功能点分析Controller
 * 
 * @author zzq
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/evaluate/analysis")
public class FunctionPointAnalysisController extends BaseController
{
    @Autowired
    private IFunctionPointAnalysisService functionPointAnalysisService;

    /**
     * 查询功能点分析列表
     */
    @PreAuthorize("@ss.hasPermi('evaluate:analysis:list')")
    @GetMapping("/list")
    public TableDataInfo list(FunctionPointAnalysis functionPointAnalysis)
    {
        startPage();
        List<FunctionPointAnalysis> list = functionPointAnalysisService.selectFunctionPointAnalysisList(functionPointAnalysis);
        return getDataTable(list);
    }

    /**
     * 导出功能点分析列表
     */
    @PreAuthorize("@ss.hasPermi('evaluate:analysis:export')")
    @Log(title = "功能点分析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FunctionPointAnalysis functionPointAnalysis)
    {
        List<FunctionPointAnalysis> list = functionPointAnalysisService.selectFunctionPointAnalysisList(functionPointAnalysis);
        ExcelUtil<FunctionPointAnalysis> util = new ExcelUtil<FunctionPointAnalysis>(FunctionPointAnalysis.class);
        util.exportExcel(response, list, "功能点分析数据");
    }

    /**
     * 获取功能点分析详细信息
     */
    @PreAuthorize("@ss.hasPermi('evaluate:analysis:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(functionPointAnalysisService.selectFunctionPointAnalysisByProjectId(projectId));
    }

    /**
     * 新增功能点分析
     */
    @PreAuthorize("@ss.hasPermi('evaluate:analysis:add')")
    @Log(title = "功能点分析", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FunctionPointAnalysis functionPointAnalysis)
    {
        System.out.println(functionPointAnalysis);
        return toAjax(functionPointAnalysisService.insertFunctionPointAnalysis(functionPointAnalysis));
    }

    /**
     * 修改功能点分析
     */
    @PreAuthorize("@ss.hasPermi('evaluate:analysis:edit')")
    @Log(title = "功能点分析", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FunctionPointAnalysis functionPointAnalysis)
    {
        return toAjax(functionPointAnalysisService.updateFunctionPointAnalysis(functionPointAnalysis));
    }

    /**
     * 删除功能点分析
     */
    @PreAuthorize("@ss.hasPermi('evaluate:analysis:remove')")
    @Log(title = "功能点分析", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(functionPointAnalysisService.deleteFunctionPointAnalysisByProjectIds(projectIds));
    }

}
