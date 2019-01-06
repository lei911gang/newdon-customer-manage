package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.github.wxiaoqi.merge.annonation.MergeField;
import com.newdon.service.TechnologyInfoService;
import com.newdon.service.impl.TechnologyInfoServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author LeiGang
 * @create 2019/1/5 16:25
 * @description 技术信息表
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("technology_info")
public class TechnologyInfo implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	private Long contractId;
	private String projectManager;
	private String technicist;
	private String basicEnvironment;
	//该字段存储为合同ID contractId
	@MergeField(feign = TechnologyInfoService.class,method = "getSystemLevelAndQuantity",isValueNeedMerge = true)
	private String systemLevelAndQuantity;
	//该字段存储为合同ID contractId
	@MergeField(feign = TechnologyInfoService.class,method = "getDeviceInformationAndQuantity",isValueNeedMerge = true)
	private String deviceInformationAndQuantity;
	private Long dateReleased;
	private String remark;
	private Integer status;
}