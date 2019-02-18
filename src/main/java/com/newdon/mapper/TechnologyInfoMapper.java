package com.newdon.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.newdon.entity.DeviceInformationAndQuantity;
import com.newdon.entity.SystemLevelAndQuantity;
import com.newdon.entity.TechnologyInfo;

import java.util.List;

public interface TechnologyInfoMapper extends BaseMapper<TechnologyInfo> {
    List<DeviceInformationAndQuantity> queryDevices(TechnologyInfo technologyInfo);

    List<SystemLevelAndQuantity> querySystems(TechnologyInfo technologyInfo);
}