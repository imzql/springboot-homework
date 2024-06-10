package com.echo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;


@Data
public class zjlAccount extends zjlTimeEntity{
    //account 表的id值
    @TableId(value = "Zjl_account_id",type = IdType.AUTO)
    private Long zjlAccountId;
    //角色权限
    private Long zjlRoleId;
    //角色名称
    @TableField(exist = false)
    private String zjlroleName;
    //用户名
    private String zjlUsername;
    //密码
    private String zjlPassword;
    //盐值
    private String zjlSalt;
    //真实姓名
    private String zjlRealName;
    //性别
    private String zjlSex;
    //邮箱
    private String zjlEmail;



}
