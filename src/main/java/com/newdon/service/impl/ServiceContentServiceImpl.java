package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.Region;
import com.newdon.entity.ServiceContent;
import com.newdon.mapper.RegionMapper;
import com.newdon.mapper.ServiceContentMapper;
import com.newdon.service.RegionService;
import com.newdon.service.ServiceContentService;
import org.springframework.stereotype.Service;

/**
 * @author: LeiGang
 * @create: 2019-01-05 17:02
 * @description:
 **/
@Service
public class ServiceContentServiceImpl extends ServiceImpl<ServiceContentMapper, ServiceContent> implements ServiceContentService {
}
