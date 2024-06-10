package com.echo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.pojo.zjlBooks;
import com.echo.service.impl.ZjlBooksServiceImpl;
import com.echo.utils.ZjlRUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
@RequestMapping("books")
public class ZjlBooksColler {

    @Autowired
    private ZjlBooksServiceImpl zjlBooksService;

    //如果是返回信息给前端人员使用
    @RequestMapping("toList")
    public String toBooksModel(){
        return "books/booksList";
    }

    @RequestMapping("list")
    @ResponseBody
    public  R<HashMap<String, Object>> BooksList(String bookId, String title, Long page, Long limit){
        LambdaQueryWrapper<zjlBooks> query = Wrappers.<zjlBooks>lambdaQuery();

        query.like(StringUtils.isNotBlank(bookId),zjlBooks::getBookId,bookId)
                .like(StringUtils.isNotBlank(title),zjlBooks::getTitle,title)
                .orderByDesc(zjlBooks::getBookId);

        Page<zjlBooks> Bookspage = new Page<>(page,limit);
        Page<zjlBooks> zjlBookss = zjlBooksService.page(Bookspage, query);

        return ZjlRUtils.getCountRecords(zjlBookss);
    }



    @RequestMapping("toAdd")
    public String toBooksAdd(){
        return "books/booksAdd";
    }

    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody zjlBooks zjlBooks){
//        System.out.println(zjlBooks);
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
//        zjlBooks.setZjlCreateTime(LocalDateTime.now());
//        zjlBooks.setZjlModifiedTime(LocalDateTime.now());
        boolean sucess = zjlBooksService.save(zjlBooks);
        return ZjlRUtils.rightOrnot(sucess);
    }


    @GetMapping("toUpdate/{id}")
    public String toBooksUpdate(@PathVariable Long id, Model model){
        //根据id查询客户的信息
        zjlBooks zjlBooks = zjlBooksService.getById(id);
        model.addAttribute("zjlBooks",zjlBooks);
        return "books/BooksUpdate";
    }


    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody zjlBooks zjlBooks){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean sucess = zjlBooksService.updateById(zjlBooks);

        return ZjlRUtils.rightOrnot(sucess);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean sucess = zjlBooksService.removeById(id);
        return ZjlRUtils.rightOrnot(sucess);
    }

    @GetMapping("toDetail/{id}")
    public String toBooksDetail(@PathVariable Long id, Model model){
        //根据id查询客户的信息
        zjlBooks zjlBooks = zjlBooksService.getById(id);
        model.addAttribute("zjlBooks",zjlBooks);
        return "books/booksDetail";
    }
}
