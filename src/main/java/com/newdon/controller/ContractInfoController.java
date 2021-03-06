package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.ContractInsertException;
import com.newdon.base.Insert;
import com.newdon.base.NewDonResult;
import com.newdon.base.Update;
import com.newdon.bo.ContractDiagramBo;
import com.newdon.constants.CommonConstants;
import com.newdon.entity.*;
import com.newdon.mapper.ContractInfoMapper;
import com.newdon.service.*;
import com.newdon.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author LeiGang
 * @create 2019/1/21 11:37
 * @description
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
    public NewDonResult query(ContractInfo contractInfo, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
//        contractInfo.setStatus(1);
        EntityWrapper<ContractInfo> wrapper = new EntityWrapper();
        wrapper.eq("status", 1);
        if (StringUtils.isNotBlank(contractInfo.getContractId())) {
            wrapper.like("contract_id", contractInfo.getContractId());
        }
        wrapper.orderAsc(Collections.singleton("contract_id"));
        if (StringUtils.isNotBlank(contractInfo.getContractCategory())) {
            wrapper.eq("contract_category", contractInfo.getContractCategory());
        }
        if (StringUtils.isNotBlank(contractInfo.getBusinessPersonnel())) {
            wrapper.like("business_personnel", contractInfo.getBusinessPersonnel());
        }
        if (StringUtils.isNotBlank(contractInfo.getIncrementOfStockNumber())) {
            wrapper.eq("increment_of_stock_number", contractInfo.getIncrementOfStockNumber());
        }
        if (StringUtils.isNotBlank(contractInfo.getNewsFrom())) {
            wrapper.eq("news_from", contractInfo.getNewsFrom());
        }
        if (StringUtils.isNotBlank(contractInfo.getCooperativeEvaluator())) {
            wrapper.eq("cooperative_evaluator", contractInfo.getCooperativeEvaluator());
        }
        if (StringUtils.isNotBlank(contractInfo.getBusinessPersonnel())) {
            wrapper.like("business_personnel", contractInfo.getBusinessPersonnel());
        }
        if (StringUtils.isNotBlank(contractInfo.getClienteleName())) {
            wrapper.like("clientele_name", contractInfo.getClienteleName());
        }
        if (StringUtils.isNotBlank(contractInfo.getProcurementMethod())) {
            wrapper.eq("procurement_method", contractInfo.getProcurementMethod());
        }
        //TODO 范围查询示例（时间段和数字）
        if (null != contractInfo.getDateOfSignatureStart() && null != contractInfo.getDateOfSignatureStop()) {
            wrapper.between("date_of_signature", contractInfo.getDateOfSignatureStart(), contractInfo.getDateOfSignatureStop());
        }
        if (null != contractInfo.getContractSumBegin() && null != contractInfo.getContractSumEnd()) {
            wrapper.between("contract_sum", contractInfo.getContractSumBegin(), contractInfo.getContractSumEnd());
        }
        Page<ContractInfo> pageInfo = this.contractInfoService.selectPage(new Page<>(page, rows), wrapper);
        //总金额和平均金额
        List<ContractInfo> records = pageInfo.getRecords();
        Double sum = 0.0;
        Double average = 0.0;
        if (null != records && records.size() > 0) {
            for (ContractInfo contract : records) {
                sum = sum + contract.getContractSum();
            }
            average = sum / (records.size());
        }
        return NewDonResult.build(200, "OK", pageInfo, sum, average);
    }

    @PostMapping(value = "/queryBrokenLine")
    public Map<Long, Double> queryBrokenLine(ContractInfo contractInfo) {
        Map<Long, Double> map = new HashMap<>();
        if (null != contractInfo.getDateOfSignatureStart() && null != contractInfo.getDateOfSignatureStop()) {
            List<Long> monthBetween = TimeUtils.getMonthBetween(contractInfo.getDateOfSignatureStart(), contractInfo.getDateOfSignatureStop());
            for (Long date : monthBetween) {
                Long monthBegin = TimeUtils.getMonthBegin(date);
                Long monthEnd = TimeUtils.getMonthEnd(date);
                EntityWrapper<ContractInfo> wrapper = new EntityWrapper();
                wrapper.eq("status", 1);
                if (StringUtils.isNotBlank(contractInfo.getContractId())) {
                    wrapper.like("contract_id", contractInfo.getContractId());
                }
                if (StringUtils.isNotBlank(contractInfo.getContractCategory())) {
                    wrapper.eq("contract_category", contractInfo.getContractCategory());
                }
                if (StringUtils.isNotBlank(contractInfo.getBusinessPersonnel())) {
                    wrapper.like("business_personnel", contractInfo.getBusinessPersonnel());
                }
                if (StringUtils.isNotBlank(contractInfo.getIncrementOfStockNumber())) {
                    wrapper.eq("increment_of_stock_number", contractInfo.getIncrementOfStockNumber());
                }
                if (StringUtils.isNotBlank(contractInfo.getNewsFrom())) {
                    wrapper.eq("news_from", contractInfo.getNewsFrom());
                }
                if (StringUtils.isNotBlank(contractInfo.getCooperativeEvaluator())) {
                    wrapper.eq("cooperative_evaluator", contractInfo.getCooperativeEvaluator());
                }
                if (StringUtils.isNotBlank(contractInfo.getServiceContent())) {
                    wrapper.eq("service_content", contractInfo.getServiceContent());
                }
                if (StringUtils.isNotBlank(contractInfo.getProcurementMethod())) {
                    wrapper.eq("procurement_method", contractInfo.getProcurementMethod());
                }
                if (StringUtils.isNotBlank(contractInfo.getBusinessPersonnel())) {
                    wrapper.like("business_personnel", contractInfo.getBusinessPersonnel());
                }
                if (StringUtils.isNotBlank(contractInfo.getClienteleName())) {
                    wrapper.like("clientele_name", contractInfo.getClienteleName());
                }
                //TODO 范围查询示例（时间段和数字）
                if (null != contractInfo.getDateOfSignatureStart() && null != contractInfo.getDateOfSignatureStop()) {
                    wrapper.between("date_of_signature", monthBegin, monthEnd);
                }
                if (null != contractInfo.getContractSumBegin() && null != contractInfo.getContractSumEnd()) {
                    wrapper.between("contract_sum", contractInfo.getContractSumBegin(), contractInfo.getContractSumEnd());
                }
                List<ContractInfo> contractInfos = this.contractInfoService.selectList(wrapper);
                //总金额和平均金额
                Double sum = 0.0;
                if (null != contractInfos && contractInfos.size() > 0) {
                    for (ContractInfo contract : contractInfos) {
                        sum = sum + contract.getContractSum();
                    }
                }
                map.put(monthBegin, sum);
            }
        }
        return map;
    }

    @PostMapping(value = "/insert")
    @Transactional(rollbackFor = Exception.class)
    //TODO 不仅要插入合同信息，还需要插入客户信息和技术信息，以及系统级别和数量，设备信息和数量，多张表
    public NewDonResult insert(@Validated(Insert.class) @RequestBody ContractInfo contractInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        //检验合同编号
        ContractInfo c = new ContractInfo();
        c.setStatus(1);
        c.setContractId(contractInfo.getContractId());
        int i = this.contractInfoService.selectCount(new EntityWrapper<>(c));
        if (i > 0) {
            return NewDonResult.build(500, "合同编号已被占用!", contractInfo.getContractId());
        }
//        ClienteleInfo clienteleInfo = contractInfo.getClienteleInfo();
//        clienteleInfo.setStatus(1);
//        boolean insert = this.clienteleInfoService.insert(clienteleInfo);
//        if (!insert) {
//            throw new ContractInsertException(CommonConstants.EX_OTHER_CODE, "insert clienteleInfo failed!");
//        }
        contractInfo.setStatus(1);
        contractInfo.setClienteleName(contractInfo.getClienteleInfo().getClienteleName());
        boolean insert1 = this.contractInfoService.insert(contractInfo);
        if (!insert1) {
            throw new ContractInsertException(CommonConstants.EX_OTHER_CODE, "insert contractInfo failed!");
        }
        TechnologyInfo technologyInfo = contractInfo.getTechnologyInfo();
        technologyInfo.setStatus(1);
        technologyInfo.setContractId(contractInfo.getContractId());
        technologyInfo.setSystemLevelAndQuantity(String.valueOf(contractInfo.getContractId()));
        technologyInfo.setDeviceInformationAndQuantity(String.valueOf(contractInfo.getContractId()));
        boolean insert2 = this.technologyInfoService.insert(technologyInfo);
        if (!insert2) {
            throw new ContractInsertException(CommonConstants.EX_OTHER_CODE, "insert technologyInfo failed!");
        }
        List<SystemLevelAndQuantity> systemLevelAndQuantities = contractInfo.getSystemLevelAndQuantities();
        if (null != systemLevelAndQuantities && systemLevelAndQuantities.size() > 0) {
            for (SystemLevelAndQuantity s : systemLevelAndQuantities) {
                s.setContractId(contractInfo.getContractId());
            }
            boolean b = this.systemLevelAndQuantityService.insertBatch(systemLevelAndQuantities);
            if (!b) {
                throw new ContractInsertException(CommonConstants.EX_OTHER_CODE, "insert systemLevelAndQuantities failed!");
            }
        }
        List<DeviceInformationAndQuantity> deviceInformationAndQuantities = contractInfo.getDeviceInformationAndQuantities();
        if (null != deviceInformationAndQuantities && deviceInformationAndQuantities.size() > 0) {
            for (DeviceInformationAndQuantity s : deviceInformationAndQuantities) {
                s.setContractId(contractInfo.getContractId());
            }
            boolean b1 = this.deviceInformationAndQuantityService.insertBatch(deviceInformationAndQuantities);
            if (!b1) {
                throw new ContractInsertException(CommonConstants.EX_OTHER_CODE, "insert deviceInformationAndQuantities failed!");
            }
        }
        //插入客户信息和技术信息，以及系统级别和数量，设备信息和数量
        return NewDonResult.build(200, "OK", contractInfo.getId());
    }

    @PostMapping(value = "/update")
    public NewDonResult update(@Validated(Update.class) @RequestBody ContractInfo contractInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.contractInfoService.updateById(contractInfo);
        if (b) {
            return NewDonResult.build(200, "OK", contractInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    public NewDonResult delete(@RequestParam("id") Long id) {
        ContractInfo contractInfo = new ContractInfo();
        contractInfo.setId(id);
        contractInfo.setStatus(0);
        ContractInfo info = this.contractInfoService.selectById(id);
        if (null != info) {
            TechnologyInfo technologyInfo = new TechnologyInfo();
            technologyInfo.setStatus(0);
            TechnologyInfo technologyInfo1 = new TechnologyInfo();
            technologyInfo1.setContractId(info.getContractId());
            this.technologyInfoService.update(technologyInfo, new EntityWrapper<>(technologyInfo1));
        }
        boolean b = this.contractInfoService.updateById(contractInfo);
        if (b) {
            return NewDonResult.build(200, "OK", contractInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @Autowired
    private ContractInfoMapper contractInfoMapper;

    @PostMapping(value = "/queryDiagramWithBusinessPerson")
    public List<ContractDiagramBo> queryDiagramWithBusinessPerson(ContractDiagramBo contractDiagramBo) {
        return this.contractInfoMapper.queryDiagramWithBusinessPerson(contractDiagramBo);
    }

    @PostMapping(value = "/queryDiagramWithNewsFrom")
    public List<ContractDiagramBo> queryDiagramWithNewsFrom(ContractDiagramBo contractDiagramBo) {
        return this.contractInfoMapper.queryDiagramWithNewsFrom(contractDiagramBo);
    }

    @Autowired
    private RegionService regionService;
    @PostMapping(value = "/queryDiagramWithRegion")
    public List<ContractDiagramBo> queryDiagramWithRegion(ContractDiagramBo contractDiagramBo) {
        //TODO 上海北京按照区，其他按照省和主要城市
        List<Region> regions = this.regionService.selectList(null);
        List<String> list = new ArrayList<>();
        for (Region region : regions) {
            if (region.getRegionName().startsWith("上海市") || region.getRegionName().startsWith("北京市")) {

            }
        }
        return this.contractInfoMapper.queryDiagramWithRegion(contractDiagramBo);
    }
}