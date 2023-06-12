package com.bit.jsptest.model.mapper;

import com.bit.jsptest.model.FileInfoDto;
import com.bit.jsptest.model.GuestBookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GuestBookMapper {
    void registerArticle(GuestBookDto guestBookDto) throws Exception;
    void registerFile(GuestBookDto guestBookDto) throws Exception;
    List<GuestBookDto> listArticle(Map<String, Object> map) throws Exception;
    int getTotalCount(Map<String, String> map) throws Exception;
    GuestBookDto getArticle(int articleNo) throws Exception;
    void updateArticle(GuestBookDto guestBookDto) throws Exception;
    void deleteFile(int articleNo) throws Exception;
    void deleteArticle(int articleNo) throws Exception;
    List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
}
