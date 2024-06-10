package com.echo.pojo;


import lombok.Data;

import java.util.List;

@Data
public class zjlResourceVo {
    private Long zjlResourceId;

    private String zjlResourceName;

    private String zjlUrl;

    private List<zjlResourceVo> subs;
}
