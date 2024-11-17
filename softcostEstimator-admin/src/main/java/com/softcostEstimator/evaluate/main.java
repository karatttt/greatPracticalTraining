package com.softcostEstimator.evaluate;

import com.alibaba.dashscope.app.Application;
import com.alibaba.dashscope.app.ApplicationParam;
import com.alibaba.dashscope.app.ApplicationResult;
import com.alibaba.dashscope.app.ApplicationUsage;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.softcostEstimator.evaluate.domain.Productbudget;
import com.softcostEstimator.evaluate.service.IProductbudgetService;
import com.softcostEstimator.evaluate.service.impl.ProductbudgetServiceImpl;


public class main {
    public static void appCall()
            throws ApiException, NoApiKeyException, InputRequiredException {

        IProductbudgetService productbudgetService = new ProductbudgetServiceImpl();

        Productbudget productbudget = new Productbudget();
        productbudget.setProductID(1L);
        productbudget.setAT(1L);
        productbudget.setBD(5L);
        productbudget.setDNC(6L);
        productbudget.setDT(7L);
        productbudget.setESDC(8L);
        productbudget.setF(9L);
        productbudget.setPDR(10L);
        productbudget.setRSK(11L);
        productbudget.setSF(12L);
        productbudget.setSL(13L);
        String json = productbudgetService.getJson(productbudget);
//        String jsonStr = json.substring(1,json.length()-1);




        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
                .appId("18de7db29ca44e629d156837394a7a3b")
                .prompt(json)
                .build();

        Application application = new Application();
        ApplicationResult result = application.call(param);

        System.out.printf("requestId: %s, text: %s, finishReason: %s\n",
                result.getRequestId(), result.getOutput().getText(), result.getOutput().getFinishReason());

        if (result.getUsage() != null && result.getUsage().getModels() != null) {
            for (ApplicationUsage.ModelUsage usage : result.getUsage().getModels()) {
                System.out.printf("modelId: %s, inputTokens: %d, outputTokens: %d\n",
                        usage.getModelId(), usage.getInputTokens(), usage.getOutputTokens());
            }
        }
    }

    public static void main(String[] args) {
        try {
            appCall();
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}
