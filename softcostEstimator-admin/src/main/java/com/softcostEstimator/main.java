//package com.softcostEstimator;
//
//import com.alibaba.dashscope.app.Application;
//import com.alibaba.dashscope.app.ApplicationParam;
//import com.alibaba.dashscope.app.ApplicationResult;
//import com.alibaba.dashscope.exception.ApiException;
//import com.alibaba.dashscope.exception.InputRequiredException;
//import com.alibaba.dashscope.exception.NoApiKeyException;
//import com.softcostEstimator.common.ResultUtils;
//import com.softcostEstimator.evaluate.domain.Productbudget;
//import com.softcostEstimator.evaluate.service.IProductbudgetService;
//import com.softcostEstimator.evaluate.service.impl.ProductbudgetServiceImpl;
//import io.reactivex.Flowable;
//
//public class main {
//    public static void main(String[] args) {
//        Productbudget productbudget = new Productbudget();
//        productbudget.setSL(1.5);
//        productbudget.setSF(2.5);
//        productbudget.setRSK(3.5);
//        productbudget.setPDR(4.5);
//        productbudget.setF(5.5);
//        productbudget.setProductID(6L);
//        productbudget.setESDC(.5);
//        productbudget.setDNC(8.5);
//        productbudget.setAT(9.5);
//        productbudget.setQR(10.5);
//        productbudget.setBD(11.5);
//        productbudget.setDT(12.5);
//        productbudget.setSDC(1.5);
//        IProductbudgetService productbudgetService = new ProductbudgetServiceImpl();
//        String json = productbudgetService.getJson(productbudget);
//        StringBuilder output = new StringBuilder();
//        ApplicationParam param = ApplicationParam.builder()
//                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
//                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
//                .appId("18de7db29ca44e629d156837394a7a3b")
//                .prompt(json)
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
//            e.printStackTrace();
//        }
//        System.out.print(output);
//
//
//
//    }
//
//
//
//
//}
