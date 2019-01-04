package com.newdon.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.entity.BasicTechInfo;
import com.newdon.entity.NewdonContract;
import com.newdon.entity.NewdonCustomer;
import com.newdon.service.NewdonContractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("contract")
public class NewdonContractController {
    @Autowired
    private NewdonContractService newdonContractService;

    @RequestMapping("add")
    public String add() {
        return "contract/contractAdd";
    }

    @PostMapping("insert")
    public String addList(NewdonContract newdonContract, NewdonCustomer newdonCustomer, BasicTechInfo basicTechInfo, Model model) {
        return newdonContractService.add(newdonContract, newdonCustomer, basicTechInfo, model);
    }

    @RequestMapping("deleteById")
    @ResponseBody
    public String deleteById(@RequestParam("id") Long id, Model model) {
        NewdonContract newdonContract = new NewdonContract();
        newdonContract.setId(id);
        newdonContract.setStatus(0);
        boolean b = this.newdonContractService.updateById(newdonContract);
        if (!b) {
            model.addAttribute("error", "删除失败!");
            return "failed";
        } else {
            model.addAttribute("success", "删除成功!");
            return "success";
        }
    }

    @RequestMapping("update")
    public String update(NewdonContract newdonContract, Model model, Integer pageNum) {
//        boolean b = this.newdonCustomerService.updateById(newdonCustomer);
//        if (!b) {
//            model.addAttribute("error", "修改失败!");
//        }
//        model.addAttribute("success", "修改成功!");
        NewdonContract contract = this.newdonContractService.selectById(newdonContract.getId());
        model.addAttribute("contract", contract);
        model.addAttribute("pageNum", pageNum);
        return "contract/contractEdit";
    }

    @RequestMapping("edit")
    public String edit(NewdonContract newdonContract, Model model, Integer pageNum) {
        if (null == pageNum || pageNum < 0) {
            pageNum = 1;
        }
        boolean b = this.newdonContractService.updateById(newdonContract);
        if (!b) {
            model.addAttribute("error", "修改失败!");
        } else {
            model.addAttribute("success", "修改成功!");
        }
        EntityWrapper<NewdonContract> wrapper = new EntityWrapper<>();
        wrapper.eq("status", 1);
        Page<NewdonContract> page = this.newdonContractService.selectPage(new Page<>(pageNum, 10), wrapper);
        model.addAttribute("customerList", page.getRecords());
        model.addAttribute("customer", newdonContract);
        //获得当前页
        model.addAttribute("pageNum", page.getCurrent());
        //获得一页显示的条数
        model.addAttribute("pageSize", page.getSize());
        //是否是第一页
        model.addAttribute("isFirstPage", page.getCurrent() == 1);
        //获得总页数
        model.addAttribute("totalPage", page.getPages());
        //是否是最后一页
        model.addAttribute("totalSize", page.getTotal());
        return "contract/contractList";
    }

    @RequestMapping("query")
    public String query(NewdonContract newdonContract, Model model, Integer pageNum, Integer tag) {
        if (null == pageNum || pageNum < 0) {
            pageNum = 1;
        }
        if (null == tag) {
            tag = -1;
        }
        EntityWrapper<NewdonContract> wrapper = new EntityWrapper<>();
        wrapper.eq("status", 1);
        if (StringUtils.isNotBlank(newdonContract.getContractNumber())) {
            if (1 == tag) {
                wrapper.eq("contract_number", newdonContract.getContractNumber());
            } else {
                wrapper.like("contract_number", newdonContract.getContractNumber());
            }
        }
        if (StringUtils.isNotBlank(newdonContract.getContractCategory())) {
            wrapper.like("contract_category", newdonContract.getContractCategory());
        }
        if (null != newdonContract.getSignStart() && null != newdonContract.getSignEnd()) {
            wrapper.between("sign_date", newdonContract.getSignStart(), newdonContract.getSignEnd());
        }
        if (StringUtils.isNotBlank(newdonContract.getBusinessMan())) {
            wrapper.like("business_man", newdonContract.getBusinessMan());
        }
        if (null != newdonContract.getMoneyStart() && null != newdonContract.getMoneyEnd()) {
            wrapper.between("contract_money", newdonContract.getMoneyStart(), newdonContract.getMoneyEnd());
        }
        if (StringUtils.isNotBlank(newdonContract.getIncreaseOrStock())) {
            wrapper.like("increase_or_stock", newdonContract.getIncreaseOrStock());
        }
        if (StringUtils.isNotBlank(newdonContract.getInformationSource())) {
            wrapper.like("information_source", newdonContract.getInformationSource());
        }
        if (StringUtils.isNotBlank(newdonContract.getEvaluationOrganization())) {
            wrapper.like("evaluation_organization", newdonContract.getEvaluationOrganization());
        }
        if (StringUtils.isNotBlank(newdonContract.getCustomerName())) {
            wrapper.like("customer_name", newdonContract.getCustomerName());
        }

        Page<NewdonContract> page = this.newdonContractService.selectPage(new Page<>(pageNum, 10), wrapper);
        List<NewdonContract> list = this.newdonContractService.selectList(wrapper);
//        double sum = list.stream().mapToDouble(NewdonContract::getContractMoney).sum();
        Double sum = 0.0;
        for (NewdonContract contract : list) {
            sum = sum + contract.getContractMoney();
        }

        if (pageNum >= page.getPages()) {
            model.addAttribute("pageNum", page.getPages());
            page = this.newdonContractService.selectPage(new Page<>((int)page.getPages(), 10), wrapper);
        } else {
            model.addAttribute("pageNum", page.getCurrent());
        }


        model.addAttribute("sum", sum);
        model.addAttribute("customerList", page.getRecords());
        model.addAttribute("customer", newdonContract);
        //获得当前页
        //获得一页显示的条数
        model.addAttribute("pageSize", page.getSize());
        //是否是第一页
        model.addAttribute("isFirstPage", page.getCurrent() == 1);
        //获得总页数
        model.addAttribute("totalPage", page.getPages());
        //是否是最后一页
        model.addAttribute("totalSize", page.getTotal());
//        int i = 1 / 0;
        return "contract/contractList";
    }
}

