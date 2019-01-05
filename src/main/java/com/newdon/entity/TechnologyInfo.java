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
	private Integer contractId;
	private String projectManager;
	private String technicist;
	private String basicEnvironment;
	private String systemLevelAndQuantity;
	private String deviceInformationAndQuantity;
	private Long dateReleased;
	private String remark;
	private Integer status;
}