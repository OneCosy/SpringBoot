package com.bit.jsptest.controller;

import com.bit.jsptest.model.MemberDto;
import com.bit.jsptest.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String login() throws Exception {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
                        HttpServletResponse response) throws Exception {
        logger.debug("map : {}", map.get("userId"));
        MemberDto memberDto = memberService.login(map);
        if (memberDto != null) {
            session.setAttribute("userinfo", memberDto);

            Cookie cookie = new Cookie("bit_id", map.get("userId"));
            cookie.setPath("/");
            if ("saveok".equals(map.get("idsave"))) {
                cookie.setMaxAge(60);
            } else {
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
            return "user/login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "user/join";
    }

    @PostMapping("/register")// 회원가입 등록
    public String register(MemberDto memberDto, Model model) throws Exception {
        logger.debug("memberDto info : {}", memberDto);
        memberService.registerMember(memberDto);
        return "redirect:/user/login";
    }

    @GetMapping("/idcheck")
    //	@ResponseBody id 존재여부 확인
    public @ResponseBody String idCheck(@RequestParam("ckid") String checkId) throws Exception {
        int idCount = memberService.idCheck(checkId);
        JSONObject json = new JSONObject();
        json.put("idcount", idCount);
        return json.toString();
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list() {
        return "user/list";
    }
}