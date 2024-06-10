package com.echo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class zjlCustomer extends zjlTimeEntity{
        //主键
        @TableId(value = "Zjl_customer_id",type = IdType.AUTO)
        private Long zjlCustomerId;

        //真实姓名
        private String zjlRealName;

        //性别
        private String zjlSex;

        //年龄
        private Integer zjlAge;

        //邮箱
        private String zjlEmail;

        //手机号码
        private String zjlPhone;

        //地址
        private String zjlAddress;

    }
