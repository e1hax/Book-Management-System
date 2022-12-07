package com.book.bookms.mapper;

import com.book.bookms.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    List<Record> getAllRecord();
    List<Record> selectReaderRecord(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize,String readerId);
    List<Record> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    Integer selectTotal();
    void brownBook(Record record);
    void returnBook(Record record);
}
