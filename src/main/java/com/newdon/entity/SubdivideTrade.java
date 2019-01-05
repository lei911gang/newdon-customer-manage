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
 * @description 细分行业
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("subdivide_trade")
public class SubdivideTrade implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String subdivideTradeName;
    private String remark;
}