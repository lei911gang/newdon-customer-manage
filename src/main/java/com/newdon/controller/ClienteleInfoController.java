package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.NewDonResult;
import com.newdon.base.Update;
import com.newdon.entity.ClienteleInfo;
import com.newdon.service.ClienteleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @ClassName ClienteleInfoService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/clienteleInfo")
@RestController
@Slf4j
public class ClienteleInfoController {
    @Autowired
    private ClienteleInfoService clienteleInfoService;

	@PostMapping(value = "/query")
    public NewDonResult query(ClienteleInfo clienteleInfo, Integer page, Integer rows){
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
//        clienteleInfo.setStatus(1);
        EntityWrapper<ClienteleInfo> wrapper = new EntityWrapper();
        wrapper.eq("status", 1);
        if (StringUtils.isNotBlank(clienteleInfo.getClienteleName())) {
            wrapper.like("clientele_name", clienteleInfo.getClienteleName());
        }
        if (StringUtils.isNotBlank(clienteleInfo.getLinkman())) {
            wrapper.like("linkman", clienteleInfo.getLinkman());
        }
        if (StringUtils.isNotBlank(clienteleInfo.getContact())) {
            wrapper.like("contact", clienteleInfo.getContact());
        }
        if (StringUtils.isNotBlank(clienteleInfo.getClienteleCategory())) {
            wrapper.eq("clientele_category", clienteleInfo.getClienteleCategory());
        }
        if (StringUtils.isNotBlank(clienteleInfo.getClienteleRegion())) {
            wrapper.eq("clientele_region", clienteleInfo.getClienteleRegion());
        }
        if (StringUtils.isNotBlank(clienteleInfo.getClienteleTrade())) {
            wrapper.eq("clientele_trade", clienteleInfo.getClienteleTrade());
        }
        if (StringUtils.isNotBlank(clienteleInfo.getSubdivideTrade())) {
            wrapper.eq("subdivide_trade", clienteleInfo.getSubdivideTrade());
        }
        if (StringUtils.isNotBlank(clienteleInfo.getTradeProperty())) {
            wrapper.eq("trade_property", clienteleInfo.getTradeProperty());
        }
        Page<ClienteleInfo> pageInfo = this.clienteleInfoService.selectPage(new Page<>(page, rows), wrapper);
        return NewDonResult.build(200, "OK", pageInfo);
    }

	@PostMapping(value = "/insert")
    public NewDonResult insert(@Validated(Insert.class) @RequestBody ClienteleInfo clienteleInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return NewDonResult.build(400,"FAILED",bindingResult.getFieldError().getDefaultMessage());
        }
        clienteleInfo.setStatus(1);
        boolean insert = this.clienteleInfoService.insert(clienteleInfo);
        if (insert) {
            return NewDonResult.build(200, "OK", clienteleInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED",null);
        }
    }

	@PostMapping(value = "/update")
    public NewDonResult update(@Validated(Update.class) @RequestBody ClienteleInfo clienteleInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return NewDonResult.build(400,"FAILED",bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.clienteleInfoService.updateById(clienteleInfo);
        if (b) {
            return NewDonResult.build(200, "OK", clienteleInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED",null);
        }
    }

	@PostMapping(value = "/delete")
    public NewDonResult delete(@RequestParam("id") Long id){
        ClienteleInfo clienteleInfo = new ClienteleInfo();
        clienteleInfo.setId(id);
        clienteleInfo.setStatus(0);
        boolean b = this.clienteleInfoService.updateById(clienteleInfo);
        if (b) {
            return NewDonResult.build(200, "OK", clienteleInfo.getId());
        } else {
            return NewDonResult.build(500, "FAILED",null);
        }
    }
}