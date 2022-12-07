package com.book.bookms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;

    //流水号
    private long sernum;
    //图书id
    private long bookId;
    //读者id
    private Integer readerId;
    //借出时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lendDate;
    //归还时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date backDate;
}
