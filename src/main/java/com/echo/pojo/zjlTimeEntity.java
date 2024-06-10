package com.echo.pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class zjlTimeEntity {
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime zjlCreateTime;
    //修改时间
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime zjlModifiedTime;
    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Long zjlCreateAccountId;
    //修改人
    @TableField(fill = FieldFill.UPDATE)
    private Long zjlModifiedAccountId;
    //逻辑删除标识 (0/否/1/是)
    @TableLogic
    private Integer zjlDeleted;
}
