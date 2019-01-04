package com.newdon.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuzhihai
 * @since 2018-08-15
 */
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 操作时间
     */
    private Long time;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作类型
     */
    @TableId("operation_type")
    private String operationType;
    /**
     * 操作对象
     */
    @TableId("operation_project")
    private String operationProject;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式
     */
    @TableId("http_method")
    private String httpMethod;
    /**
     * ip
     */
    private String ip;
    /**
     * 请求参数
     */
    private String args;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationProject() {
        return operationProject;
    }

    public void setOperationProject(String operationProject) {
        this.operationProject = operationProject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysLog{" +
        ", id=" + id +
        ", time=" + time +
        ", operator=" + operator +
        ", operationType=" + operationType +
        ", operationProject=" + operationProject +
        ", url=" + url +
        ", httpMethod=" + httpMethod +
        ", ip=" + ip +
        ", args=" + args +
        "}";
    }
}
