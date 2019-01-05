package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.Result;
import com.newdon.base.Update;
import com.newdon.entity.TechnologyInfo;
import com.newdon.service.TechnologyInfoService;
import lombok.extern.slf4j.Slf4j;
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
@CrossOrigin
@RestController
@Slf4j
public class TechnologyInfoController {
    @Autowired
    private TechnologyInfoService technologyInfoService;

    @PostMapping(value = "/query")
    @ResponseBody
    public Result query(TechnologyInfo technologyInfo, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        technologyInfo.setStatus(1);
        EntityWrapper<TechnologyInfo> wrapper = new EntityWrapper();
        wrapper.setEntity(technologyInfo);
        Page<TechnologyInfo> pageInfo = this.technologyInfoService.selectPage(new Page<>(page, rows), wrapper);
        return Result.build(200, "OK", pageInfo);
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public Result insert(@Validated(Insert.class) @RequestBody TechnologyInfo technologyInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        technologyInfo.setStatus(1);
        boolean insert = this.technologyInfoService.insert(technologyInfo);
        if (insert) {
            return Result.build(200, "OK", technologyInfo.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Result update(@Validated(Update.class) @RequestBody TechnologyInfo technologyInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.technologyInfoService.updateById(technologyInfo);
        if (b) {
            return Result.build(200, "OK", technologyInfo.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestParam("id") Long id) {
        TechnologyInfo technologyInfo = new TechnologyInfo();
        technologyInfo.setId(id);
        technologyInfo.setStatus(0);
        boolean b = this.technologyInfoService.updateById(technologyInfo);
        if (b) {
            return Result.build(200, "OK", technologyInfo.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

}