package com.echo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class zjlBooks {
    @TableId("book_id")
    private int bookId;
    @TableField("title")
    private String title;
    @TableField("author")
    private String author;
    @TableField("publication_year")
    private int publicationYear;
    @TableField("publisher")
    private String publisher;
    @TableField("isbn")
    private String isbn;
    @TableField("genre")
    private String genre;
    @TableField("quantity")
    private int quantity;
    @TableField("available_quantity")
    private int availableQuantity;
}
