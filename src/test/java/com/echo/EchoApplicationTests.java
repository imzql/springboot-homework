package com.echo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.controller.ZjlAccountController;
import com.echo.controller.ZjlCustomerColler;
import com.echo.controller.ZjlUserController;
import com.echo.mapper.zjlCustomerMapper;
import com.echo.mapper.zjlResourceMapper;
import com.echo.mapper.zjlAccountMapper;
import com.echo.pojo.*;
import com.echo.service.ZjlMenuService;
import com.echo.service.impl.ZjlAccountServiceImpl;
import com.echo.service.impl.ZjlCustomerServiceImpl;
import com.echo.service.impl.ZjlResourceShowServiceImpl;
import com.echo.service.impl.ZjlRoleServiceImpl;
import com.echo.utils.AccountQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class EchoApplicationTests {

    @Autowired
    private ZjlResourceShowServiceImpl zjlResourceShowService;

    @Test
    void contextLoads() {
        List<zjlModelView> zjlModelViews = zjlResourceShowService.lookUpdateModels(1L);
        System.out.println(zjlModelViews);

    }
}
