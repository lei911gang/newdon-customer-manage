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
	private Long contractId;
	private Long contractCategory;
	private Long dateOfSignature;
	private String businessPersonnel;
	private Long contractSum;
	private Integer serviceContent;
	private String incrementOfStockNumber;
	private String newsfrom;
	private String cooperativeEvaluator;
	private Long procurementMethod;
	private Long clienteleId;
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
	private Date dateOfSignatureStart;
	@TableField(exist = false)
	private Date dateOfSignatureEnd;
	@TableField(exist = false)
	private Double contractSumStart;
	@TableField(exist = false)
	private Double contractSumEnd;
}