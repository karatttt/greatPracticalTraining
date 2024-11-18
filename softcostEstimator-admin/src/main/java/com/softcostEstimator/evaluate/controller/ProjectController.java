package com.softcostEstimator.evaluate.controller;

import java.util.List;
import javax.annotation.Resource;
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
import com.softcostEstimator.evaluate.domain.Project;
import com.softcostEstimator.evaluate.service.IProjectService;
import com.softcostEstimator.common.utils.poi.ExcelUtil;
import com.softcostEstimator.common.core.page.TableDataInfo;

/**
 * 项目信息Controller
 * 
 * @author zzq
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/evaluate/project")
public class ProjectController extends BaseController
{
    @Resource
    private IProjectService projectService;

    /**
     * 查询项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('evaluate:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }

    /**
     * 导出项目信息列表
     */
    @PreAuthorize("@ss.hasPermi('evaluate:project:export')")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Project project)
    {
        List<Project> list = projectService.selectProjectList(project);
        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
        util.exportExcel(response, list, "项目信息数据");
    }

    /**
     * 获取项目信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('evaluate:project:query')")
    @GetMapping(value = "/{projectID}")
    public AjaxResult getInfo(@PathVariable("projectID") Long projectID)
    {
        return success(projectService.selectProjectByProjectID(projectID));
    }

    /**
     * 新增项目信息
     */
    @PreAuthorize("@ss.hasPermi('evaluate:project:add')")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Project project)
    {
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目信息
     */
    @PreAuthorize("@ss.hasPermi('evaluate:project:edit')")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Project project)
    {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目信息
     */
    @PreAuthorize("@ss.hasPermi('evaluate:project:remove')")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIDs}")
    public AjaxResult remove(@PathVariable Long[] projectIDs)
    {
        return toAjax(projectService.deleteProjectByProjectIDs(projectIDs));
    }
}
