package com.bit.jsptest.model.service;

import com.bit.jsptest.model.MemberDto;
import com.bit.jsptest.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImp implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDto login(Map<String, String> map) throws Exception {
        return memberMapper.login(map);
    }

    @Override
    public int idCheck(String checkId) throws Exception {
        return memberMapper.idCheck(checkId); // 0 or 1
    }

    @Override
    public void registerMember(MemberDto memberDto) throws Exception {
        memberMapper.registerMember(memberDto);
    }

    @Override
    public List<MemberDto> listMember() throws Exception {
        return memberMapper.listMember();
    }

    @Override
    public MemberDto getMember(String userId) throws Exception {
        return memberMapper.getMember(userId);
    }

    @Override
    public void updateMember(MemberDto memberDto) throws Exception {
        memberMapper.updateMember(memberDto);
    }

    @Override
    public void deleteMember(String userId) throws Exception {
        memberMapper.deleteMember(userId);
    }
}
