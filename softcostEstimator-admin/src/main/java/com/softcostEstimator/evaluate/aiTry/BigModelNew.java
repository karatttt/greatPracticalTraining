package com.softcostEstimator.evaluate.aiTry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.web.bind.annotation.PostMapping;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class BigModelNew extends WebSocketListener {

    public static final String hostUrl = "https://spark-api.xf-yun.com/v3.5/chat";
    public static final String domain = "generalv3.5";
    public static final String appid = "4af83306";
    public static final String apiSecret = "MzNmNDBiY2YyZWNiNDVlMzExODZkNGE1";
    public static final String apiKey = "afba82bf9ec6d65e11215f3a382df958";


    public static List<RoleContent> historyList=new ArrayList<>(); // 对话历史存储集合

    public static String totalAnswer=""; // 大模型的答案汇总

    // 环境治理的重要性  环保  人口老龄化  我爱我的祖国
    public static  String NewQuestion = "";

    public static final Gson gson = new Gson();

    // 个性化参数
    private String userId;
    private Boolean wsCloseFlag;
    public static double[] values1= {1,2,3};

    private List<Text> textList;
    private static Boolean totalFlag=true; // 控制提示用户是否输入
    // 构造函数
    public BigModelNew(String userId, Boolean wsCloseFlag) {
        this.userId = userId;
        this.wsCloseFlag = wsCloseFlag;
    }

    // 调用函数
    public String getFrontText(String text) throws Exception {
        // 个性化参数入口，如果是并发使用，可以在这里模拟
        if(totalFlag){
            String ques;
            String getText;
            getText = text;
            ques="你是软件造价评估系统助手请严格按照以下格式输出：" +
                    "你的输出格式应该为：{ILP:int类型数据,EIP:int类型数据,EI:int类型数据,EO:int类型数据,EQ:int类型数据,UFP:int类型数据,GSC:int类型数据,TCP:float类型数据,AFP:float类型数据}" +
                    "大括号囊括9个数字，仅输出这个大括号和这九个数字。"+"ILP (Integrate Labor Points): 集成劳动力点数，可能指的是与软件集成工作相关的人力工作量。" +
                    "EIP (External Interface Points): 外部接口点数，涉及软件与其他系统或服务接口的功能点数。" +
                    "EI (Equipment Interface Points): 设备接口点数，可能指的是与硬件设备接口相关的功能点数。" +
                    "EO (External Output Points): 外部输出点数，涉及软件生成的外部报告或输出的功能点数。" +
                    "EQ (Equipment Points): 设备点数，可能指的是与特定硬件设备相关的功能点数。" +
                    "UFP (User Function Points): 用户功能点数，指的是用户特定需求的功能点数。" +
                    "GSC (General System Characteristics): 通用系统特征点数，指的是影响整个系统复杂性的14个因素（如数据通信、分布式数据处理、性能要求等）的功能点数。" +
                    "TCP (Total Cost of Function Points): 功能点数总成本，可能指的是基于功能点数估算的软件开发总成本。" +
                    "AFP (Additional Function Points): 功能点总数"+",无论任何情况请严格按照格式输出,任何数值不能为0";
            Scanner scanner=new Scanner(getText+ques);
            totalFlag=false;
            NewQuestion=scanner.nextLine();
            // 构建鉴权url
            String authUrl = getAuthUrl(hostUrl, apiKey, apiSecret);
            OkHttpClient client = new OkHttpClient.Builder().build();
            String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
            Request request = new Request.Builder().url(url).build();
            for (int i = 0; i < 1; i++) {
                WebSocket webSocket = client.newWebSocket(request, new BigModelNew(i + "",
                        false));
            }
        }else{
            Thread.sleep(200);
        }
//        while(true){
//            Thread.sleep(200);
//            if (wsCloseFlag) {
//                break;
//            }
//        }
        return totalAnswer;
    }


    public static double[] parseValues(String input) {
        if (input == null || input.length() < 2 || input.charAt(0) != '{' || input.charAt(input.length() - 1) != '}') {
            throw new IllegalArgumentException("Input string must be in the format {key:value,...}");
        }

        // Remove the enclosing braces and split by commas
        String content = input.substring(1, input.length() - 1);
        String[] pairs = content.split(",");

        List<Double> values = new ArrayList<>();
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Invalid key-value pair: " + pair);
            }

            // Parse the value and add to the list
            try {
                double value = Double.parseDouble(keyValue[1].trim());
                values.add(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid value in pair: " + pair, e);
            }
        }

        // Convert the list of values to an array
        double[] result = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            result[i] = values.get(i);
        }

        return result;
    }

    public static boolean canAddHistory(){  // 由于历史记录最大上线1.2W左右，需要判断是能能加入历史
        int history_length=0;
        for(RoleContent temp:historyList){
            history_length=history_length+temp.content.length();
        }
        if(history_length>12000){
            historyList.remove(0);
            historyList.remove(1);
            historyList.remove(2);
            historyList.remove(3);
            historyList.remove(4);
            return false;
        }else{
            return true;
        }
    }

    // 线程来发送音频与参数
    class MyThread extends Thread {
        private WebSocket webSocket;

        public MyThread(WebSocket webSocket) {
            this.webSocket = webSocket;
        }

        public void run() {
            try {
                JSONObject requestJson=new JSONObject();

                JSONObject header=new JSONObject();  // header参数
                header.put("app_id",appid);
                header.put("uid",UUID.randomUUID().toString().substring(0, 10));

                JSONObject parameter=new JSONObject(); // parameter参数
                JSONObject chat=new JSONObject();
                chat.put("domain",domain);
                chat.put("temperature",0.5);
                chat.put("max_tokens",4096);
                parameter.put("chat",chat);

                JSONObject payload=new JSONObject(); // payload参数
                JSONObject message=new JSONObject();
                JSONArray text=new JSONArray();

                // 历史问题获取
                if(historyList.size()>0){
                    for(RoleContent tempRoleContent:historyList){
                        text.add(JSON.toJSON(tempRoleContent));
                    }
                }

                // 最新问题
                RoleContent roleContent=new RoleContent();
                roleContent.role="user";
                roleContent.content=NewQuestion;
                text.add(JSON.toJSON(roleContent));
                historyList.add(roleContent);


                message.put("text",text);
                payload.put("message",message);


                requestJson.put("header",header);
                requestJson.put("parameter",parameter);
                requestJson.put("payload",payload);
                System.err.println(requestJson); // 可以打印看每次的传参明细
                webSocket.send(requestJson.toString());
                // 等待服务端返回完毕后关闭
                while (true) {
                    // System.err.println(wsCloseFlag + "---");
                    Thread.sleep(200);
                    if (wsCloseFlag) {
                        break;
                    }
                }
                webSocket.close(1000, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        MyThread myThread = new MyThread(webSocket);
        myThread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        // System.out.println(userId + "用来区分那个用户的结果" + text);
        JsonParse myJsonParse = gson.fromJson(text, JsonParse.class);
        if (myJsonParse.header.code != 0) {
            System.out.println("发生错误，错误码为：" + myJsonParse.header.code);
            System.out.println("本次请求的sid为：" + myJsonParse.header.sid);
            webSocket.close(1000, "");
        }
        textList = myJsonParse.payload.choices.text;
        for (Text temp : textList) {
            System.out.print(temp.content);
            totalAnswer = totalAnswer + temp.content;
        }

//        double[] values1 = null;
        if (myJsonParse.header.status == 2) {
            // 可以关闭连接，释放资源
            System.out.println();
            if (canAddHistory()) {
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer);
                historyList.add(roleContent);
            } else {
                historyList.remove(0);
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer);
                historyList.add(roleContent);
            }
            System.out.println(totalAnswer);
            BigModelNew.values1 = parseValues(totalAnswer);
            for (int i = 0; i < BigModelNew.values1.length; i++) {
                System.out.println(BigModelNew.values1[i]);
            }
            totalAnswer="";
            wsCloseFlag = true;
            totalFlag = true;
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        try {
            if (null != response) {
                int code = response.code();
                System.out.println("onFailure code:" + code);
                System.out.println("onFailure body:" + response.body().string());
                if (101 != code) {
                    System.out.println("connection failed");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    // 鉴权方法
    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = new URL(hostUrl);
        // 时间
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        // 拼接
        String preStr = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        // System.err.println(preStr);
        // SHA256加密
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);

        byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
        // Base64加密
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        // System.err.println(sha);
        // 拼接
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        // 拼接地址
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder().//
                addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8))).//
                addQueryParameter("date", date).//
                addQueryParameter("host", url.getHost()).//
                build();

        // System.err.println(httpUrl.toString());
        return httpUrl.toString();
    }

    //返回的json结果拆解
    class JsonParse {
        Header header;
        Payload payload;
    }

    class Header {
        int code;
        int status;
        String sid;
    }

    class Payload {
        Choices choices;
    }

    class Choices {
        List<Text> text;
    }

    public class Text {
        String role;
        public String content;
    }
    class RoleContent{
        String role;
        String content;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            role="你是一个软件造价评估师,请根据我的需求根据特定项目的需求进行功能点数的计算";
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            content="你的输出格式应该为：{ILP:int类型数据,EIP:int类型数据,EI:int类型数据,EO:int类型数据,EQ:int类型数据,UFP:int类型数据,GSC:int类型数据,TCP:float类型数据,AFP:float类型数据}\n" +
                    "大括号囊括9个数字，仅输出这个大括号和这九个数字。";
            this.content = content;
        }
    }
}