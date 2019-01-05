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
 * @description 系统级别和数量
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_level_and_quantity")
public class SystemLevelAndQuantity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long contractId;
    private String systemLevel;
    private Integer systemQuantity;
    private String remark;
}