package org.spring.springboot.aspectJ;

import java.io.Serializable;

/**
 * 发送短信响应参数.
 * 
 * Created by Bryce Yao<bryce.yao@wolaidai.com> on 2017-11-20.
 */
public class SmsResponse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1915880411027344890L;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回码说明
     */
    private String desc;
    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();
    /**
     * 失败的短信号码
     * 返回码非0时才有值。
     * 多个号码间用英文逗号分隔。
     */
    private String failMobiles;
    /**
     * 业务系统产生的批次号,流水号。不超过30个字符
     */
    private String batchId;
    /**
     * 响应数据
     */
    private Object data;
    
    public SmsResponse(){
        
    }
    
    public void init(CodeType codeType){
        if(codeType!=null){
            this.code=codeType.getValue();
            this.desc=codeType.getDesc();
        }
    }
    
    public void init(CodeType codeType,String batchId){
        if(codeType!=null){
            this.code=codeType.getValue();
            this.desc=codeType.getDesc();
        }
        if(batchId!=null){
            this.batchId=batchId;
        }
    }
    
    /**
     * 响应码枚举类.
     * 
     * Created by Bryce Yao<bryce.yao@wolaidai.com> on 2017-11-02.
     */
    public enum CodeType {
        /**
         * 成功
         */
        SUCCESS(0,"请求成功"),
        /**
         * 失败
         */
        FAIL(9,"失败，其他原因"),
        /**
         * 拒绝 缺少orgId参数
         */
        REJECT_ORG_ID(11011,"拒绝，缺少orgId参数"),
        /**
         * 部分成功，常见于短信群发
         */
        THIRD_PARTIALLY(10010001,"部分成功，常见于短信群发"),
        /**
         * 异常，短信服务内部错误
         */
        SYS_INTERNAL(10010011,"异常，短信服务内部错误"),
        /**
         * 失败，提交供应商超时
         */
        THIRD_ERROR(10010031,"失败，提交供应商异常"),
        /**
         * 拒绝，参数不符合短信服务接口规范
         */
        REJECT_PARAM(10010101,"拒绝，参数不符合短信服务接口规范"),
        /**
         * 拒绝，单位时间内超出发送次数
         */
        REJECT_RATE_ERROR(10010102,"拒绝，超出单位时间最大发送次数"),
        /**
         * 拒绝，黑名单限制
         */
        REJECT_BLACKLIST(10010103,"拒绝，命中黑名单"),
        /**
         * 拒绝，群发短信超出数量限制
         */
        REJECT_GROUP_NUM(10010104,"拒绝，群发短信超出数量限制"),
        /**
         * 拒绝，未定义的来源渠道
         */
        REJECT_SOURCE_CHANNEL(10010105,"拒绝，参数sourceChannel无效"),
        /**
         * 拒绝，参数tags长度超过32位
         */
        REJECT_TAGS(10010106,"拒绝，参数appTags长度不能超过32位"),
        /**
         * 拒绝，参数content长度超过500位
         */
        REJECT_CONTENT(10010107,"拒绝，参数content不能为空，长度不能超过500个字符"),
        /**
         * 拒绝，手机号码不能为空
         */
        REJECT_MOBILE(10010108,"拒绝，参数mobile不能为空、或者超出15未长度限制"),
        /**
         * 拒绝，参数templateName不能为空
         */
        REJECT_TEMPLATE(10010109,"拒绝，参数templateName不能为空"),
        /**
         * 拒绝，参数replaceField不能为空
         */
        REJECT_REPLACE_FIELD(10010110,"拒绝，参数replaceField不能为空"),
        /**
         * 拒绝，参数templateName无效，或者参数replaceField非标准json格式
         */
        REJECT_TEMPLATE_CONTENT(10010111,"拒绝，参数templateName无效，或者参数replaceField非标准json格式"),
        /**
         * 拒绝，群发彩信超出数量限制
         */
        REJECT_MMS_GROUP_NUM(10010112,"拒绝，群发彩信超出数量限制"),
        /**
         * 拒绝，参数messageChannel无效
         */
        REJECT_MESSAGE_CHANNEL(10010113,"拒绝，参数messageChannel无效");
        
        
        private Integer value;
        
        private String desc;
        
        private CodeType(Integer value,String desc) {
            this.value = value;
            this.desc = desc;
        }
        
        public Integer getValue() {
            return value;
        }
        
        public String getDesc() {
            return desc;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFailMobiles() {
        return failMobiles;
    }

    public void setFailMobiles(String failMobiles) {
        this.failMobiles = failMobiles;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
