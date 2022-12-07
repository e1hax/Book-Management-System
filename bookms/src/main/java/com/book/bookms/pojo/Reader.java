package com.book.bookms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;
    //读者id
    private Integer readerId;
    //姓名
    private String name;
    //性别
    private String sex;
    //出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    //地址
    private String address;
    //电话
    private String telcode;
    //挂失状态
    private Integer gs;
    //用户状态
    private Integer status;
}
