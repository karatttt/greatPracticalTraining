package com.softcostEstimator.evaluate.AI;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.softcostEstimator.common.core.domain.R;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
public class LongPollingController{

    @PostMapping("/sendMsg")
    public void receiveMes(@RequestBody MsgReq msgReq) throws NoApiKeyException, InputRequiredException {
        MessageUtil.streamCall(msgReq.content);
    }

    // 监听长轮询请求，直到队列中有消息
    @GetMapping("/chat")
    public Response handleLongPolling() throws InterruptedException, NoApiKeyException, InputRequiredException {

        // 设置超时时间，例如5分钟
        Response message = MessageUtil.getQueue().poll();  // 如果有消息，直接返回；如果没有，则阻塞直到有消息
        if (message == null) {
            // 如果没有消息，阻塞5分钟，模拟超时机制
            message = MessageUtil.getQueue().take();  // 这里会阻塞直到有消息或超时
        }
        return message;  // 返回消息
    }

    // 供其他地方发送消息给客户端（例如在数据库变动时触发）
//    public static void sendMessage(String message) {
//        messageResponseQueue.offer(message);  // 将消息放入队列，等待客户端接收
//    }
//    public void fetchData(){
//        messageResponseQueue.offer(new Response(false, "dsdd"));
//        messageResponseQueue.offer(new Response(false, "dsdd"));
//        messageResponseQueue.offer(new Response(false, "dsdd"));
//        messageResponseQueue.offer(new Response(true, "dsddds"));
//    }

}

