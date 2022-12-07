package com.book.bookms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    //用户id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //用户角色
    private String role;
    //挂失
    private Integer gs;
    //用户状态
    private Integer status;

}
