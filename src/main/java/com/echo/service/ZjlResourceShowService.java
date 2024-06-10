package com.echo.service;

import com.echo.pojo.zjlModelView;

import java.util.List;

public interface ZjlResourceShowService {
    List<zjlModelView> showModel();

    List<zjlModelView> lookUpdateModels(Long roleId);

}
