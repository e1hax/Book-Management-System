package com.book.bookms.controller.admin;


import com.book.bookms.common.R;
import com.book.bookms.dto.ReaderDto;
import com.book.bookms.mapper.ReaderMapper;
import com.book.bookms.mapper.UserMapper;
import com.book.bookms.pojo.Book;
import com.book.bookms.pojo.Reader;
import com.book.bookms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/admin/reader")
public class AdminReaderController {
    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有的读者信息
     *
     * @return
     */
    @GetMapping("/getAll")
    public R<List<Reader>> getAll() {
        List<Reader> readers = readerMapper.getAllReader();
        return R.success(readers);
    }

    /**
     * 分页获取所有的读者信息
     *
     * @return
     */
    @GetMapping("/page")
    public R<List<Reader>> findAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Reader> readers = readerMapper.selectPage(pageNum, pageSize);
        Integer total = readerMapper.selectTotal();
        return R.success(readers).add("total", total);
    }

    /**
     * 更新读者挂失信息
     *
     * @param readerDto
     * @return
     */
    @PutMapping("/updateGs")
    public R<String> updateGs(@RequestBody ReaderDto readerDto) {

        if (readerDto.getReaderId() != null && ("1".equals(readerDto.getStatus())
                || "0".equals(readerDto.getStatus()))) {
            readerMapper.checkReader_gs(readerDto.getReaderId(), readerDto.getStatus());
            userMapper.checkUser_gs(readerDto.getReaderId(), readerDto.getStatus());
            return R.success("状态更新成功");
        }
        return R.error("更新状态失败");
    }

    /**
     * 更新读者停借状态
     *
     * @param readerDto
     * @return
     */
    @PutMapping("/updateStatus")
    public R<String> updateStatus(@RequestBody ReaderDto readerDto) {
        if (readerDto.getReaderId() != null && ("1".equals(readerDto.getStatus())
                || "0".equals(readerDto.getStatus()))) {
            readerMapper.checkReader_status(readerDto.getReaderId(), readerDto.getStatus());
            userMapper.checkUser_status(readerDto.getReaderId(), readerDto.getStatus());
            return R.success("状态更新成功");
        }
        return R.error("更新状态失败");
    }
}