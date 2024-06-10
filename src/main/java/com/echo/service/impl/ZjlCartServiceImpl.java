package com.echo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlCartMapper;
import com.echo.mapper.zjlCustomerMapper;
import com.echo.pojo.zjlCart;
import com.echo.pojo.zjlCustomer;
import com.echo.service.ZjlCartService;
import com.echo.service.ZjlCustomerService;
import org.springframework.stereotype.Service;

@Service
public class ZjlCartServiceImpl extends ServiceImpl<zjlCartMapper, zjlCart> implements ZjlCartService {

}
