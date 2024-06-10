package com.echo.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlAccountMapper;
import com.echo.pojo.zjlAccount;
import com.echo.pojo.zjlLoginDTO;
import com.echo.service.ZjlLoginService;
import org.springframework.stereotype.Service;


@Service
public class ZjlLoginServiceImpl extends ServiceImpl<zjlAccountMapper, zjlAccount> implements ZjlLoginService {

    public zjlLoginDTO Login(String username,String password){

        zjlLoginDTO zjlLoginDTO = new zjlLoginDTO();

        //客户传来的用户名username  原数据库的Account表zjlAccount::getZjlUsername的用户名匹配，查看用户是否存在
        zjlAccount account = lambdaQuery().eq(zjlAccount::getZjlUsername, username).one();
        System.out.println(account);

        if (account == null){
            zjlLoginDTO.setError("用户名不存在,请先注册该用户");
            zjlLoginDTO.setPath("redirect:/");
            return zjlLoginDTO;
        }

        //将用户名查出来的数据进行转换，转换为字节数组
        MD5 md5 = new MD5(account.getZjlSalt().getBytes());
        System.out.println(md5);

        //将前端用户传来的密码 根据数据库里面的盐值进行转换
        String digestHex = md5.digestHex(password);
        System.out.println(digestHex);

        if (!digestHex.equals(account.getZjlPassword())){
            zjlLoginDTO.setError("密码错误.请检查您的密码是否正确哦");
            zjlLoginDTO.setPath("redirect:/");
            return zjlLoginDTO;
        }

        zjlLoginDTO.setZjlAccount(account);
        zjlLoginDTO.setPath("login/menu");
        return zjlLoginDTO;
    }
}
