package com.softcostEstimator.evaluate.AI;

import com.alibaba.dashscope.app.*;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import io.reactivex.Flowable;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageUtil {

    private static final BlockingQueue<Response> messageResponseQueue = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Response> messageResponseQueue2 = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Response> messageResponseQueue3 = new LinkedBlockingQueue<>();
    public static BlockingQueue<Response> getQueue(){
        return messageResponseQueue;
    }
    public static BlockingQueue<Response> getQueue2(){
        return messageResponseQueue2;
    }
    public static BlockingQueue<Response> getQueue3() {
        return messageResponseQueue3;
    }
    public static void streamCall(String userMessage) throws NoApiKeyException, InputRequiredException {
        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
                .appId("d65798ea79404664af60856f00b7b6d0")
                .prompt(userMessage)
                .incrementalOutput(true)
                .build();

        Application application = new Application();
        Flowable<ApplicationResult> result = application.streamCall(param);

        result.blockingForEach(data -> {
            boolean isEnd = Objects.equals(data.getOutput().getFinishReason(), "stop");
            messageResponseQueue.offer(new Response(isEnd, data.getOutput().getText()));
            System.out.printf("requestId: %s, text: %s, finishReason: %s\n",
                    data.getRequestId(), data.getOutput().getText(), data.getOutput().getFinishReason());
        });
    }

    public static void streamCall2(String json) throws NoApiKeyException, InputRequiredException {
        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
                .appId("18de7db29ca44e629d156837394a7a3b")
                .prompt(json)
                .incrementalOutput(true)
                .build();

        Application application = new Application();
        Flowable<ApplicationResult> result = application.streamCall(param);

        result.blockingForEach(data -> {
            boolean isEnd = Objects.equals(data.getOutput().getFinishReason(), "stop");
            messageResponseQueue2.offer(new Response(isEnd, data.getOutput().getText()));
//            System.out.printf("requestId: %s, text: %s, finishReason: %s\n",
//                    data.getRequestId(), data.getOutput().getText(), data.getOutput().getFinishReason());
        });
    }

    public static void questionInit(String json) throws NoApiKeyException, InputRequiredException {
        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
                .appId("d65798ea79404664af60856f00b7b6d0")
                .prompt(json)
                .incrementalOutput(true)
                .build();

        Application application = new Application();
        Flowable<ApplicationResult> result = application.streamCall(param);


//        result.blockingForEach(data -> {
//            boolean isEnd = Objects.equals(data.getOutput().getFinishReason(), "stop");
//            messageResponseQueue.offer(new Response(isEnd, data.getOutput().getText()));
//            System.out.printf("questionInit::requestId: %s, text: %s, finishReason: %s\n",
//                    data.getRequestId(), data.getOutput().getText(), data.getOutput().getFinishReason());
//        });
    }


    public static void streamCall3(String content) throws NoApiKeyException, InputRequiredException {
        ApplicationParam param = ApplicationParam.builder()
                // 若没有配置环境变量，可用百炼API Key将下行替换为：api_key="sk-xxx"。但不建议在生产环境中直接将API Key硬编码到代码中，以减少API Key泄露风险。
                .apiKey("sk-050b42af20b4467e97b1510365a4eb0c")
                .appId("8a62e12cf9c24f1697f3110a6dcedc80")
                .prompt(content)
                .incrementalOutput(true)
                .build();

        Application application = new Application();
        Flowable<ApplicationResult> result = application.streamCall(param);

        result.blockingForEach(data -> {
            boolean isEnd = Objects.equals(data.getOutput().getFinishReason(), "stop");
            messageResponseQueue3.offer(new Response(isEnd, data.getOutput().getText()));
            System.out.printf("requestId: %s, text: %s, finishReason: %s\n",
                    data.getRequestId(), data.getOutput().getText(), data.getOutput().getFinishReason());
        });
    }
}