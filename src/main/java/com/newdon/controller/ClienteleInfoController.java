package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.Result;
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
    public Result query(ClienteleInfo clienteleInfo, Integer page, Integer rows){
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        clienteleInfo.setStatus(1);
        EntityWrapper<ClienteleInfo> wrapper = new EntityWrapper();
//        wrapper.setEntity(clienteleInfo);
        //TODO 模糊查询示例
        if (StringUtils.isNotBlank(clienteleInfo.getClienteleCategory())) {
            wrapper.like("clientele_category", clienteleInfo.getClienteleCategory());
        }
        Page<ClienteleInfo> pageInfo = this.clienteleInfoService.selectPage(new Page<>(page, rows), wrapper);
        return Result.build(200, "OK", pageInfo);
    }

	@PostMapping(value = "/insert")
    public Result insert(@Validated(Insert.class) @RequestBody ClienteleInfo clienteleInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.build(400,"FAILED",bindingResult.getFieldError().getDefaultMessage());
        }
        clienteleInfo.setStatus(1);
        boolean insert = this.clienteleInfoService.insert(clienteleInfo);
        if (insert) {
            return Result.build(200, "OK", clienteleInfo.getId());
        } else {
            return Result.build(500, "FAILED",null);
        }
    }

	@PostMapping(value = "/update")
    public Result update(@Validated(Update.class) @RequestBody ClienteleInfo clienteleInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.build(400,"FAILED",bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.clienteleInfoService.updateById(clienteleInfo);
        if (b) {
            return Result.build(200, "OK", clienteleInfo.getId());
        } else {
            return Result.build(500, "FAILED",null);
        }
    }

	@PostMapping(value = "/delete")
    public Result delete(@RequestParam("id") Long id){
        ClienteleInfo clienteleInfo = new ClienteleInfo();
        clienteleInfo.setId(id);
        clienteleInfo.setStatus(0);
        boolean b = this.clienteleInfoService.updateById(clienteleInfo);
        if (b) {
            return Result.build(200, "OK", clienteleInfo.getId());
        } else {
            return Result.build(500, "FAILED",null);
        }
    }
}