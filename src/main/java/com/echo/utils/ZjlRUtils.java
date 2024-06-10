package com.echo.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.pojo.zjlCustomer;

import java.util.HashMap;

public class ZjlRUtils {

    //增加,修改,删除是否成功,返回给前端的信息 共性方法
    public static R<Object> rightOrnot(boolean sucess) {
        if (sucess){
            return R.ok(null);
        }else {
            return R.failed("操作失败");
        }
    }

    //分页查询返回给前端 数据表的数据条数及数据
    //泛型 所有类型 object  通配符 ? T 安全性比object高
    public static R<HashMap<String, Object>> getCountRecords(IPage<?> page) {
        //数据库一共有多少条数据
        HashMap<String , Object> data = new HashMap<>();
        //当前页面的数据 必须要有
        data.put("count", page.getTotal());
        data.put("records", page.getRecords());
        return R.ok(data);
    }

}
