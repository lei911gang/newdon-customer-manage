package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.wxiaoqi.merge.core.MergeCore;
import com.newdon.base.*;
import com.newdon.constants.CommonConstants;
import com.newdon.entity.DeviceInformationAndQuantity;
import com.newdon.entity.SystemLevelAndQuantity;
import com.newdon.entity.TechnologyInfo;
import com.newdon.mapper.TechnologyInfoMapper;
import com.newdon.service.DeviceInformationAndQuantityService;
import com.newdon.service.SystemLevelAndQuantityService;
import com.newdon.service.TechnologyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @ClassName TechnologyInfoService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/technologyInfo")
@RestController
@Slf4j
public class TechnologyInfoController {
    @Autowired
    private TechnologyInfoService technologyInfoService;
    @Autowired
    private TechnologyInfoMapper technologyInfoMapper;
    @Autowired
    private MergeCore mergeCore;
    @Autowired
    private SystemLevelAndQuantityService systemLevelAndQuantityService;
    @Autowired
    private DeviceInformationAndQuantityService deviceInformationAndQuantityService;

    @PostMapping(value = "/query")
    public NewDonTechResult query(TechnologyInfo technologyInfo, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
//        technologyInfo.setStatus(1);
        EntityWrapper<TechnologyInfo> wrapper = new EntityWrapper();
        wrapper.eq("status", 1);
        if (StringUtils.isNotBlank(technologyInfo.getContractId())) {
            wrapper.like("contract_id", technologyInfo.getContractId());
        }
        if (StringUtils.isNotBlank(technologyInfo.getProjectManager())) {
            wrapper.like("project_manager", technologyInfo.getProjectManager());
        }
        if (StringUtils.isNotBlank(technologyInfo.getTechnicist())) {
            wrapper.like("technicist", technologyInfo.getTechnicist());
        }
        if (StringUtils.isNotBlank(technologyInfo.getBasicEnvironment())) {
            wrapper.eq("basic_environment", technologyInfo.getBasicEnvironment());
        }
        if (null != technologyInfo.getDateReleasedStart() && null != technologyInfo.getDateReleasedStop()) {
            wrapper.between("date_released", technologyInfo.getDateReleasedStart(), technologyInfo.getDateReleasedStop());
        }
        Page<TechnologyInfo> pageInfo = this.technologyInfoService.selectPage(new Page<>(page, rows), wrapper);
        try {
            if (!pageInfo.getRecords().isEmpty()) {
                mergeCore.mergeResult(TechnologyInfo.class, pageInfo.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(technologyInfo.getDeviceInfo())) {
            List<DeviceInformationAndQuantity> list = this.technologyInfoMapper.queryDevices(technologyInfo);
            sb.append(technologyInfo.getDeviceInfo()).append(":");
            int sum = 0;
            if (null != list && list.size() > 0) {
                for (DeviceInformationAndQuantity d : list) {
                    if (technologyInfo.getDeviceInfo().equals(d.getDeviceInfo())) {
                        //只统计查询的那一类
                        sum = sum + d.getDeviceQuantity();
                    }
                }
            }
            sb.append(sum).append(" ");
        }
        if (StringUtils.isNotBlank(technologyInfo.getSystemLevel())) {
            List<SystemLevelAndQuantity> list = this.technologyInfoMapper.querySystems(technologyInfo);
            sb.append(technologyInfo.getSystemLevel()).append(":");
            int sum = 0;
            if (null != list && list.size() > 0) {
                for (SystemLevelAndQuantity s : list) {
                    if (technologyInfo.getSystemLevel().equals(s.getSystemLevel())) {
                        //只统计查询的那一类
                        sum = sum + s.getSystemQuantity();
                    }
                }
            }
            sb.append(sum);
        }
        return NewDonTechResult.build(200, "OK", pageInfo, sb.toString());
    }

    @PostMapping(value = "/insert")
    public NewDonResult insert(@Validated(Insert.class) @RequestBody TechnologyInfo technologyInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        technologyInfo.setStatus(1);
        boolean insert = this.technologyInfoService.insert(technologyInfo);
        if (insert) {
            return NewDonResult.build(200, "OK", technologyInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    public NewDonResult update(@Validated(Update.class) @RequestBody TechnologyInfo technologyInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        technologyInfo.setSystemLevelAndQuantity(technologyInfo.getContractId());
        technologyInfo.setDeviceInformationAndQuantity(technologyInfo.getContractId());
        //删除原来的关联
        SystemLevelAndQuantity ss = new SystemLevelAndQuantity();
        ss.setContractId(technologyInfo.getContractId());
        DeviceInformationAndQuantity dd = new DeviceInformationAndQuantity();
        dd.setContractId(technologyInfo.getContractId());
        boolean delete = this.systemLevelAndQuantityService.delete(new EntityWrapper<>(ss));
        log.info(delete + "");
        boolean delete1 = this.deviceInformationAndQuantityService.delete(new EntityWrapper<>(dd));
        log.info(delete1 + "");
        List<SystemLevelAndQuantity> systemLevelAndQuantities = technologyInfo.getSystemLevelAndQuantities();
        if (null != systemLevelAndQuantities && systemLevelAndQuantities.size() > 0) {
            for (SystemLevelAndQuantity s : systemLevelAndQuantities) {
                s.setContractId(technologyInfo.getContractId());
            }
            boolean b = this.systemLevelAndQuantityService.insertBatch(systemLevelAndQuantities);
            if (!b) {
                throw new ContractInsertException(CommonConstants.EX_OTHER_CODE, "insert systemLevelAndQuantities failed!");
            }
        }
        List<DeviceInformationAndQuantity> deviceInformationAndQuantities = technologyInfo.getDeviceInformationAndQuantities();
        if (null != deviceInformationAndQuantities && deviceInformationAndQuantities.size() > 0) {
            for (DeviceInformationAndQuantity s : deviceInformationAndQuantities) {
                s.setContractId(technologyInfo.getContractId());
            }
            boolean b1 = this.deviceInformationAndQuantityService.insertBatch(deviceInformationAndQuantities);
            if (!b1) {
                throw new ContractInsertException(CommonConstants.EX_OTHER_CODE, "insert deviceInformationAndQuantities failed!");
            }
        }
        boolean b = this.technologyInfoService.updateById(technologyInfo);
        if (b) {
            return NewDonResult.build(200, "OK", technologyInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    public NewDonResult delete(@RequestParam("id") Long id) {
        TechnologyInfo technologyInfo = new TechnologyInfo();
        technologyInfo.setId(id);
        technologyInfo.setStatus(0);
        boolean b = this.technologyInfoService.updateById(technologyInfo);
        if (b) {
            return NewDonResult.build(200, "OK", technologyInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

}