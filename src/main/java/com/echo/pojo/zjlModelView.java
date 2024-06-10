package com.echo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class zjlModelView {
    //resourceId = 1
    private Long id;
    //resourceName = 系统管理
    private String title;
    //二级目录(子目录)
    private List<zjlModelView> children;
    //查看修改用户的模块是否处于被选中状态
    private boolean checked;
}
