package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.CustomerCategory;
import com.newdon.entity.CustomerNature;
import com.newdon.mapper.CustomerCategoryMapper;
import com.newdon.mapper.CustomerNatureMapper;
import com.newdon.service.CustomerCategoryService;
import com.newdon.service.CustomerNatureService;
import org.springframework.stereotype.Service;

/**
 * @author: LeiGang
 * @create: 2019-01-05 15:22
 * @description:
 **/
@Service
public class CustomerNatureServiceImpl extends ServiceImpl<CustomerNatureMapper, CustomerNature> implements CustomerNatureService {
}
