package com.echo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class zjlRoleResource {
    //主键
    private Long zjlRoleResourceId;
    //角色id
    private Long zjlRoleId;
    //资源id
    private Long zjlResourceId;
}
