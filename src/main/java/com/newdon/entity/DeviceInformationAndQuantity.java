package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("device_information_and_quantity")
/**
 * @author LeiGang
 * @create 2019/1/5 16:25
 * @description 设备信息和数量
 **/
public class DeviceInformationAndQuantity implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	private Long contractId;
	private String deviceInfo;
	private Integer deviceQuantity;
	private String remark;
}