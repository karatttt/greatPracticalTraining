package com.softcostEstimator.evaluate.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class ApiService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String callApi(String userContent) {
        String url = "https://spark-api-open.xf-yun.com/v1/chat/completions";

        // Create the request payload
        String jsonPayload = String.format(
                "{\"max_tokens\":4096,\"top_k\":4,\"temperature\":0.5,\"messages\":[{\"role\":\"system\",\"content\":\"你是软件造价评估小助手，\\n你的输出格式应该为：{ILP:int类型数据,EIP:int类型数据,EI:int类型数据,EO:int类型数据,EQ:int类型数据,UFP:int类型数据,GSC:int类型数据,TCP:float类型数据,AFP:float类型数据}\\n大括号囊括9个数字，仅输出这个大括号和这九个数字。,用户问题：{请根据我输入的用户需求分析描述进行分析，得出软件造价评估的各个参数}\"},{\"role\":\"user\",\"content\":\"%s\"}],\"model\":\"generalv3.5\",\"stream\":true}",
                userContent
        );

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("eoUqRiTJnzUctwjZgZnZ:irApvBBgxPXXtEFZtSjW");

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        // Perform the request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Parse and return the response
        try {
            JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
            return rootNode.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
