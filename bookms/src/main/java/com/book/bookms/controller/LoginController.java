package com.book.bookms.controller;

import com.book.bookms.common.R;
import com.book.bookms.dto.UserDto;
import com.book.bookms.mapper.ReaderMapper;
import com.book.bookms.mapper.UserMapper;
import com.book.bookms.pojo.Reader;
import com.book.bookms.pojo.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReaderMapper readerMapper;

    @PostMapping("/login")
    public R<User> login(@RequestBody User user) {
        //如果传过来的对象为空则不作处理
        if (user == null) {
            return R.error("重新输入");
        }
        //对传输过来的密码进行MD5加密
        String password = DigestUtils.md5DigestAsHex((user.getPassword()).getBytes());

        //根据前端传入的user来查询数据库是否存在该用户
        User res_user = userMapper.getUserByUsername(user.getUsername());
        if (res_user == null) {
            return R.error("用户不存在");
        }
        //判断前端传过来的密码是否正确
        if (!(password.equals(res_user.getPassword()))) {
            return R.error("密码错误");
        }

        //判断用户的状态
        if (res_user.getGs() == 0 || res_user.getStatus()==0) {
            return R.error("用户异常，联系管理员");
        }
        //返回登录成功
        return R.success(res_user);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody UserDto userDto) {
        //如果传过来的对象为空则不作处理
        if (userDto == null) {
            return R.error("重新输入");
        }

        User user = userDto.getUser();
        Reader reader = userDto.getReader();

        //检查用户名是否存在
        if(!(userMapper.getUserByUsername(user.getUsername()) == null)){
            return R.error("用户已存在");
        }

        //对传输过来的密码进行MD5加密
        String password = DigestUtils.md5DigestAsHex((user.getPassword()).getBytes());
        user.setPassword(password);
        reader.setReaderId(Integer.valueOf(user.getUsername()));

        userMapper.addUser(user);
        readerMapper.addReader(reader);

        return R.success("注册成功");

    }
}
