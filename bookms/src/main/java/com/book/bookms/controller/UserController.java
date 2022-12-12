package com.book.bookms.controller;

import com.book.bookms.common.R;
import com.book.bookms.mapper.UserMapper;
import com.book.bookms.pojo.User;
import com.book.bookms.pojo.vo.ChangePasswdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PutMapping("/changePassword")
    public R<String> changePassword(@RequestBody ChangePasswdVo changePasswdVo)  {
        //根据用户名查询数据库
        User user = userMapper.getUserByUsername(changePasswdVo.getUserName());
        //判断user是否为空 或者 旧密码不等于数据库中该用户的密码
        String olderPassword = DigestUtils.md5DigestAsHex((changePasswdVo.getOlderPassword()).getBytes());
        if (user==null) {
            return R.error("修改失败");
        }
        if(!(olderPassword.equals(user.getPassword()))){
            return R.error("旧密码有误");
        }
        //修改密码
        userMapper.changePassword(changePasswdVo.getUserName(),
                DigestUtils.md5DigestAsHex((changePasswdVo.getNewPassword().getBytes())));
        return R.success("密码修改成功");
    }

}
