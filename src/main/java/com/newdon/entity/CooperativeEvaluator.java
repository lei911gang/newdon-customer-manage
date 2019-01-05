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
 * @create 2019/1/5 16:24
 * @description 合作评测机构
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cooperative_evaluator")
public class CooperativeEvaluator implements Serializable {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	private String cooperativeEvaluatorName;
	private String remark;
}