package com.book.bookms.mapper;

import com.book.bookms.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    List<Book> getAllBook();
    Book getBookById(Long id);
    void updateBook(Book book);
    void deleteBook(long id);
    void addBook(Book book);
    List<Book> selectPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);
    Integer selectTotal(String keyword);
    Book getBookByIsbn(String isbn);
    List<Book> pageSearch(Integer pageNum,Integer pageSize,String keyword);
    void updateBookByBookId_0(Long bookId);
    void updateBookByBookId_1(Long bookId);
}
