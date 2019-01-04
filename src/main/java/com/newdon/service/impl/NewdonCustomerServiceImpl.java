package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.NewdonCustomer;
import com.newdon.mapper.NewdonCustomerMapper;
import com.newdon.service.NewdonCustomerService;
import org.springframework.stereotype.Service;

@Service
public class NewdonCustomerServiceImpl extends ServiceImpl<NewdonCustomerMapper, NewdonCustomer> implements NewdonCustomerService {

}
