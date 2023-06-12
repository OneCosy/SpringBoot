package com.bit.jsptest.model;

import lombok.Data;

@Data
public class FileInfoDto {
    private String saveFolder;
    private String originFile;  // 실제 파일명
    private String saveFile;
}
