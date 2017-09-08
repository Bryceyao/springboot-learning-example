//package com.wolaidai.ws.jrocket2.thirdparty.context.impl;
//
//import java.io.ByteArrayInputStream;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.core.MediaType;
//
//import org.glassfish.jersey.message.internal.MediaTypes;
//import org.glassfish.jersey.server.ContainerRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.wolaidai.ws.jrocket2.shrb.client.ShrbClient;
//import com.wolaidai.ws.jrocket2.thirdparty.context.CheckSignWithMD5;
//
///**
// * 华瑞银行验证签名
// * 
// * Created by Bryce Yao<bryce.yao@wolaidai.com> on 2017-07-15.
// */
//public class CheckSignWithMD5ShrbImpl implements CheckSignWithMD5{
//    private static final Logger log = LoggerFactory.getLogger(CheckSignWithMD5ShrbImpl.class);
//
//    private ShrbClient shrbClient;
//    
//    @Override
//    public void init() {
//        this.shrbClient=new ShrbClient();
//    }
//
//    /**
//     * 华瑞银行 签名方式 reqData+sequenceID+appSecret 组成签名字符串
//     */
//    @Override
//    public boolean verifySign(ContainerRequestContext requestContext) {
//        ContainerRequest request = (ContainerRequest) requestContext.getRequest();
////        String entityStr=request.readEntity(String.class);
//        String entityStr = getJsonString(request);
//        try {
//            log.info("CheckSignWithMD5ShrbImpl verifySign entityStr={}",entityStr);
//            return shrbClient.checkSign(entityStr,"reqData");
//        } catch (Exception e) {
//            log.warn("CheckSignWithMD5ShrbImpl verifySign error Entity={}",entityStr,e);
//        }finally{
//            request.setEntityStream(new ByteArrayInputStream(entityStr.getBytes()));
//        }
//        return false;
//    }
//    
//    
//    
//    private String getJsonString(ContainerRequest req)
//    {
//        if (MediaTypes.typeEqual(MediaType.APPLICATION_JSON_TYPE, req.getMediaType()))
//        {
//            req.bufferEntity();
//            String json = req.readEntity(String.class);
//            return json;
//        }
//        else
//        {
//            return "";
//        }
//    }
//}
