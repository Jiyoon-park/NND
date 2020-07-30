package com.ssafy.nnd.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.util.KakaoAPI;


@CrossOrigin
@Controller
public class HomeController {
 
	private String name;
	private String email;
	private String company;
	private String profile;
	
    @Autowired
    private KakaoAPI kakao;
    
    @RequestMapping(value="/")
    public String index() {
        
        return "index";
    }
    
    @RequestMapping(value="/login")
    public String login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakao.getAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);
        Member mem = new Member();
        mem.setEmail((String)userInfo.get("email"));
        mem.setName((String)userInfo.get("nickname"));
        mem.setProfile((String)userInfo.get("profile"));
        mem.setCompany((String)userInfo.get("company"));
        name= mem.getName();
        email=mem.getEmail();
        profile=mem.getProfile();
        company=mem.getCompany();
        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
//            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("member", mem);
            session.setAttribute("access_Token", access_Token);
        }
//        System.out.println("로그인중"+mem.toString());
        return "redirect:http://localhost:8081/";
    }

   
    @GetMapping(value="/userinfo")
    public @ResponseBody Member returnUserinfo(HttpSession session) throws Exception{
    	Member member = new Member();
    	member.setEmail(email);
    	member.setName(name);
    	member.setCompany(company);
    	member.setProfile(profile);
//    	System.out.println(member.toString());
    	return member;
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
    	System.out.println("로그아웃에는 세션이 있는지 확인 "+session.getAttribute("member"));
        kakao.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        return "index";
    }

}

