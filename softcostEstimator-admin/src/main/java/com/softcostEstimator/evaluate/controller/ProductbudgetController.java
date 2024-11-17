package com.softcostEstimator.evaluate.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dashscope.app.Application;
import com.alibaba.dashscope.app.ApplicationParam;
import com.alibaba.dashscope.app.ApplicationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.threads.ContentText;
import com.softcostEstimator.common.BaseResponse;
import com.softcostEstimator.common.ResultUtils;
import com.softcostEstimator.common.core.domain.R;
import io.reactivex.Flowable;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;
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
 * 综合评估Controller
 * 
 * @author ruoyi
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/evaluate/productbudget")
@Slf4j
public class ProductbudgetController extends BaseController
{
    @Autowired
    private IProductbudgetService productbudgetService;


    /**
     * 查询综合评估列表
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
     * 导出综合评估列表
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:export')")
    @Log(title = "综合评估", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Productbudget productbudget)
    {
        List<Productbudget> list = productbudgetService.selectProductbudgetList(productbudget);
        ExcelUtil<Productbudget> util = new ExcelUtil<Productbudget>(Productbudget.class);
        util.exportExcel(response, list, "综合评估数据");
    }

    /**
     * 获取综合评估详细信息
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:query')")
    @GetMapping(value = "/{productID}")
    public AjaxResult getInfo(@PathVariable("productID") Long productID)
    {
        return success(productbudgetService.selectProductbudgetByProductID(productID));
    }

    /**
     * 新增综合评估
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:add')")
    @Log(title = "综合评估", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Productbudget productbudget)
    {
        return toAjax(productbudgetService.insertProductbudget(productbudget));
    }

    /**
     * 修改综合评估
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:edit')")
    @Log(title = "综合评估", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Productbudget productbudget)
    {
        return toAjax(productbudgetService.updateProductbudget(productbudget));
    }

    /**
     * 删除综合评估
     */
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:remove')")
    @Log(title = "综合评估", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIDs}")
    public AjaxResult remove(@PathVariable Long[] productIDs)
    {
        return toAjax(productbudgetService.deleteProductbudgetByProductIDs(productIDs));
    }

    @PostMapping("/generate/report")
    public BaseResponse<Text> generateReportByAi(Productbudget productbudget) {
        String json = productbudgetService.getJson(productbudget);
        Text text = new Text();
        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
                .appId("18de7db29ca44e629d156837394a7a3b")
                .prompt(json)
                .incrementalOutput(true)
                .build();
        Application application = new Application();
        try{
            Flowable<ApplicationResult> result = application.streamCall(param);
            result.blockingForEach(data -> {
                text.setText(data.getOutput().getText());

            });
        }catch (ApiException | NoApiKeyException | InputRequiredException e){
            log.error(e.getMessage());
        }
        return ResultUtils.success(text);


    }
}
