package com.echo.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.pojo.zjlAccount;
import com.echo.pojo.zjlCustomer;
import com.echo.pojo.zjlRole;
import com.echo.service.ZjlAccountService;
import com.echo.service.impl.ZjlAccountRoleNameServiceImpl;
import com.echo.service.impl.ZjlAccountServiceImpl;
import com.echo.service.impl.ZjlRoleServiceImpl;
import com.echo.utils.AccountQuery;
import com.echo.utils.ZjlRUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("account")
public class ZjlAccountController {

    @Autowired
    private ZjlAccountServiceImpl accountService;

    @Autowired
    private ZjlAccountRoleNameServiceImpl zjlAccountRoleNameService;

    //如果是返回信息给前端人员使用
    @RequestMapping("toList")
    public String toCustomerModel() {
        return "account/accountList";
    }

    @RequestMapping("list")
    @ResponseBody
    public R<HashMap<String, Object>> customerList(AccountQuery accountQuery) {
        System.out.println(accountQuery.getCreateTimeRange());
        return accountService.zjlPageQuery(accountQuery);
    }

    @RequestMapping("toAdd")
    public String toAccountAdd(Model model) {
        zjlAccountRoleNameService.getRoleName(model);
        return "account/accountAdd";
    }

    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody zjlAccount zjlAccount) {
        zjlAccountRoleNameService.zjlGetpasswordSalt(zjlAccount);
        boolean sucess = accountService.save(zjlAccount);
        return ZjlRUtils.rightOrnot(sucess);
    }

    @GetMapping("toUpdate/{id}")
    public String toAccountUpdate(@PathVariable Long id, Model model) {
        zjlAccountRoleNameService.getRoleName(model);
        //根据id查询账号的信息
        zjlAccount account = accountService.getById(id);
        model.addAttribute("account", account);
        return "account/accountUpdate";
    }



    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody zjlAccount zjlAccount) {
        if (StringUtils.isNotBlank(zjlAccount.getZjlPassword())){
            zjlAccountRoleNameService.zjlGetpasswordSalt(zjlAccount);
        }else {
            zjlAccount.setZjlPassword(null);
        }
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        boolean sucess = accountService.updateById(zjlAccount);
        return ZjlRUtils.rightOrnot(sucess);
    }

    @GetMapping("toDetail/{id}")
    public String toCustomerDetail(@PathVariable Long id, Model model){
        //根据id查询account和role表生成的新表的信息客户的信息
        zjlAccount zjlAccount = accountService.zjlAccountOne(id);
        model.addAttribute("account",zjlAccount);
        return "account/accountDetail";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id, HttpSession session){
        //将共性的功能抽取成方法的快捷键
        //ctrl alt M
        //获取当前登录人的信息
        zjlAccount account = (zjlAccount)session.getAttribute("account");
        //判断你要删除的账号id是否与现在登录账号的一致
        if (account.getZjlAccountId().equals(id)){
            return  R.failed("你现在正在登录这个账号,无法删除该账号");
        }

        boolean sucess = accountService.removeById(id);
        return ZjlRUtils.rightOrnot(sucess);
    }



    //账号用户名检验是否重名
    @GetMapping({"/{username}","/{username}/{accountId}"})
    @ResponseBody
    public R<Object> checkUsername(@PathVariable String username,@PathVariable(required = false) Long accountId){
        LambdaQueryChainWrapper<zjlAccount> Wrapper = accountService.lambdaQuery();
        Integer count = Wrapper.eq(zjlAccount::getZjlUsername, username)
                .ne(accountId != null,zjlAccount::getZjlAccountId,accountId)
                .count();
        return R.ok(count);
    }

}
