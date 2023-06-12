package com.bit.jsptest.model;

import lombok.Data;


@Data
public class MemberDto {
    private String userName;
    private String userId;
    private String userPwd;
    private String email;
    private String joinDate;


}
