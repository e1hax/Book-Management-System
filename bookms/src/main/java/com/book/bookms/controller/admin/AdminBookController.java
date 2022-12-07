package com.book.bookms.controller.admin;

import com.book.bookms.common.R;
import com.book.bookms.mapper.BookMapper;
import com.book.bookms.mapper.RecordMapper;
import com.book.bookms.pojo.Book;
import com.book.bookms.pojo.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@RestController
@RequestMapping("/admin/book")
public class AdminBookController {
    @Autowired
    private BookMapper  bookMapper;

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 获取所有图书信息
     * @return
     */
    @GetMapping("/getAll")
    public R<List<Book>> getAll(){
        List<Book> books = bookMapper.getAllBook();
        return R.success(books);
    }

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
     * 图书编辑详情
     * @param id
     * @return
     */
    @GetMapping("/editInfo/{id}")
    public R<Book> editInfo(@PathVariable Long id){
        Book book = bookMapper.getBookById(id);
        return R.success(book);
    }

    /**
     * 图书信息更新
     * @param book
     * @return
     */
    @PutMapping("/updateBook")
    public R<String> updateBook(@RequestBody Book book){
        if (bookMapper.getBookById(book.getBookId())==null) {
            return R.error("修改失败");
        }
        bookMapper.updateBook(book);
        return R.success("修改成功");
    }

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable Long id){
        bookMapper.deleteBook(id);
        return R.success("删除成功");
    }

    /**
     * 添加图书信息
     * @param book
     * @return
     */
    @PostMapping("/add")
    public R<String> add(@RequestBody Book book){
        //根据isbn判断添加的图书是否存在
        Book check_book = bookMapper.getBookByIsbn(book.getIsbn());
        if (!(check_book == null)) {
            return R.error("添加失败，该书已存在");
        }
        //添加图书
        bookMapper.addBook(book);
        return R.success("添加成功");
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


    // 借书 传参  readerId 和 bookId
    // 插入 lend_list   借书记录
    // 更新book_info  库存状态

    /**
     * 借书
     * @param record
     * @return
     */
    @PostMapping("/borrowBook")
    public R<String> borrowBook(@RequestBody Record record){
        if (bookMapper.getBookById(record.getBookId()).getState()==0) {
            return R.error("该书缺货");
        }
        //添加借阅记录
        recordMapper.brownBook(record);
        //给图书状态改为0
        bookMapper.updateBookByBookId_0(record.getBookId());
        return R.success("借书成功");
    }

    /**
     * 还书
     * @param record
     * @return
     */
    @PutMapping("/returnBook")
    public R<String> returnBook(@RequestBody Record record){
        if (record.getReaderId() == null) {
            return R.error("还书失败");
        }
        //删除借阅记录
        recordMapper.returnBook(record);
        //给图书状态改为0
        bookMapper.updateBookByBookId_1(record.getBookId());
        return R.success("还书成功");
    }

}
