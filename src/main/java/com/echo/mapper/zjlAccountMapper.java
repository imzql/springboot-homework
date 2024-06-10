package com.echo.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.pojo.zjlAccount;
import com.echo.pojo.zjlCustomer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface zjlAccountMapper extends BaseMapper<zjlAccount> {


    IPage<zjlAccount> zjlAccountPage(Page<zjlAccount> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    //根据accountid查询account与role表生成新表的账号信息

    //根据角色id进行查询
    zjlAccount zjlAccountOne(Long id);
}
