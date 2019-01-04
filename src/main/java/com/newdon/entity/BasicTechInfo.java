package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.sql.Date;

/**
 * 技术基础信息
 */
@Data
@TableName("basic_tech_info")
public class BasicTechInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 合同编号
     */
    private String contractNumber;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     *项目经理--查询
     */
    private String projectManager;
    /**
     * 技术人员--查询
     */
    private String artisan;
    /**
     * 基础环境
     */
    private String basicEnv;
    /**
     * 系统级别--查询
     * 一级，二级，三级，四级，五级
     */
    private String systemLevel;
    /**
     * 系统数量
     */
    private int systemCount;
    /**
     * 设备信息
     */
    private String deviceInfo;
    /**
     * 硬件数量
     */
    private int hardwareCount;
    /**
     * 交付日期--时间段查询
     */
    private Date deliverDate;
    /**
     * 有效状态
     * 1有效，0无效
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    @TableField(exist = false)
    private Date deliverStart;
    @TableField(exist = false)
    private Date deliverEnd;
}
