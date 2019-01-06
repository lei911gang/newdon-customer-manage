package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.Result;
import com.newdon.base.Update;
import com.newdon.entity.SystemLevelAndQuantity;
import com.newdon.service.SystemLevelAndQuantityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @ClassName SystemLevelAndQuantityService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/systemLevelAndQuantity")
@RestController
@Slf4j
public class SystemLevelAndQuantityController {
    @Autowired
    private SystemLevelAndQuantityService systemLevelAndQuantityService;

    @PostMapping(value = "/query")
    public Result query(SystemLevelAndQuantity systemLevelAndQuantity, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        EntityWrapper<SystemLevelAndQuantity> wrapper = new EntityWrapper();
        wrapper.setEntity(systemLevelAndQuantity);
        Page<SystemLevelAndQuantity> pageInfo = this.systemLevelAndQuantityService.selectPage(new Page<>(page, rows), wrapper);
        return Result.build(200, "OK", pageInfo);
    }

    @PostMapping(value = "/insert")
    public Result insert(@Validated(Insert.class) @RequestBody SystemLevelAndQuantity systemLevelAndQuantity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean insert = this.systemLevelAndQuantityService.insert(systemLevelAndQuantity);
        if (insert) {
            return Result.build(200, "OK", systemLevelAndQuantity.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    public Result update(@Validated(Update.class) @RequestBody SystemLevelAndQuantity systemLevelAndQuantity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.systemLevelAndQuantityService.updateById(systemLevelAndQuantity);
        if (b) {
            return Result.build(200, "OK", systemLevelAndQuantity.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("id") Long id) {
        boolean b = this.systemLevelAndQuantityService.deleteById(id);
        if (b) {
            return Result.build(200, "OK", id);
        } else {
            return Result.build(500, "FAILED", null);
        }
    }
}