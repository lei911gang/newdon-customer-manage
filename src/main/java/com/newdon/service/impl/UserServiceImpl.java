package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.User;
import com.newdon.mapper.UserMapper;
import com.newdon.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: LeiGang
 * @create: 2019-01-05 17:02
 * @description:
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
