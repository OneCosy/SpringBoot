package com.bit.jsptest.model.service;

import com.bit.jsptest.model.MemberDto;

import java.util.List;
import java.util.Map;

public interface MemberService {
    MemberDto login(Map<String, String> map) throws Exception;
    int idCheck(String checkId) throws Exception;
    void registerMember(MemberDto memberDto) throws Exception;
    List<MemberDto> listMember() throws Exception;
    MemberDto getMember(String userId) throws Exception;
    void updateMember(MemberDto memberDto) throws Exception;
    void deleteMember(String userId) throws Exception;
}
