package com.echo.service;

import com.echo.pojo.zjlResourceVo;

import java.util.List;

public interface ZjlMenuService {
    //根据用户他的地位,比如设计部,角色来查询他可以看到的模块
   List<zjlResourceVo> modelCanLookByRoleId(Long roleId);
}
