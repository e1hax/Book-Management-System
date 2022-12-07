package com.book.bookms.controller.user;

import com.book.bookms.common.R;
import com.book.bookms.mapper.BookMapper;
import com.book.bookms.pojo.Book;
import com.book.bookms.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RestController
@RequestMapping("/user/book")
public class UserBookController {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 分页查询图书信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<List<Book>> findAll(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        pageNum = (pageNum-1)*pageSize;
        List<Book> books = bookMapper.selectPage(pageNum, pageSize);
        Integer total = bookMapper.selectTotal("");
        return R.success(books).add("total",total);
    }

    /**
     * 根据id获取图书详情
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public R<Book> info(@PathVariable Long id){
        Book book = bookMapper.getBookById(id);
        return R.success(book);
    }

    /**
     * 根据关键字搜索
     * @param keyword
     * @return
     */
    @GetMapping("/pageSearch")
    public R<List<Book>> pageSearch(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false,defaultValue = "5") Integer pageSize,
                                    @RequestParam(required = false,defaultValue = "") String keyword) {
        pageNum = (pageNum - 1) * pageSize;
        List<Book> books = bookMapper.pageSearch(pageNum, pageSize, keyword);
        Integer total = bookMapper.selectTotal(keyword);
        return R.success(books).add("total", total);

    }

}
