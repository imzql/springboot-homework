package com.echo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class zjlRole extends zjlTimeEntity {
    /**
     * 主键
     */

    @TableId(value = "Zjl_role_id",type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    /**
     * 角色名称
     */
    @TableField("Zjl_role_name")
    private String roleName;
    /**
     * 备注
     */
    @TableField("Zjl_remark")
    private String remark;

    @TableField(exist = false)
    private List<Long> resourceIds;
}
