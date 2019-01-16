package com.newdon.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @ClassName ContractCategory
 * @Auther: Dong
 * @Date: 2019/1/16 22:00
 * @Description: TODO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_category")
public class ContractCategory {
    private Integer id;
    private String name;//合同类别
    private String remark;
}
