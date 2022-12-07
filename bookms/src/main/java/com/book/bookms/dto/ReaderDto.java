package com.book.bookms.dto;

import lombok.Data;

/**
 * 用户挂失停借状态 类
 */
@Data
public class ReaderDto {
    private String readerId;
    private String status;
}
