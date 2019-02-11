package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.CustomerCategory;
import com.newdon.entity.DeviceCategory;
import com.newdon.mapper.CustomerCategoryMapper;
import com.newdon.mapper.DeviceCategoryMapper;
import com.newdon.service.CustomerCategoryService;
import com.newdon.service.DeviceCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author: LeiGang
 * @create: 2019-01-05 15:22
 * @description:
 **/
@Service
public class DeviceCategoryServiceImpl extends ServiceImpl<DeviceCategoryMapper, DeviceCategory> implements DeviceCategoryService {
}
