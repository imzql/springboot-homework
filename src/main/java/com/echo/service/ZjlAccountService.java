package com.echo.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.echo.pojo.zjlAccount;
import com.echo.utils.AccountQuery;

import java.util.HashMap;

public interface ZjlAccountService {

    //interface 就是接口 目的就是为了规范代码
    R<HashMap<String, Object>> zjlPageQuery(AccountQuery accountQuery);

    zjlAccount zjlAccountOne(Long id);

}
