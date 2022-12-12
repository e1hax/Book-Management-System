package com.book.bookms.dto;

import com.book.bookms.pojo.Reader;
import com.book.bookms.pojo.User;
import lombok.Data;

/**
 * 用户注册 类
 */
@Data
public class UserDto {
    private User user;
    private Reader reader;
}

