package com.echo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlRoleMapper;
import com.echo.pojo.zjlRole;
import com.echo.pojo.zjlRoleResource;
import com.echo.service.ZjlRoleService;
import com.echo.mapper.zjlRoleResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZjlRoleServiceImpl extends ServiceImpl<zjlRoleMapper, zjlRole> implements ZjlRoleService {

    @Autowired
    private zjlRoleResourceMapper zjlRoleResourceMapper;


    //新增角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(zjlRole zjlRole) {
        //this就是代表此类  代指ZjlRoleServiceImpl 所以this.save()就是mybatis给我们提供的新增
        this.save(zjlRole);
        Long roleId = zjlRole.getRoleId();
        List<Long> resourceIds = zjlRole.getResourceIds();

        if (CollectionUtils.isNotEmpty(resourceIds)){
            for (Long resourceId:resourceIds){
                //新增roleResource 这个表
                zjlRoleResource roleResource = new zjlRoleResource();
                roleResource.setZjlRoleId(roleId);
                roleResource.setZjlResourceId(resourceId);
                zjlRoleResourceMapper.insert(roleResource);

            }
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(zjlRole zjlRole) {
        this.updateById(zjlRole);

        Long roleId = zjlRole.getRoleId();
        List<Long> resourceIds = zjlRole.getResourceIds();

        LambdaQueryWrapper<zjlRoleResource> Wrapper = Wrappers.lambdaQuery();
        Wrapper.eq(zjlRoleResource::getZjlRoleId,roleId);
        zjlRoleResourceMapper.delete(Wrapper);

        if (CollectionUtils.isNotEmpty(resourceIds)){
            for (Long resourceId:resourceIds){
                //新增roleResource 这个表
                zjlRoleResource roleResource = new zjlRoleResource();
                roleResource.setZjlRoleId(roleId);
                roleResource.setZjlResourceId(resourceId);
                zjlRoleResourceMapper.insert(roleResource);

            }
        }

        return true;
    }


}
