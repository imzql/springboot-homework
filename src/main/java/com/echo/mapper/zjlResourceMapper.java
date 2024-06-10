package com.echo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.echo.pojo.zjlModelView;
import com.echo.pojo.zjlResource;
import com.echo.pojo.zjlResourceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface zjlResourceMapper extends BaseMapper<zjlResource> {

  List<zjlResourceVo> zjlResourceMenu(@Param(Constants.WRAPPER) Wrapper<zjlResource> wrapper);

  //根据角色id查询出角色可以查看的模块,这里使用到了三个表
  List<zjlModelView> updateModels(@Param(Constants.WRAPPER) Wrapper<zjlResource> wrapper,
                                  @Param("roleId") Long roleId);
}
