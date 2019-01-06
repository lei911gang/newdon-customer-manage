package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.Result;
import com.newdon.base.Update;
import com.newdon.entity.*;
import com.newdon.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

/**
 * @version 1.0
 * @ClassName ContractInfoService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/contractInfo")
@RestController
@Slf4j
public class ContractInfoController {
    @Autowired
    private ContractInfoService contractInfoService;
    @Autowired
    private ClienteleInfoService clienteleInfoService;
    @Autowired
    private TechnologyInfoService technologyInfoService;
    @Autowired
    private SystemLevelAndQuantityService systemLevelAndQuantityService;
    @Autowired
    private DeviceInformationAndQuantityService deviceInformationAndQuantityService;

    @PostMapping(value = "/query")
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
    @Transactional(rollbackFor = Exception.class)
    //TODO 不仅要插入合同信息，还需要插入客户信息和技术信息，以及系统级别和数量，设备信息和数量，多张表
    public Result insert(@Validated(Insert.class) @RequestBody ContractInfo contractInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        ClienteleInfo clienteleInfo = contractInfo.getClienteleInfo();
        clienteleInfo.setStatus(1);
        this.clienteleInfoService.insert(clienteleInfo);
        contractInfo.setStatus(1);
        contractInfo.setClienteleId(clienteleInfo.getId());
        this.contractInfoService.insert(contractInfo);
        TechnologyInfo technologyInfo = contractInfo.getTechnologyInfo();
        technologyInfo.setContractId(contractInfo.getId());
        technologyInfo.setStatus(1);
        this.technologyInfoService.insert(contractInfo.getTechnologyInfo());
        List<SystemLevelAndQuantity> systemLevelAndQuantities = contractInfo.getSystemLevelAndQuantities();
        List<DeviceInformationAndQuantity> deviceInformationAndQuantities = contractInfo.getDeviceInformationAndQuantities();
        for (SystemLevelAndQuantity s : systemLevelAndQuantities) {
            s.setContractId(contractInfo.getId());
        }
        for (DeviceInformationAndQuantity s : deviceInformationAndQuantities) {
            s.setContractId(contractInfo.getId());
        }
        this.systemLevelAndQuantityService.insertBatch(systemLevelAndQuantities);
        this.deviceInformationAndQuantityService.insertBatch(deviceInformationAndQuantities);
        //插入客户信息和技术信息，以及系统级别和数量，设备信息和数量
        return Result.build(200, "OK", contractInfo.getId());
    }

    @PostMapping(value = "/update")
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