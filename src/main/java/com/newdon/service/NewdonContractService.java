package com.newdon.service;

import com.baomidou.mybatisplus.service.IService;
import com.newdon.entity.BasicTechInfo;
import com.newdon.entity.NewdonContract;
import com.newdon.entity.NewdonCustomer;
import org.springframework.ui.Model;

public interface NewdonContractService extends IService<NewdonContract> {

    String add(NewdonContract newdonContract, NewdonCustomer newdonCustomer, BasicTechInfo basicTechInfo, Model model);
}

