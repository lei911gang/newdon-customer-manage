package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.BasicTechInfo;
import com.newdon.mapper.BasicTechInfoMapper;
import com.newdon.service.BasicTechInfoService;
import org.springframework.stereotype.Service;

@Service
public class BasicTechInfoServiceImpl extends ServiceImpl<BasicTechInfoMapper, BasicTechInfo> implements BasicTechInfoService {

}
