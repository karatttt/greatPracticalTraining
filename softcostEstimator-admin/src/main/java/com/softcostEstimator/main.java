package com.softcostEstimator;

import com.alibaba.dashscope.app.Application;
import com.alibaba.dashscope.app.ApplicationParam;
import com.alibaba.dashscope.app.ApplicationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.softcostEstimator.common.ResultUtils;
import com.softcostEstimator.evaluate.domain.Productbudget;
import com.softcostEstimator.evaluate.service.IProductbudgetService;
import com.softcostEstimator.evaluate.service.impl.ProductbudgetServiceImpl;
import io.reactivex.Flowable;
import javafx.scene.text.Text;

public class main {
    public static void main(String[] args) {
        Productbudget productbudget = new Productbudget();
        productbudget.setSL(1L);
        productbudget.setSF(2L);
        productbudget.setRSK(3L);
        productbudget.setPDR(4L);
        productbudget.setF(5L);
        productbudget.setProductID(6L);
        productbudget.setESDC(7L);
        productbudget.setDNC(8L);
        productbudget.setAT(9L);
        productbudget.setQR(10L);
        productbudget.setBD(11L);
        productbudget.setDT(12L);
        productbudget.setSDC(13L);
        IProductbudgetService productbudgetService = new ProductbudgetServiceImpl();
        String json = productbudgetService.getJson(productbudget);
        StringBuilder output = new StringBuilder();
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
                output.append(data.getOutput().getText());

            });
        }catch (ApiException | NoApiKeyException | InputRequiredException e){
            e.printStackTrace();
        }
        System.out.print(output);



    }




}
