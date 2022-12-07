package com.book.bookms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    //图书ID
    private long bookId;
    //图书名称
    private String name;
    //作者
    private String author;
    //出版社
    private String publish;
    //ISBN
    private String isbn;
    //简介
    private String introduction;
    //语言
    private String language;
    //价格
    private BigDecimal price;
    //出版日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubdate;
    //分类号
    private int classId;
    //书架号
    private int pressmark;
    //是否在馆 0 1
    private int state;


}
