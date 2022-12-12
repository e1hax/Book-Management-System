package com.book.bookms.controller.admin;

import com.book.bookms.common.R;
import com.book.bookms.mapper.RecordMapper;
import com.book.bookms.pojo.Reader;
import com.book.bookms.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/admin/record")
public class AdminRecordController {
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 获取所有借还记录
     * @return
     */
    @GetMapping("/getAll")
    public R<List<Record>> getAll(){
        List<Record> records = recordMapper.getAllRecord();
        return R.success(records);
    }


    /**
     * 分页获取所有借阅记录
     * @return
     */
    @GetMapping("/page")
    public R<List<Record>> findAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum = (pageNum-1)*pageSize;
        List<Record> records = recordMapper.selectPage(pageNum, pageSize);
        Integer total = recordMapper.selectTotal();
        return R.success(records).add("total",total);
    }
}
