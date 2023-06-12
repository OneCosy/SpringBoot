package com.bit.jsptest.controller;

import com.bit.jsptest.model.MemberDto;
import com.bit.jsptest.model.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//엔드포인트(Endpoint)는 웹 서비스에서 클라이언트가 요청을 보낼 수 있는 특정 URL을 의미합니다.
//엔드포인트는 RESTful 웹 서비스에서 각각의 리소스에 대한 접근을 제공하고, 해당 리소스와 관련된 동작을 수행합니다.
//일반적으로 RESTful 웹 서비스에서는 다음과 같은 HTTP 메서드와 URL 패턴으로 구성된 엔드포인트를 사용합니다:
//GET: 리소스의 조회 또는 검색을 위한 메서드입니다. 주로 데이터의 읽기 작업에 사용됩니다.
//예시: GET /api/users (모든 사용자 정보 조회)
//GET /api/users/{id} (특정 사용자 정보 조회)
//POST: 리소스의 생성을 위한 메서드입니다. 주로 데이터의 생성 또는 추가 작업에 사용됩니다.
//예시: POST /api/users (새로운 사용자 생성)
//PUT: 리소스의 전체적인 변경을 위한 메서드입니다. 주로 데이터의 수정 작업에 사용됩니다.
//예시: PUT /api/users/{id} (특정 사용자 정보 수정)
//PATCH: 리소스의 부분적인 변경을 위한 메서드입니다. 주로 데이터의 일부 속성 수정 작업에 사용됩니다.
//예시: PATCH /api/users/{id} (특정 사용자의 일부 정보 수정)
//DELETE: 리소스의 삭제를 위한 메서드입니다. 주로 데이터의 삭제 작업에 사용됩니다.
//예시: DELETE /api/users/{id} (특정 사용자 삭제)

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/user")
    public ResponseEntity<?> userList() {
        try {
            List<MemberDto> list = memberService.listMember();
            if(list != null && !list.isEmpty()) {
                return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//				return new ResponseEntity<List<MemberDto>>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }
    @PostMapping(value = "/user")
    public ResponseEntity<?> userRegister(@RequestBody MemberDto memberDto) {
        try {
            memberService.registerMember(memberDto);
            List<MemberDto> list = memberService.listMember();
            return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
//			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }


    @GetMapping(value = "/user/{userid}")
    public ResponseEntity<?> userInfo(@PathVariable("userid") String userid) {
        try {
            logger.debug("파라미터 : {}", userid);
            MemberDto memberDto = memberService.getMember(userid);
            if(memberDto != null)
                return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
            else
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    @PutMapping(value = "/user")
    public ResponseEntity<?> userModify(@RequestBody MemberDto memberDto) {
        try {
            memberService.updateMember(memberDto);
            List<MemberDto> list = memberService.listMember();
            return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> userDelete(@PathVariable("userid") String userid) {
        try {
            memberService.deleteMember(userid);
            List<MemberDto> list = memberService.listMember();
            return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
