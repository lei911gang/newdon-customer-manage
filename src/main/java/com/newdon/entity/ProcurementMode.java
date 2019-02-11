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
 * @description 采购方式
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("procurement_mode")
public class ProcurementMode implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String procurementMode;
    private String remark;
}