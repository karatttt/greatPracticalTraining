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
import com.softcostEstimator.evaluate.AI.MessageUtil;
import com.softcostEstimator.evaluate.AI.Response;
import com.softcostEstimator.evaluate.domain.FunctionPointAnalysis;
import com.softcostEstimator.evaluate.domain.Project;
import com.softcostEstimator.evaluate.domain.ReportData;
import com.softcostEstimator.evaluate.domain.request.AiGenenrateReportRequest;
import com.softcostEstimator.evaluate.service.IFunctionPointAnalysisService;
import com.softcostEstimator.evaluate.service.IProjectService;
import com.softcostEstimator.evaluate.transform.TransFactory;
import com.softcostEstimator.evaluate.transform.TransUtil;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @date 2024-11-15
 */
@RestController
@RequestMapping("/evaluate/productbudget")
@Slf4j
public class ProductbudgetController extends BaseController
{
    @Resource
    private IProductbudgetService productbudgetService;

    @Resource
    private IProjectService projectService;

    @Resource
    private IFunctionPointAnalysisService functionPointAnalysisService;

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
    @GetMapping(value = "/{projectID}")
    public AjaxResult getInfo(@PathVariable("projectID") Long projectID)
    {
        return success(productbudgetService.selectProductbudgetByProjectID(projectID));
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
    @DeleteMapping("/{projectIDs}")
    public AjaxResult remove(@PathVariable Long[] projectIDs)
    {
        return toAjax(productbudgetService.deleteProductbudgetByProjectIDs(projectIDs));
    }
    @PreAuthorize("@ss.hasPermi('evaluate:productbudget:generate:report')")
    @Log(title = "综合评估", businessType = BusinessType.GENERATE_REPORT)
    @GetMapping("/generate/report")
    public void generateReportByAi(Long id) throws NoApiKeyException, InputRequiredException, InterruptedException {
       Long projectId = id;

        //根据id获取所有实体类
        FunctionPointAnalysis functionPointAnalysis = functionPointAnalysisService.selectFunctionPointAnalysisByProjectId(projectId);
        Project project = projectService.selectProjectByProjectID(projectId);
        Productbudget productbudget = productbudgetService.selectProductbudgetByProductID2(projectId);
        //将每个实体类转为json格式
        String functionJson = functionPointAnalysisService.getJson(functionPointAnalysis);
        String projectJson = projectService.getJson(project);
        String productbudgetJson = productbudgetService.getJson(productbudget);
        //去括号
        String subFunctionJson = functionJson.substring(1,functionJson.length()-1);
        String subProjectJson = projectJson.substring(1,projectJson.length()-1);
        String subProductBudgetJson = productbudgetJson.substring(1,productbudgetJson.length()-1);
        //合并
        StringBuilder json = new StringBuilder().append(subFunctionJson).append(subProjectJson).append(subProductBudgetJson);
        StringBuilder output = new StringBuilder();
        Thread.sleep(3000);
        MessageUtil.questionInit("这是软件造价通的数据，后面我们的问题都根据这个数据回答：" + json.toString());

        MessageUtil.streamCall2(json.toString());


//        ApplicationParam param = ApplicationParam.builder()
//                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
//                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
//                .appId("18de7db29ca44e629d156837394a7a3b")
//                .prompt(json.toString())
//                .incrementalOutput(true)
//                .build();
//        Application application = new Application();
//        try{
//            Flowable<ApplicationResult> result = application.streamCall(param);
//            result.blockingForEach(data -> {
//                output.append(data.getOutput().getText());
//
//            });
//        }catch (ApiException | NoApiKeyException | InputRequiredException e){
//            log.error(e.getMessage());
//        }
//
//        TransUtil transUtil = TransFactory.newInstance("html");
//        String path = transUtil.transForm(output.toString());
//        return new ReportData(output.toString());
//        return ResultUtils.success(output.toString());
    }
    @GetMapping("/pollReport")
    public Response handleLongPolling() throws InterruptedException, NoApiKeyException, InputRequiredException {

        // 设置超时时间，例如5分钟
        Response message = MessageUtil.getQueue2().poll();  // 如果有消息，直接返回；如果没有，则阻塞直到有消息
        if (message == null) {
            // 如果没有消息，阻塞5分钟，模拟超时机制
            message = MessageUtil.getQueue2().take();  // 这里会阻塞直到有消息或超时
        }
        return message;  // 返回消息
    }


    @PostMapping("/transform/report")
    public BaseResponse<String> transformReport(@RequestBody String text,String type){
        if(StringUtils.isAllBlank(text,type)){
            throw new RuntimeException("内容为空");
        }
        TransUtil transUtil = TransFactory.newInstance(type);
        String path = transUtil.transForm(text);
        if(StringUtils.isBlank(path)){
            throw new RuntimeException("未找到");
        }
        return ResultUtils.success(path);
    }
}
