package com.softcostEstimator.generator.controller;

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
import com.softcostEstimator.generator.domain.SoftwareProject;
import com.softcostEstimator.generator.service.ISoftwareProjectService;
import com.softcostEstimator.common.utils.poi.ExcelUtil;
import com.softcostEstimator.common.core.page.TableDataInfo;

/**
 * Function_point_analysisController
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/point_analysis/project")
public class SoftwareProjectController extends BaseController
{
    @Autowired
    private ISoftwareProjectService softwareProjectService;

    /**
     * 查询Function_point_analysis列表
     */
    @PreAuthorize("@ss.hasPermi('point_analysis:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(SoftwareProject softwareProject)
    {
        startPage();
        List<SoftwareProject> list = softwareProjectService.selectSoftwareProjectList(softwareProject);
        return getDataTable(list);
    }

    /**
     * 导出Function_point_analysis列表
     */
    @PreAuthorize("@ss.hasPermi('point_analysis:project:export')")
    @Log(title = "Function_point_analysis", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SoftwareProject softwareProject)
    {
        List<SoftwareProject> list = softwareProjectService.selectSoftwareProjectList(softwareProject);
        ExcelUtil<SoftwareProject> util = new ExcelUtil<SoftwareProject>(SoftwareProject.class);
        util.exportExcel(response, list, "Function_point_analysis数据");
    }

    /**
     * 获取Function_point_analysis详细信息
     */
    @PreAuthorize("@ss.hasPermi('point_analysis:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") String projectId)
    {
        return success(softwareProjectService.selectSoftwareProjectByProjectId(projectId));
    }

    /**
     * 新增Function_point_analysis
     */
    @PreAuthorize("@ss.hasPermi('point_analysis:project:add')")
    @Log(title = "Function_point_analysis", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SoftwareProject softwareProject)
    {
        return toAjax(softwareProjectService.insertSoftwareProject(softwareProject));
    }

    /**
     * 修改Function_point_analysis
     */
    @PreAuthorize("@ss.hasPermi('point_analysis:project:edit')")
    @Log(title = "Function_point_analysis", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SoftwareProject softwareProject)
    {
        return toAjax(softwareProjectService.updateSoftwareProject(softwareProject));
    }

    /**
     * 删除Function_point_analysis
     */
    @PreAuthorize("@ss.hasPermi('point_analysis:project:remove')")
    @Log(title = "Function_point_analysis", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable String[] projectIds)
    {
        return toAjax(softwareProjectService.deleteSoftwareProjectByProjectIds(projectIds));
    }
}
