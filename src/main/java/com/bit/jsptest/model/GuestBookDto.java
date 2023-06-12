package com.bit.jsptest.model;

import lombok.Data;

import java.util.List;

@Data
public class GuestBookDto {
    private int articleNo;
    private String userId;
    private String userName;
    private String subject;
    private String content;
    private String regTime;
    private List<FileInfoDto> fileInfos;
}
