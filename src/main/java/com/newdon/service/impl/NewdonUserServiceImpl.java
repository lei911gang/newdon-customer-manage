package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.NewdonUser;
import com.newdon.mapper.NewdonUserMapper;
import com.newdon.service.NewdonUserService;
import org.springframework.stereotype.Service;

@Service
public class NewdonUserServiceImpl extends ServiceImpl<NewdonUserMapper, NewdonUser> implements NewdonUserService {

}
