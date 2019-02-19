package com.newdon.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LeiGang
 * @create 2019/1/5 16:25
 * @description 设备信息和数量
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAndSystemBo implements Serializable {
    private String deviceInfo;
    private Integer deviceQuantity;
    private String systemLevel;
    private Integer systemQuantity;
}