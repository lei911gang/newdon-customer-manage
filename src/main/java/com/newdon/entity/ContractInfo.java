package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
	private Integer serviceContent;
	private String incrementOfStockNumber;
	private String newsFrom;
	private String cooperativeEvaluator;
	private Long procurementMethod;
	private String clienteleName;
	private String remark;
	private Integer status;

	@TableField(exist = false)
	private ClienteleInfo clienteleInfo;
	@TableField(exist = false)
	private TechnologyInfo technologyInfo;
	@TableField(exist = false)
	private List<SystemLevelAndQuantity> systemLevelAndQuantities;
	@TableField(exist = false)
	private List<DeviceInformationAndQuantity> deviceInformationAndQuantities;

	@TableField(exist = false)
	private Long dateOfSignatureStart;
	@TableField(exist = false)
	private Long dateOfSignatureStop;
	@TableField(exist = false)
	private Double contractSumBegin;
	@TableField(exist = false)
	private Double contractSumEnd;
}