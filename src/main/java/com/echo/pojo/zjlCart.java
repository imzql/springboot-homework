package com.echo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class zjlCart {
    @TableId("cart_id")
    private int cartId;
    @TableField("user_id")
    private int userId;
    @TableField("book_id")
    private int bookId;
    @TableField("quantity")
    private int quantity;
}
