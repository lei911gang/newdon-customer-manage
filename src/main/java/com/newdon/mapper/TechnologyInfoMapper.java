package com.newdon.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.newdon.bo.DeviceAndSystemBo;
import com.newdon.entity.DeviceInformationAndQuantity;
import com.newdon.entity.SystemLevelAndQuantity;
import com.newdon.entity.TechnologyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyInfoMapper extends BaseMapper<TechnologyInfo> {
    List<DeviceAndSystemBo> queryDevices(TechnologyInfo technologyInfo);

    List<SystemLevelAndQuantity> querySystems(TechnologyInfo technologyInfo);

    List<TechnologyInfo> queryList(TechnologyInfo technologyInfo);

    Integer getTotal(TechnologyInfo technologyInfo);
}