package com.newdon.service;


import com.baomidou.mybatisplus.service.IService;
import com.newdon.entity.TechnologyInfo;

import java.util.Map;

/**
 * @version 1.0
 * @ClassName TechnologyInfoService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/
public interface TechnologyInfoService extends IService<TechnologyInfo> {
    Map<String, String> getSystemLevelAndQuantity(String contractId);
    Map<String, String> getDeviceInformationAndQuantity(String contractId);
}