package com.book.bookms.controller.user;

import com.book.bookms.common.R;
import com.book.bookms.mapper.BookMapper;
import com.book.bookms.mapper.RecordMapper;
import com.book.bookms.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RestController
@RequestMapping("/user/record")
public class UserRecordController {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private BookMapper bookMapper;

    /**
     * 获取当前用户所有借还记录
     * @return
     */
    @GetMapping("/recordPage")
    public R<List<Record>> recordPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String readerId){
        pageNum = (pageNum-1)*pageSize;
        List<Record> records = recordMapper.selectReaderRecord(pageNum, pageSize,readerId);
        Integer total = recordMapper.selectTotal();
        return R.success(records).add("total",total);
    }


}
