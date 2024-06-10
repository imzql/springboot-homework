package com.echo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlAccountMapper;
import com.echo.pojo.zjlAccount;
import com.echo.pojo.zjlCustomer;
import com.echo.service.ZjlAccountService;
import com.echo.utils.AccountQuery;
import com.echo.utils.ZjlRUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ZjlAccountServiceImpl extends ServiceImpl<zjlAccountMapper, zjlAccount> implements ZjlAccountService {
    //Account表与role表结合的新表的分页查询以及条件查询
    @Override
    public R<HashMap<String, Object>> zjlPageQuery(AccountQuery accountQuery) {

        QueryWrapper<zjlAccount> query = Wrappers.query();
        query.like(StringUtils.isNotBlank(accountQuery.getZjlRealName()), "a.Zjl_real_name", accountQuery.getZjlRealName())
                .like(StringUtils.isNotBlank(accountQuery.getZjlEmail()), "a.Zjl_email", accountQuery.getZjlEmail());

        if (StringUtils.isNotBlank(accountQuery.getCreateTimeRange())) {
            //2024
            String[] times = accountQuery.getCreateTimeRange().split(" - ");
            System.out.println(Arrays.toString(times));

            query.ge("a.Zjl_create_time", times[0]).le("a.Zjl_create_time", times[1]);
        }

        query.eq("a.Zjl_deleted", 0).orderByDesc("a.Zjl_account_id");

        Page<zjlAccount> accountPage = new Page<>(accountQuery.getPage(), accountQuery.getLimit());
        IPage<zjlAccount> zjlAccounts = baseMapper.zjlAccountPage(accountPage, query);
        return ZjlRUtils.getCountRecords(zjlAccounts);

    }

    @Override
    public zjlAccount zjlAccountOne(Long id) {
        return baseMapper.zjlAccountOne(id);
    }
}
