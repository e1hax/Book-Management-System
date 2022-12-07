package com.book.bookms.mapper;

import com.book.bookms.pojo.Book;
import com.book.bookms.pojo.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReaderMapper {
    List<Reader> getAllReader();
    String getReaderName(String readerId);
    Reader getReaderInfo(String readerId);
    void updateInfo(Reader reader);
    List<Reader> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    Integer selectTotal();
    void addReader(Reader reader);
    void checkReader_gs(String readerId, String status);
    void checkReader_status(String readerId,String status);
}
