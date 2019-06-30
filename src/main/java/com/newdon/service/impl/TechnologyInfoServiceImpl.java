package com.newdon.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.DeviceInformationAndQuantity;
import com.newdon.entity.SystemLevelAndQuantity;
import com.newdon.entity.TechnologyInfo;
import com.newdon.mapper.TechnologyInfoMapper;
import com.newdon.service.DeviceInformationAndQuantityService;
import com.newdon.service.SystemLevelAndQuantityService;
import com.newdon.service.TechnologyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LeiGang
 * @create: 2019-01-05 17:05
 * @description:
 **/
@Service
public class TechnologyInfoServiceImpl extends ServiceImpl<TechnologyInfoMapper, TechnologyInfo> implements TechnologyInfoService {
    @Autowired
    private SystemLevelAndQuantityService systemLevelAndQuantityService;
    @Autowired
    private DeviceInformationAndQuantityService deviceInformationAndQuantityService;
    @Override
    public Map<String, String> getSystemLevelAndQuantity(String contractId) {
        Map<String, String> map = new HashMap<>();
        //TODO 查询出该合同ID所有的系统级别以及系统数量，合并为字符串
        String[] split = contractId.split(",");
        for (int i = 0; i < split.length; i++) {
            SystemLevelAndQuantity s = new SystemLevelAndQuantity();
            s.setContractId(split[i]);
            List<SystemLevelAndQuantity> list = this.systemLevelAndQuantityService.selectList(new EntityWrapper<>(s));
            if (null != list && list.size() > 0) {
                StringBuilder str = new StringBuilder();
                //合并为字符串
                for (SystemLevelAndQuantity sl : list) {
                    String systemLevel = sl.getSystemLevel();
                    Integer systemQuantity = sl.getSystemQuantity();
                    str.append(systemLevel).append("=").append(systemQuantity).append("个").append(",");
                }
                map.put(split[i], str.toString());
            } else {
                map.put(split[i], "无");
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getDeviceInformationAndQuantity(String contractId) {
        //TODO 查询出该合同ID所有的设备信息和数量，合并为字符串
        Map<String, String> map = new HashMap<>();
        String[] split = contractId.split(",");
        for (int i = 0; i < split.length; i++) {
            DeviceInformationAndQuantity d = new DeviceInformationAndQuantity();
            d.setContractId(split[i]);
            List<DeviceInformationAndQuantity> list = this.deviceInformationAndQuantityService.selectList(new EntityWrapper<>(d));
            if (null != list && list.size() > 0) {
                StringBuilder str = new StringBuilder();
                //合并为字符串
                for (DeviceInformationAndQuantity di : list) {
                    String deviceInfo = di.getDeviceInfo();
                    Integer deviceQuantity = di.getDeviceQuantity();
                    str.append(deviceInfo).append("=").append(deviceQuantity).append("套").append(",");
                }
                map.put(split[i], str.toString());
            } else {
                map.put(split[i], "无");
            }
        }
        return map;
    }
}
