package com.newdon.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.newdon.entity.NewdonCustomer;
import com.newdon.service.NewdonCustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("customer")
public class NewdonCustomerController {
    @Autowired
    private NewdonCustomerService newdonCustomerService;

    @RequestMapping("add")
    public String add() {
        return "customer/customerAdd";
    }

    @PostMapping("insert")
    public String addList(NewdonCustomer newdonCustomer, Model model) {
        newdonCustomer.setStatus(1);
        boolean insert = this.newdonCustomerService.insert(newdonCustomer);
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
        NewdonCustomer newdonCustomer = new NewdonCustomer();
        newdonCustomer.setId(id);
        newdonCustomer.setStatus(0);
        boolean b = this.newdonCustomerService.updateById(newdonCustomer);
        model.addAttribute("error", "删除失败!");
        if (!b) {
            model.addAttribute("error", "删除失败!");
            return "failed";
        } else {
            model.addAttribute("success", "删除成功!");
            return "success";
        }
//        return "redirect:customer/query";
    }

    @RequestMapping("update")
    public String update(NewdonCustomer newdonCustomer, Model model, Integer pageNum) {
//        boolean b = this.newdonCustomerService.updateById(newdonCustomer);
//        if (!b) {
//            model.addAttribute("error", "修改失败!");
//        }
//        model.addAttribute("success", "修改成功!");
        NewdonCustomer customer = this.newdonCustomerService.selectById(newdonCustomer.getId());
        model.addAttribute("customer", customer);
        model.addAttribute("pageNum", pageNum);
        return "customer/customerEdit";
    }

    @RequestMapping("edit")
    public String edit(NewdonCustomer newdonCustomer, Model model, Integer pageNum) {
        if (null == pageNum || pageNum < 0) {
            pageNum = 1;
        }
        boolean b = this.newdonCustomerService.updateById(newdonCustomer);
        if (!b) {
            model.addAttribute("error", "修改失败!");
        } else {
            model.addAttribute("success", "修改成功!");
        }
        EntityWrapper<NewdonCustomer> wrapper = new EntityWrapper<>();
        wrapper.eq("status", 1);
        Page<NewdonCustomer> page = this.newdonCustomerService.selectPage(new Page<>(pageNum, 10), wrapper);
        model.addAttribute("customerList", page.getRecords());
        model.addAttribute("customer", newdonCustomer);
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
        return "customer/customerList";
    }

    @RequestMapping("query")
    public String query(NewdonCustomer newdonCustomer, Model model, Integer pageNum, Integer tag) {
        if (null == pageNum || pageNum < 0) {
            pageNum = 1;
        }
        if (null == tag) {
            tag = -1;
        }
        EntityWrapper<NewdonCustomer> wrapper = new EntityWrapper<>();
        wrapper.eq("status", 1);
        if (StringUtils.isNotBlank(newdonCustomer.getCustomerName())) {
            if (1 == tag) {
                wrapper.eq("customer_name", newdonCustomer.getCustomerName());
            } else {
                wrapper.like("customer_name", newdonCustomer.getCustomerName());
            }
        }
        if (StringUtils.isNotBlank(newdonCustomer.getCustomerCategory())) {
            wrapper.like("customer_category", newdonCustomer.getCustomerCategory());
        }
        if (StringUtils.isNotBlank(newdonCustomer.getCustomerArea())) {
            wrapper.like("customer_area", newdonCustomer.getCustomerArea());
        }
        if (StringUtils.isNotBlank(newdonCustomer.getCustomerIndustry())) {
            wrapper.like("customer_industry", newdonCustomer.getCustomerIndustry());
        }
        if (StringUtils.isNotBlank(newdonCustomer.getMinceIndustry())) {
            wrapper.like("mince_industry", newdonCustomer.getMinceIndustry());
        }
        if (StringUtils.isNotBlank(newdonCustomer.getIndustryNature())) {
            wrapper.like("industry_nature", newdonCustomer.getIndustryNature());
        }
        Page<NewdonCustomer> page = this.newdonCustomerService.selectPage(new Page<>(pageNum, 10), wrapper);

        if (pageNum >= page.getPages()) {
            model.addAttribute("pageNum", page.getPages());
            page = this.newdonCustomerService.selectPage(new Page<>((int)page.getPages(), 10), wrapper);
        } else {
            model.addAttribute("pageNum", page.getCurrent());
        }

        model.addAttribute("customerList", page.getRecords());
        model.addAttribute("customer", newdonCustomer);
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
        return "customer/customerList";
    }
}

