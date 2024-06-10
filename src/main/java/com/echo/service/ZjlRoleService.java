package com.echo.service;

import com.echo.pojo.zjlRole;

import javax.management.relation.Role;

public interface ZjlRoleService {
    //新增角色以及角色可以查看的模块
    boolean addRole(zjlRole zjlRole);

    //修改角色以及修改角色对应的模块
    boolean updateRole(zjlRole zjlRole);

}
