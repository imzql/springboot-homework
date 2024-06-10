package com.echo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.pojo.zjlCart;
import com.echo.pojo.zjlCart;
import com.echo.service.impl.ZjlCartServiceImpl;
import com.echo.utils.ZjlRUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
@RequestMapping("cart")
public class ZjlCartColler {

    @Autowired
    private ZjlCartServiceImpl zjlCartService;

    //如果是返回信息给前端人员使用
    @RequestMapping("toList")
    public String toCartModel(){
        return "cart/cartList";
    }

    @RequestMapping("list")
    @ResponseBody
    public  R<HashMap<String, Object>> cartList(String cartId, String userId, Long page, Long limit){
        LambdaQueryWrapper<zjlCart> query = Wrappers.<zjlCart>lambdaQuery();

        query.like(StringUtils.isNotBlank(cartId),zjlCart::getCartId,cartId)
                .like(StringUtils.isNotBlank(userId),zjlCart::getUserId,userId)
                .orderByDesc(zjlCart::getCartId);

        Page<zjlCart> Cartpage = new Page<>(page,limit);
        Page<zjlCart> zjlCarts = zjlCartService.page(Cartpage, query);

        return ZjlRUtils.getCountRecords(zjlCarts);
    }



    @RequestMapping("toAdd")
    public String toCartAdd(){
        return "cart/cartAdd";
    }

    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody zjlCart zjlCart){
//        System.out.println(zjlCart);
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M

        boolean sucess = zjlCartService.save(zjlCart);
        return ZjlRUtils.rightOrnot(sucess);
    }


    @GetMapping("toUpdate/{id}")
    public String toCartUpdate(@PathVariable Long id, Model model){
        //根据id查询客户的信息
        zjlCart zjlCart = zjlCartService.getById(id);
        model.addAttribute("zjlCart",zjlCart);
        return "cart/cartUpdate";
    }


    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody zjlCart zjlCart){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean sucess = zjlCartService.updateById(zjlCart);

        return ZjlRUtils.rightOrnot(sucess);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean sucess = zjlCartService.removeById(id);
        return ZjlRUtils.rightOrnot(sucess);
    }

    @GetMapping("toDetail/{id}")
    public String toCartDetail(@PathVariable int id, Model model){
        //根据id查询客户的信息
        zjlCart zjlCart = zjlCartService.getById(id);
        model.addAttribute("zjlCart",zjlCart);
        return "cart/cartDetail";
    }
}
