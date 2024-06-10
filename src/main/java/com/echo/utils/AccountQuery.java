package com.echo.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountQuery {
    //真实姓名
    private String zjlRealName;

    //邮箱
    private String zjlEmail;

    //创建时间
    private String createTimeRange;

    private Long page;

    private Long limit;
}
