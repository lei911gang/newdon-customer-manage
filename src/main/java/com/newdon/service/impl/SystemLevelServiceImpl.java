package com.newdon.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.ServiceContent;
import com.newdon.entity.SystemLevel;
import com.newdon.mapper.ServiceContentMapper;
import com.newdon.mapper.SystemLevelMapper;
import com.newdon.service.ServiceContentService;
import com.newdon.service.SystemLevelService;
import org.springframework.stereotype.Service;

/**
 * @author: LeiGang
 * @create: 2019-01-05 17:02
 * @description:
 **/
@Service
public class SystemLevelServiceImpl extends ServiceImpl<SystemLevelMapper, SystemLevel> implements SystemLevelService {
}
