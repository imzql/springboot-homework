package com.echo.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlAccountMapper;
import com.echo.pojo.zjlAccount;
import com.echo.pojo.zjlRole;
import com.echo.service.ZjlAcccouuntRoleNameService;
import com.echo.service.ZjlAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ZjlAccountRoleNameServiceImpl extends ServiceImpl<zjlAccountMapper,zjlAccount> implements ZjlAcccouuntRoleNameService {
    @Autowired
    private ZjlRoleServiceImpl roleService;

    //将前端传进来的密码根基随机生成的salt进行加密
    public void zjlGetpasswordSalt(@RequestBody zjlAccount zjlAccount) {
        String password = zjlAccount.getZjlPassword();
        String s = UUID.fastUUID().toString().replaceAll("-", "");
        MD5 md5 = new MD5(s.getBytes());
        String diggestHex = md5.digestHex(password);
        zjlAccount.setZjlPassword(diggestHex);
        zjlAccount.setZjlSalt(s);
    }

    /**
     * 获取role表的rolename来增加修改查看当前人属于什么样带的就角色,例如:财务总管 总监  技术经理
     * @param model
     */
    public void getRoleName(Model model) {
        LambdaQueryWrapper<zjlRole> Wrapper = Wrappers.<zjlRole>lambdaQuery();
        Wrapper.orderByDesc(zjlRole::getRoleId);
        List<zjlRole> roleList = roleService.list(Wrapper);
        model.addAttribute("roles", roleList);
    }
}
