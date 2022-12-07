package com.book.bookms.controller.user;

import com.book.bookms.common.R;
import com.book.bookms.mapper.ReaderMapper;
import com.book.bookms.pojo.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RestController
@RequestMapping("/user/reader")
public class UserReaderController {

    @Autowired
    private ReaderMapper readerMapper;

    /**
     * 查询读者个人信息
     * @param readerId
     * @return
     */
    @GetMapping("/readerInfo/{readerId}")
    public R<Reader> readerInfo(@PathVariable String readerId) {
        Reader reader = readerMapper.getReaderInfo(readerId);
        return R.success(reader);
    }


    /**
     * 更新读者个人信息
     * @param reader
     * @return
     */
    @PutMapping("/updateInfo")
    public R<String> updateInfo(@RequestBody Reader reader) {
        if (reader.getReaderId() == null) {
            return R.error("更新失败");
        }
        readerMapper.updateInfo(reader);
        return R.success("更新成功");
    }


}
