package com.newdon.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.newdon.entity.BasicTechInfo;
import com.newdon.entity.NewdonContract;
import com.newdon.entity.NewdonCustomer;
import com.newdon.mapper.NewdonContractMapper;
import com.newdon.service.BasicTechInfoService;
import com.newdon.service.NewdonContractService;
import com.newdon.service.NewdonCustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class NewdonContractServiceImpl extends ServiceImpl<NewdonContractMapper, NewdonContract> implements NewdonContractService {
    @Autowired
    private NewdonCustomerService newdonCustomerService;
    @Autowired
    private BasicTechInfoService basicTechInfoService;
    @Override
    @Transactional
    public String add(NewdonContract newdonContract, NewdonCustomer newdonCustomer, BasicTechInfo basicTechInfo, Model model) {
        newdonContract.setStatus(1);
        newdonCustomer.setStatus(1);
        basicTechInfo.setStatus(1);
        NewdonContract n = new NewdonContract();
        n.setContractNumber(newdonContract.getContractNumber());
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.setEntity(n);
        NewdonContract contract = this.selectOne(wrapper);
        if (StringUtils.isBlank(newdonContract.getContractNumber())) {
            model.addAttribute("error", "合同编号不能为空!");
            return "redirect:add";
        }
        if (StringUtils.isBlank(newdonContract.getCustomerName())) {
            model.addAttribute("error", "客户名称不能为空!");
            return "redirect:add";
        }
        if (null != contract) {
            model.addAttribute("error", "合同编号已存在!");
            return "contract/contractAdd";
        }
        boolean insert = this.insert(newdonContract);
        this.newdonCustomerService.insert(newdonCustomer);
        this.basicTechInfoService.insert(basicTechInfo);
        if (!insert) {
            model.addAttribute("error", "添加失败!");
        } else {
            model.addAttribute("success", "添加成功!");
        }
        return "redirect:query";
    }
}
