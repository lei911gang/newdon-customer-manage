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
 * @create 2019/1/5 15:52
 * @description 客户信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("clientele_info")
public class ClienteleInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String clienteleName;
    private String linkman;
    private String contact;
    private String clienteleCategory;
    private String clienteleRegion;
    private String clienteleTrade;
    private String subdivideTrade;
    private String tradeProperty;
    private String remark;
    private Integer status;
}