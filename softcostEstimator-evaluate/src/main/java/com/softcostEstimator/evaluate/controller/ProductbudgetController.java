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
import com.softcostEstimator.evaluate.domain.Productbudget;
import com.softcostEstimator.evaluate.service.IProductbudgetService;
import com.softcostEstimator.common.utils.poi.ExcelUtil;
import com.softcostEstimator.common.core.page.TableDataInfo;

/**
 * 造价评估Controller
 * 
 * @author li
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/evaluate/productbudget")
public class ProductbudgetController extends BaseController
{
    @Autowired
    private IProductbudgetService productbudgetService;

    /**
     * 查询造价评估列表
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:list')")
    @GetMapping("/list")
    public TableDataInfo list(Productbudget productbudget)
    {
        startPage();
        List<Productbudget> list = productbudgetService.selectProductbudgetList(productbudget);
        return getDataTable(list);
    }

    /**
     * 导出造价评估列表
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:export')")
    @Log(title = "造价评估", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Productbudget productbudget)
    {
        List<Productbudget> list = productbudgetService.selectProductbudgetList(productbudget);
        ExcelUtil<Productbudget> util = new ExcelUtil<Productbudget>(Productbudget.class);
        util.exportExcel(response, list, "造价评估数据");
    }

    /**
     * 获取造价评估详细信息
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:query')")
    @GetMapping(value = "/{productID}")
    public AjaxResult getInfo(@PathVariable("productID") Long productID)
    {
        return success(productbudgetService.selectProductbudgetByProductID(productID));
    }

    /**
     * 新增造价评估
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:add')")
    @Log(title = "造价评估", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Productbudget productbudget)
    {
        return toAjax(productbudgetService.insertProductbudget(productbudget));
    }

    /**
     * 修改造价评估
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:edit')")
    @Log(title = "造价评估", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Productbudget productbudget)
    {
        return toAjax(productbudgetService.updateProductbudget(productbudget));
    }

    /**
     * 删除造价评估
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:remove')")
    @Log(title = "造价评估", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIDs}")
    public AjaxResult remove(@PathVariable Long[] productIDs)
    {
        return toAjax(productbudgetService.deleteProductbudgetByProductIDs(productIDs));
    }
}
