package com.echo.pojo;

import lombok.Data;

@Data
public class zjlLoginDTO {

    //重定向或跳转路径
    private String path;
    //错误信息提示
    private String error;
    //当前登录人的信息
    private zjlAccount zjlAccount;
}
