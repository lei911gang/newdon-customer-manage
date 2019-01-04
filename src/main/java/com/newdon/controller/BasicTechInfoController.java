package com.newdon.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.entity.BasicTechInfo;
import com.newdon.service.BasicTechInfoService;
import com.newdon.service.NewdonContractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("basic")
public class BasicTechInfoController {
    @Autowired
    private BasicTechInfoService basicTechInfoService;
    @Autowired
    private NewdonContractService newdonContractService;

    @RequestMapping("add")
    public String add() {
        return "basic/basicAdd";
    }

    @PostMapping("insert")
    public String addList(BasicTechInfo basicTechInfo, Model model) {
        basicTechInfo.setStatus(1);
        boolean insert = this.basicTechInfoService.insert(basicTechInfo);
        if (!insert) {
            model.addAttribute("error", "添加失败!");
        } else {
            model.addAttribute("success", "添加成功!");
        }
        return "redirect:query";
    }

    @RequestMapping("deleteById")
    @ResponseBody
    public String deleteById(@RequestParam("id") Long id, Model model) {
        BasicTechInfo basicTechInfo = new BasicTechInfo();
        basicTechInfo.setId(id);
        basicTechInfo.setStatus(0);
        boolean b = this.basicTechInfoService.updateById(basicTechInfo);
        if (!b) {
            model.addAttribute("error", "删除失败!");
            return "failed";
        } else {
            model.addAttribute("success", "删除成功!");
            return "success";
        }
    }

    @RequestMapping("update")
    public String update(BasicTechInfo basicTechInfo, Model model, Integer pageNum) {
//        boolean b = this.newdonCustomerService.updateById(newdonCustomer);
//        if (!b) {
//            model.addAttribute("error", "修改失败!");
//        }
//        model.addAttribute("success", "修改成功!");
        BasicTechInfo basic = this.basicTechInfoService.selectById(basicTechInfo.getId());
        model.addAttribute("basic", basic);
        model.addAttribute("pageNum", pageNum);
        return "basic/basicEdit";
    }

    @RequestMapping("edit")
    public String edit(BasicTechInfo basicTechInfo, Model model, Integer pageNum) {
        if (null == pageNum || pageNum < 0) {
            pageNum = 1;
        }
        boolean b = this.basicTechInfoService.updateById(basicTechInfo);
        if (!b) {
            model.addAttribute("error", "修改失败!");
        } else {
            model.addAttribute("success", "修改成功!");
        }
        EntityWrapper<BasicTechInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("status", 1);
        Page<BasicTechInfo> page = this.basicTechInfoService.selectPage(new Page<>(pageNum, 10), wrapper);
        model.addAttribute("basicList", page.getRecords());
        model.addAttribute("basic", basicTechInfo);
        //获得当前页
        model.addAttribute("pageNum", page.getCurrent());
        //获得一页显示的条数
        model.addAttribute("pageSize", page.getSize());
        //是否是第一页
        model.addAttribute("isFirstPage", page.getCurrent() == 1);
        //获得总页数
        model.addAttribute("totalPage", page.getPages());
        //是否是最后一页
        model.addAttribute("totalSize", page.getTotal());
        return "basic/basicList";
    }

    @RequestMapping("query")
    public String query(BasicTechInfo basicTechInfo, Model model, Integer pageNum, Integer tag) {
        if (null == pageNum || pageNum < 0) {
            pageNum = 1;
        }
        if (null == tag) {
            tag = -1;
        }
        EntityWrapper<BasicTechInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("status", 1);
        if (StringUtils.isNotBlank(basicTechInfo.getContractNumber())) {
            if (1 == tag) {
                wrapper.eq("contract_number", basicTechInfo.getContractNumber());
            } else {
                wrapper.like("contract_number", basicTechInfo.getContractNumber());
            }
        }
        if (StringUtils.isNotBlank(basicTechInfo.getProjectManager())) {
            wrapper.like("project_manager", basicTechInfo.getProjectManager());
        }
        if (StringUtils.isNotBlank(basicTechInfo.getArtisan())) {
            wrapper.like("artisan", basicTechInfo.getArtisan());
        }
        if (StringUtils.isNotBlank(basicTechInfo.getSystemLevel())) {
            wrapper.like("system_level", basicTechInfo.getSystemLevel());
        }
        if (basicTechInfo.getSystemCount()>0) {
            wrapper.eq("system_count", basicTechInfo.getSystemCount());
        }
        if (null != basicTechInfo.getDeliverStart() && null != basicTechInfo.getDeliverEnd()) {
            wrapper.between("deliver_date", basicTechInfo.getDeliverStart(), basicTechInfo.getDeliverEnd());
        }

        Page<BasicTechInfo> page = this.basicTechInfoService.selectPage(new Page<>(pageNum, 10), wrapper);

        if (pageNum >= page.getPages()) {
            model.addAttribute("pageNum", page.getPages());
            page = this.basicTechInfoService.selectPage(new Page<>((int)page.getPages(), 10), wrapper);
        } else {
            model.addAttribute("pageNum", page.getCurrent());
        }

        model.addAttribute("basicList", page.getRecords());
        model.addAttribute("basic", basicTechInfo);
        //获得当前页
//        model.addAttribute("pageNum", page.getCurrent());
        //获得一页显示的条数
        model.addAttribute("pageSize", page.getSize());
        //是否是第一页
        model.addAttribute("isFirstPage", page.getCurrent() == 1);
        //获得总页数
        model.addAttribute("totalPage", page.getPages());
        //是否是最后一页
        model.addAttribute("totalSize", page.getTotal());
//        int i = 1 / 0;
        return "basic/basicList";
    }
}

