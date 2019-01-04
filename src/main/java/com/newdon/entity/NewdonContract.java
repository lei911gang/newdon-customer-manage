package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.sql.Date;

/**
 * 合同基础信息
 */
@Data
@TableName("newdon_contract")
public class NewdonContract {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 合同编号--查询
     */
    private String contractNumber;
    /**
     * 合同类别--查询
     */
    private String contractCategory;
    /**
     * 签署日期--时间段查询
     */
    private Date signDate;
    /**
     * 业务人员--查询
     */
    private String businessMan;
    /**
     * 合同金额--区间查询，统计汇总
     */
    private Double contractMoney;
    /**
     * 服务内容
     */
    private String serviceContent;
    /**
     * 存量&增量--查询
     * 存量，增量
     */
    private String increaseOrStock;
    /**
     * 信息来源--查询
     * 自跑，互联网，渠道
     */
    private String informationSource;
    /**
     * 合作测评机构--查询
     * 国家网络与信息系统安全产品质量监督检验中心，上海市信息安全测评认证中心，上海交通大学(信息安全服务技术研究实验室)，上海计算机软件技术开发中心，上海市网络技术综合应用研究所
     */
    private String evaluationOrganization;
    /**
     * 采购方式
     * 公开招标，竞争性谈判，上会审核，单一来源，直签
     */
    private String procurementMode;
    /**
     * 客户名称--查询
     */
    private String customerName;
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
    private Date signStart;
    @TableField(exist = false)
    private Date signEnd;
    @TableField(exist = false)
    private Double moneyStart;
    @TableField(exist = false)
    private Double moneyEnd;
}
