package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * 客户基础信息
 */
@Data
@TableName("newdon_customer")
public class NewdonCustomer {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 客户名称--查询
     */
    private String customerName;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 客户类别--查询
     * 终端客户，分包
     */
    private String customerCategory;
    /**
     * 客户地区--查询
     */
    private String customerArea;
    /**
     * 客户行业--查询
     */
    private String customerIndustry;
    /**
     * 细分行业--查询
     */
    private String minceIndustry;
    /**
     * 行业性质--查询
     */
    private String industryNature;
    /**
     * 有效状态
     *1有效，0无效
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
}

