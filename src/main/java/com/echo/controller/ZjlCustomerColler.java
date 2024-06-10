package com.echo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.pojo.zjlCustomer;
import com.echo.service.ZjlCustomerService;
import com.echo.service.impl.ZjlCustomerServiceImpl;
import com.echo.utils.ZjlRUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("customer")
public class ZjlCustomerColler {

    @Autowired
    private ZjlCustomerServiceImpl zjlCustomerService;

    //如果是返回信息给前端人员使用
    @RequestMapping("toList")
    public String toCustomerModel(){
        return "customer/customerList";
    }

    @RequestMapping("list")
    @ResponseBody
    public  R<HashMap<String, Object>> customerList(String zjlRealName, String zjlPhone, Long page, Long limit){
        LambdaQueryWrapper<zjlCustomer> query = Wrappers.<zjlCustomer>lambdaQuery();

        query.like(StringUtils.isNotBlank(zjlRealName),zjlCustomer::getZjlRealName,zjlRealName)
                .like(StringUtils.isNotBlank(zjlPhone),zjlCustomer::getZjlPhone,zjlPhone)
                .orderByDesc(zjlCustomer::getZjlCustomerId);

        Page<zjlCustomer> Customerpage = new Page<>(page,limit);
        Page<zjlCustomer> zjlCustomers = zjlCustomerService.page(Customerpage, query);

        return ZjlRUtils.getCountRecords(zjlCustomers);
    }



    @RequestMapping("toAdd")
    public String toCustomerAdd(){
        return "customer/customerAdd";
    }

    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody zjlCustomer zjlCustomer){
//        System.out.println(zjlCustomer);
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        zjlCustomer.setZjlCreateTime(LocalDateTime.now());
        zjlCustomer.setZjlModifiedTime(LocalDateTime.now());
        boolean sucess = zjlCustomerService.save(zjlCustomer);
        return ZjlRUtils.rightOrnot(sucess);
    }


    @GetMapping("toUpdate/{id}")
    public String toCustomerUpdate(@PathVariable Long id, Model model){
        //根据id查询客户的信息
        zjlCustomer zjlCustomer = zjlCustomerService.getById(id);
        model.addAttribute("zjlCustomer",zjlCustomer);
        return "customer/customerUpdate";
    }


    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody zjlCustomer zjlcustomer){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean sucess = zjlCustomerService.updateById(zjlcustomer);

        return ZjlRUtils.rightOrnot(sucess);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean sucess = zjlCustomerService.removeById(id);
        return ZjlRUtils.rightOrnot(sucess);
    }

    @GetMapping("toDetail/{id}")
    public String toCustomerDetail(@PathVariable Long id, Model model){
        //根据id查询客户的信息
        zjlCustomer zjlCustomer = zjlCustomerService.getById(id);
        model.addAttribute("zjlCustomer",zjlCustomer);
        return "customer/customerDetail";
    }
}
