package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.Result;
import com.newdon.base.Update;
import com.newdon.entity.ContractInfo;
import com.newdon.service.ContractInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @ClassName ContractInfoService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/contractInfo")
@CrossOrigin
@RestController
@Slf4j
public class ContractInfoController {
    @Autowired
    private ContractInfoService contractInfoService;

    @PostMapping(value = "/query")
    @ResponseBody
    public Result query(ContractInfo contractInfo, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        contractInfo.setStatus(1);
        EntityWrapper<ContractInfo> wrapper = new EntityWrapper();
//        wrapper.setEntity(contractInfo);
        //TODO 范围查询示例（时间段和数字）
        if (null != contractInfo.getDateOfSignature() && null != contractInfo.getDateOfSignatureEnd()) {
            wrapper.between("date_of_signature", contractInfo.getDateOfSignature(), contractInfo.getDateOfSignatureEnd());
        }
        Page<ContractInfo> pageInfo = this.contractInfoService.selectPage(new Page<>(page, rows), wrapper);
        return Result.build(200, "OK", pageInfo);
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    //TODO 不仅要插入合同信息，还需要插入客户信息和技术信息，以及系统级别和数量，设备信息和数量，多张表
    public Result insert(@Validated(Insert.class) @RequestBody ContractInfo contractInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        contractInfo.setStatus(1);
        boolean insert = this.contractInfoService.insert(contractInfo);
        if (insert) {
            return Result.build(200, "OK", contractInfo.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Result update(@Validated(Update.class) @RequestBody ContractInfo contractInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.contractInfoService.updateById(contractInfo);
        if (b) {
            return Result.build(200, "OK", contractInfo.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestParam("id") Long id) {
        ContractInfo contractInfo = new ContractInfo();
        contractInfo.setId(id);
        contractInfo.setStatus(0);
        boolean b = this.contractInfoService.updateById(contractInfo);
        if (b) {
            return Result.build(200, "OK", contractInfo.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }
}