package com.echo.pojo;

import lombok.Data;

@Data
public class zjlResource {
    //主键
    private Long zjlResourceId;
    //父id
    private Long zjlParentId;
    //资源名称
    private String zjlResourceName;
    //资源类型(0  目录 1.菜单 2.按钮)
    private Integer zjlResourceType;
    //请求地址
    private String zjlUrl;
    //权限标识码
    private String zjlCode;
    //排序
    private Integer zjlSort;
}
