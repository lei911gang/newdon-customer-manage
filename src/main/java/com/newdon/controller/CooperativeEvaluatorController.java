package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.Insert;
import com.newdon.base.Result;
import com.newdon.base.Update;
import com.newdon.entity.CooperativeEvaluator;
import com.newdon.service.CooperativeEvaluatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @ClassName CooperativeEvaluatorService
 * @Auther: Dong
 * @Date: 2018/11/29 10:30
 * @Description: TODO
 **/

@RequestMapping("/newdon/cooperativeEvaluator")
@RestController
@Slf4j
public class CooperativeEvaluatorController {
    @Autowired
    private CooperativeEvaluatorService cooperativeEvaluatorService;

    @PostMapping(value = "/query")
    public Result query(CooperativeEvaluator cooperativeEvaluator, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        EntityWrapper<CooperativeEvaluator> wrapper = new EntityWrapper();
        wrapper.setEntity(cooperativeEvaluator);
        Page<CooperativeEvaluator> pageInfo = this.cooperativeEvaluatorService.selectPage(new Page<>(page, rows), wrapper);
        return Result.build(200, "OK", pageInfo);
    }

    @PostMapping(value = "/insert")
    public Result insert(@Validated(Insert.class) @RequestBody CooperativeEvaluator cooperativeEvaluator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean insert = this.cooperativeEvaluatorService.insert(cooperativeEvaluator);
        if (insert) {
            return Result.build(200, "OK", cooperativeEvaluator.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/update")
    public Result update(@Validated(Update.class) @RequestBody CooperativeEvaluator cooperativeEvaluator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.build(400, "FAILED", bindingResult.getFieldError().getDefaultMessage());
        }
        boolean b = this.cooperativeEvaluatorService.updateById(cooperativeEvaluator);
        if (b) {
            return Result.build(200, "OK", cooperativeEvaluator.getId());
        } else {
            return Result.build(500, "FAILED", null);
        }
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("id") Long id) {
        boolean b = this.cooperativeEvaluatorService.deleteById(id);
        if (b) {
            return Result.build(200, "OK", id);
        } else {
            return Result.build(500, "FAILED", null);
        }
    }
}