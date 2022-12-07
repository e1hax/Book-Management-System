package com.book.bookms.mapper;

import com.book.bookms.pojo.Reader;
import com.book.bookms.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User getUserByUsername(String username);
    void changePassword(String username, String newPassword);
    String getPassword(String username);
    void addUser(User user);
    void checkUser_gs(@Param("username") String readerId,String status);
    void checkUser_status(@Param("username") String readerId,String status);
}
