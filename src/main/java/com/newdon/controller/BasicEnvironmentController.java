package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.NewDonResult;
import com.newdon.base.Update;
import com.newdon.entity.BasicEnvironment;
import com.newdon.service.BasicEnvironmentService;
import com.newdon.service.BasicEnvironmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @ClassName BasicEnvironmentService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/basicEnvironment")
@RestController
@Slf4j
public class BasicEnvironmentController {
    @Autowired
    private BasicEnvironmentService basicEnvironmentService;

    @PostMapping(value = "/query")
    public NewDonResult query(BasicEnvironment basicEnvironment, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        EntityWrapper<BasicEnvironment> wrapper = new EntityWrapper();
        wrapper.setEntity(basicEnvironment);
        Page<BasicEnvironment> pageInfo = this.basicEnvironmentService.selectPage(new Page<>(page, rows), wrapper);
        return NewDonResult.build(200, "OK", pageInfo);
    }

    @PostMapping(value = "/insert")
    public NewDonResult insert(@Validated(Insert.class) @RequestBody BasicEnvironment basicEnvironment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean insert = this.basicEnvironmentService.insert(basicEnvironment);
        if (insert) {
            return NewDonResult.build(200, "OK", basicEnvironment.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    public NewDonResult update(@Validated(Update.class) @RequestBody BasicEnvironment basicEnvironment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return NewDonResult.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.basicEnvironmentService.updateById(basicEnvironment);
        if (b) {
            return NewDonResult.build(200, "OK", basicEnvironment.getId());
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    public NewDonResult delete(@RequestParam("id") Long id) {
        boolean b = this.basicEnvironmentService.deleteById(id);
        if (b) {
            return NewDonResult.build(200, "OK", id);
        } else {
            return NewDonResult.build(500, "FAILED", null);
        }
    }

}