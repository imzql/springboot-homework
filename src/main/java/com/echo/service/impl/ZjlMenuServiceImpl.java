package com.echo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlResourceMapper;
import com.echo.pojo.zjlResource;
import com.echo.pojo.zjlResourceVo;
import com.echo.service.ZjlMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ZjlMenuServiceImpl extends ServiceImpl<zjlResourceMapper, zjlResource> implements ZjlMenuService {

    @Override
    public List<zjlResourceVo> modelCanLookByRoleId(Long roleId) {
        QueryWrapper<zjlResource> query = Wrappers.query();
        //客户登录的时候,传进来他拥有的角色,设计部的经理这样的角色
        //查出一级目录
        query.eq("rr.Zjl_role_id",roleId).isNull("re.Zjl_parent_id");
        List<zjlResourceVo> zjlResourceVos = baseMapper.zjlResourceMenu(query);
//        System.out.println("一级目录集合"+zjlResourceVos);


        zjlResourceVos.forEach(zjlResourceVo -> {
//            System.out.println("一级目录单条数据"+zjlResourceVo);
            Long zjlResourceId = zjlResourceVo.getZjlResourceId();
//            System.out.println(zjlResourceId);
            QueryWrapper<zjlResource> subquery = Wrappers.query();
            subquery.eq("rr.Zjl_role_id",roleId).eq("re.Zjl_parent_id",zjlResourceId);
            List<zjlResourceVo> subResourceVos = baseMapper.zjlResourceMenu(subquery);
            //判断是否有子目录,有的话，装起来
            if (CollectionUtils.isNotEmpty(subResourceVos)){
                zjlResourceVo.setSubs(subResourceVos);
            }
        });

        return zjlResourceVos;
    }
}
