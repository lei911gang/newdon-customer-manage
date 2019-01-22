package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.NewDonResult;
import com.newdon.base.Update;
import com.newdon.entity.DeviceInformationAndQuantity;
import com.newdon.service.DeviceInformationAndQuantityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @ClassName DeviceInformationAndQuantityService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/deviceInformationAndQuantity")
@RestController
@Slf4j
public class DeviceInformationAndQuantityController {
    @Autowired
    private DeviceInformationAndQuantityService deviceInformationAndQuantityService;

    @PostMapping(value = "/query")
    public NewDonResult query(DeviceInformationAndQuantity deviceInformationAndQuantity, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        EntityWrapper<DeviceInformationAndQuantity> wrapper = new EntityWrapper();
        wrapper.setEntity(deviceInformationAndQuantity);
        Page<DeviceInformationAndQuantity> pageInfo = this.deviceInformationAndQuantityService.selectPage(new Page<>(page, rows), wrapper);
        return NewDonResult.build(200, "OK", pageInfo);
    }

    @PostMapping(value = "/insert")
    public NewDonResult insert(@Validated(Insert.class) @RequestBody DeviceInformationAndQuantity deviceInformationAndQuantity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean insert = this.deviceInformationAndQuantityService.insert(deviceInformationAndQuantity);
        if (insert) {
            return NewDonResult.build(200, "OK", deviceInformationAndQuantity.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    public NewDonResult update(@Validated(Update.class) @RequestBody DeviceInformationAndQuantity deviceInformationAndQuantity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.deviceInformationAndQuantityService.updateById(deviceInformationAndQuantity);
        if (b) {
            return NewDonResult.build(200, "OK", deviceInformationAndQuantity.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    public NewDonResult delete(@RequestParam("id") Long id) {
        boolean b = this.deviceInformationAndQuantityService.deleteById(id);
        if (b) {
            return NewDonResult.build(200, "OK", id);
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }
}