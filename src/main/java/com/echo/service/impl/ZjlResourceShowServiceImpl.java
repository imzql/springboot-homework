package com.echo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.mapper.zjlResourceMapper;
import com.echo.pojo.zjlModelView;
import com.echo.pojo.zjlResource;
import com.echo.service.ZjlResourceShowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZjlResourceShowServiceImpl extends ServiceImpl<zjlResourceMapper, zjlResource> implements ZjlResourceShowService {

    //展示模块,对模块进行选择 例如 选择某人查看 用户模块 商品模块
    @Override
    public List<zjlModelView> showModel(){
        LambdaQueryWrapper<zjlResource> Wrapper = Wrappers.lambdaQuery();

        Wrapper.isNull(zjlResource::getZjlParentId).orderByAsc(zjlResource::getZjlSort);

        List<zjlResource> Resources = this.list(Wrapper);

        List<zjlModelView> ResourceModels = Resources.stream().map(Resource -> {

            zjlModelView zjlModelView = new zjlModelView();
            zjlModelView.setId(Resource.getZjlResourceId());
            zjlModelView.setTitle(Resource.getZjlResourceName());

            LambdaQueryWrapper<zjlResource> wrapper2 = Wrappers.lambdaQuery();
            //一级目录下面对应的子目录
            wrapper2.eq(zjlResource::getZjlParentId, Resource.getZjlResourceId());
            List<zjlResource> subs = this.list(wrapper2);
//            System.out.println(subs);

            if (CollectionUtils.isNotEmpty(subs)) {
                //打印出来的ResultSubs 就是一级目录下的子目录集合
                List<zjlModelView> ResultSubs = subs.stream().map(sub -> {
                    zjlModelView zjlModelView1 = new zjlModelView();
                    zjlModelView1.setId(sub.getZjlResourceId());
                    zjlModelView1.setTitle(sub.getZjlResourceName());
                    return zjlModelView1;
                }).collect(Collectors.toList());
                zjlModelView.setChildren(ResultSubs);
            }

            return zjlModelView;
        }).collect(Collectors.toList());
        return ResourceModels;
    }

    //查看选中改动数据本身拥有的模块信息
    @Override
    public List<zjlModelView> lookUpdateModels(Long roleId){
        QueryWrapper<zjlResource> wrapper = Wrappers.query();
        //查询该用户可以看到的一级目录
        wrapper.isNull("re.Zjl_parent_id").orderByAsc("re.Zjl_sort");
        List<zjlModelView> zjlModelViews = baseMapper.updateModels(wrapper, roleId);

        zjlModelViews.forEach(zjlModelView ->{
            //清空当前角色所拥有的所有模块 让该角色选择要的模块
            zjlModelView.setChecked(false);
            Long id = zjlModelView.getId();
            QueryWrapper<zjlResource> subWrapper = Wrappers.query();
            //将一级目录的id值作为parent_id来查看它的二级目录
            subWrapper.eq("re.Zjl_parent_id",id).orderByAsc("re.Zjl_sort");
            List<zjlModelView> submodelVos = baseMapper.updateModels(subWrapper, roleId);

            if (CollectionUtils.isNotEmpty(submodelVos)){
                zjlModelView.setChildren(submodelVos);
            }

        });
        return zjlModelViews;
    }

}
