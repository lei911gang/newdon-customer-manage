package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.wxiaoqi.merge.core.MergeCore;
import com.newdon.base.Insert;
import com.newdon.base.NewDonResult;
import com.newdon.base.Update;
import com.newdon.entity.TechnologyInfo;
import com.newdon.service.TechnologyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private MergeCore mergeCore;
    @PostMapping(value = "/query")
    public NewDonResult query(TechnologyInfo technologyInfo, Integer page, Integer rows) {
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
        Page<TechnologyInfo> pageInfo = this.technologyInfoService.selectPage(new Page<>(page, rows), wrapper);
        try {
            if (!pageInfo.getRecords().isEmpty()) {
                mergeCore.mergeResult(TechnologyInfo.class, pageInfo.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NewDonResult.build(200, "OK", pageInfo);
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