package com.book.bookms.pojo.vo;

import lombok.Data;

//用户密码修改实体类'
@Data
public class ChangePasswdVo {

    private String userName;

    private String olderPassword;

    private String newPassword;
}
