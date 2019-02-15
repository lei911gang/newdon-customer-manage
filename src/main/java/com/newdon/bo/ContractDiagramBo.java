package com.newdon.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LeiGang
 * @create: 2019-02-15 15:45
 * @description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDiagramBo implements Serializable {
    private String businessPersonnel;
    private Double contractSum;
    private String clienteleRegion;
    private String newsFrom;

    @TableField(exist = false)
    private Long dateOfSignatureStart;
    @TableField(exist = false)
    private Long dateOfSignatureStop;
}
