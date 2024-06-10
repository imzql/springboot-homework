package com.echo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlBooksMapper;
import com.echo.mapper.zjlCustomerMapper;
import com.echo.pojo.zjlBooks;
import com.echo.pojo.zjlCustomer;
import com.echo.service.ZjlBooksService;
import com.echo.service.ZjlCustomerService;
import org.springframework.stereotype.Service;

@Service
public class ZjlBooksServiceImpl extends ServiceImpl<zjlBooksMapper, zjlBooks> implements ZjlBooksService {

}
