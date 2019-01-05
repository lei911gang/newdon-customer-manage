package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.ContractInfo;
import com.newdon.mapper.ContractInfoMapper;
import com.newdon.service.ContractInfoService;
import org.springframework.stereotype.Service;

/**
 * @author: LeiGang
 * @create: 2019-01-05 16:55
 * @description:
 **/
@Service
public class ContractInfoServiceImpl extends ServiceImpl<ContractInfoMapper, ContractInfo> implements ContractInfoService {
}
