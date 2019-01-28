package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author LeiGang
 * @create 2019/1/5 16:24
 * @description 合同信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_info")
public class ContractInfo implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	private String contractId;
	private String contractCategory;
	private Long dateOfSignature;
	private String businessPersonnel;
	private Double contractSum;
	private String serviceContent;
	private String incrementOfStockNumber;
	private String newsFrom;
	private String cooperativeEvaluator;
	private String procurementMethod;
	private String clienteleName;
	private String remark;
	private Integer status;

	@TableField(exist = false)
    @JsonIgnore
	private ClienteleInfo clienteleInfo;
	@TableField(exist = false)
    @JsonIgnore
	private TechnologyInfo technologyInfo;
	@TableField(exist = false)
    @JsonIgnore
	private List<SystemLevelAndQuantity> systemLevelAndQuantities;
	@TableField(exist = false)
    @JsonIgnore
	private List<DeviceInformationAndQuantity> deviceInformationAndQuantities;

	@TableField(exist = false)
    @JsonIgnore
	private Long dateOfSignatureStart;
	@TableField(exist = false)
    @JsonIgnore
	private Long dateOfSignatureStop;
	@TableField(exist = false)
    @JsonIgnore
	private Double contractSumBegin;
	@TableField(exist = false)
    @JsonIgnore
	private Double contractSumEnd;
}