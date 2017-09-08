package org.spring.springboot;

import java.util.Objects;

import javax.validation.ValidationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestResponse {
    /**
     * 响应码
     */
    public static final String STATUS_CODE="statusCode";
    /**
     * 响应数据
     */
    public static final String DATA="data";
    /**
     * 响应异常消息
     */
    public static final String ERROR_MESSAGE="errorMessage";
    
    public static void main(String[] args) {
        String url ="http://192.168.104.5:8038/loan-procedure/api/v1/loanApplication/confirm";
        String param="{\"totalRate\":0.29,\"handlingFee\":true,\"partnerCode\":\"\",\"capitalPayCode\":\"0002\",\"applicationId\":\"17090416405835203390512\",\"userId\":469314,\"welabProductCode\":\"WLD-Staff-Rong360-B\",\"challengeCode\":\"123456\"}";
        
//        url ="http://127.0.0.1:8080/api/req";
        JSONObject obj = JSONObject.parseObject(param);
        JSONObject rtn =postForObjectNew(url, obj);
        System.out.println("====rtn====="+rtn.toJSONString());
        postForObject(url, obj, JSONObject.class);
        
        testNull();
    }
    
    public static void testNull(){
        String responseBody="{\"exception\":\"com.wolaidai.loanprocedure.exception.BusinessException\",\"message\":\"userId为0|totalRate为null|handlingFee为null\",\"error\":\"Precondition Failed\",\"status\":\"412\"}";
        responseBody="df";
        JSONObject responseBodyJson = null;
        try {
            responseBodyJson = JSONObject.parseObject(responseBody);
        } catch (Exception e2) {
            System.out.println("{} postForObjectCaptureException Un JSONObject responseBody {} error"+ responseBody+e2);
        }
        if (Objects.nonNull(responseBodyJson)&& StringUtils.isNotEmpty(responseBodyJson.getString("message"))) {
            System.out.println(responseBodyJson.getString("message"));
        } else {
            System.out.println(responseBody);
        }
    }
    
    public static void postForObjectStatusCode(String url, Object request) {
        RestTemplate restTemplate = new RestTemplate();
        
        JSONObject response = null;
        try {
            response = restTemplate.postForObject(url, JSON.toJSON(request), JSONObject.class);
        } catch (Exception e) {
            if(e instanceof HttpClientErrorException  ){
                HttpClientErrorException e1=(HttpClientErrorException) e;
                
                System.out.println("getStatusCode======"+e1.getStatusCode());
                System.out.println("getStatusText======"+e1.getStatusText());
                System.out.println("getResponseBodyAsString======"+e1.getResponseBodyAsString());
                System.out.println("getResponseHeaders======"+e1.getResponseHeaders());
            }
            else{
                System.out.println("Exception======"+e.getMessage());
            }
        }
        System.out.println("response result======"+ response);
    }
    
    public static JSONObject postForObjectNew(String url, Object request) {
        RestTemplate restTemplate = new RestTemplate();
        
        System.out.println("{} execute method postForObject url [{}], request parameter [{}]."+ url+
                JSONObject.toJSON(request));

        JSONObject response = null;
        JSONObject rtn = new JSONObject();
        try {
            response = restTemplate.postForObject(url, JSON.toJSON(request), JSONObject.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException) {
                HttpClientErrorException e1 = (HttpClientErrorException) e;
                int statusCode = e1.getStatusCode().value();
                String responseBody = e1.getResponseBodyAsString();
                String responseHeader = e1.getResponseHeaders().toString();
                System.out.println("{} postForObjectCaptureException error statusCode={} responseBody={} responseHeaders={} "+
                        + statusCode+ responseBody+ responseHeader);
                /**
                 * 设置响应状态 STATUS_CODE
                 */
                rtn.put(STATUS_CODE, statusCode);
                JSONObject responseBodyJson = null;
                try {
                    responseBodyJson = JSONObject.parseObject(responseBody);
                } catch (Exception e2) {
                    System.out.println("{} postForObjectCaptureException Un JSONObject responseBody {} error {}"+responseBody+e2);
                }
                /**
                 * 设置错误消息 ERROR_MESSAGE
                 */
                if (Objects.nonNull(responseBodyJson)&& StringUtils.isNotEmpty(responseBodyJson.getString("message"))) {
                    rtn.put(ERROR_MESSAGE, responseBodyJson.getString("message"));
                } else {
                    rtn.put(ERROR_MESSAGE, responseBody);
                }
                return rtn;
            } else {
                System.out.println("{} postForObjectCaptureException Un analyze exception {}"+ e);
                throw new ValidationException("errors.model.welender.error");
            }
        }
        rtn.put(STATUS_CODE, HttpStatus.PRECONDITION_FAILED.value());
        
        System.out.println("{} postForObjectCaptureException response result {} "+ response);
        if (Objects.isNull(response)) {
            System.out.println("Failed to invoke {} server, caused by response is null");
            rtn.put(ERROR_MESSAGE, "response is null");
            return rtn;
        }

        Integer status = response.getInteger("status");
        rtn.put(STATUS_CODE, status);
        if (Objects.isNull(status) || status != HttpStatus.OK.value()) {
            System.out.println("Failed to invoke {} server, caused by code {}"+ status);
            rtn.put(ERROR_MESSAGE, "caused by code");
            return rtn;
        }
        JSONObject data = response.getJSONObject("data");
        if (Objects.isNull(data) || data.isEmpty()) {
            System.out.println("Failed to invoke {} server, caused by data is null");
            rtn.put(ERROR_MESSAGE, "data is null");
        }
        /**
         * 设置响应内容 DATA
         */
        else{
            rtn.put(DATA, data);
        }
        
        return rtn;
    }
    
//    public static void postForObjectEx(String url, Object request) {
//        RestTemplate restTemplate = new RestTemplate();
//        
//        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
//        
//        JSONObject response = null;
//        try {
//            response = restTemplate.postForObject(url, JSON.toJSON(request), JSONObject.class);
//        } catch (Exception e) {
//            Throwable exc=e.getCause();
//            if(exc instanceof CustomException ){
//                System.out.println("CustomException======"+((CustomException) exc).getProperties());
//            }
//            else{
//                System.out.println("Exception======"+e.getMessage());
//            }
//        }
//        System.out.println("response result======"+ response);
//    }
    
    public static <T> T postForObject(String url, Object request, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("{} execute method postForObject url [{}], request parameter [{}]."+
                url+ JSONObject.toJSON(request));

        JSONObject response = null;
        try {
            response = restTemplate.postForObject(url, JSON.toJSON(request), JSONObject.class);
        } catch (Exception e) {
            System.out.println("Failed to invoke {} server, caused by exception."+ e);
            throw new ValidationException("");
        }

        System.out.println("{} execute method postForObject response result {} "+ response);
        if (Objects.isNull(response)) {
            System.out.println("Failed to invoke {} server, caused by response is null");
            return null;
        }

        Integer status = response.getInteger("status");
        if (Objects.isNull(status) || status != 200) {
            System.out.println("Failed to invoke {} server, caused by code {}"+ status);
            return null;
        }

        JSONObject data = response.getJSONObject("data");
        if (Objects.isNull(data) || data.isEmpty()) {
            System.out.println("Failed to invoke {} server, caused by data is null");
            return null;
        }

        T object = JSONObject.parseObject(data.toJSONString(), responseType);
        if (Objects.isNull(object)) {
            System.out.println("Failed to invoke {} server, caused by object is empty.");
            return null;
        }
        return object;
    }
}
