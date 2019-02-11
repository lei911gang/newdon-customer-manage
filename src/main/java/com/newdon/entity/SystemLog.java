package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LeiGang
 * @create 2019/1/5 16:21
 * @description 日志表
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_log")
public class SystemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
    private String operationType;
    /**
     * 操作对象
     */
    private String operationProject;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式
     */
    private String httpMethod;
    /**
     * ip
     */
    private String ip;
    /**
     * 请求参数
     */
    private String args;
}
