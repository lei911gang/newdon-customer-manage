package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.BasicEnvironment;
import com.newdon.entity.CustomerCategory;
import com.newdon.mapper.BasicEnvironmentMapper;
import com.newdon.mapper.CustomerCategoryMapper;
import com.newdon.service.BasicEnvironmentService;
import com.newdon.service.CustomerCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author: LeiGang
 * @create: 2019-01-05 15:22
 * @description:
 **/
@Service
public class CustomerCategoryServiceImpl extends ServiceImpl<CustomerCategoryMapper, CustomerCategory> implements CustomerCategoryService {
}
