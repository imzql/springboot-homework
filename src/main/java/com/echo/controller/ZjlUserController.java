package com.echo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ZjlUserController {


    @RequestMapping("/book")
    @ResponseBody
    public String look(){
        return "hello 你好";
    }
}
