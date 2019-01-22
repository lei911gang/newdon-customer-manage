package com.newdon.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.base.NewDonResult;
import com.newdon.entity.ContractCategory;
import com.newdon.service.ContractCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @ClassName ContractCategoryController
 * @Auther: Dong
 * @Date: 2019/1/16 22:09
 * @Description: TODO
 **/
@RequestMapping("/newdon/contractCategory")
@RestController
@Slf4j
public class ContractCategoryController {
    @Autowired
    private ContractCategoryService contractCategoryService;

    @PostMapping(value = "/query")
    public NewDonResult query(ContractCategory contractCategory, Integer page, Integer rows) {
        if (null == page || page < 0) {
            page = 1;
        }
        if (null == rows || rows < 0) {
            rows = 10;
        }
        EntityWrapper<ContractCategory> wrapper = new EntityWrapper();
        wrapper.setEntity(contractCategory);
        Page<ContractCategory> pageInfo = this.contractCategoryService.selectPage(new Page<>(page, rows), wrapper);
        return NewDonResult.build(200, "OK", pageInfo);
    }
}
