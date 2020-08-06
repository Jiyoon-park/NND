package com.ssafy.nnd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.BasicResponse;
import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.service.JwtService;
import com.ssafy.nnd.util.KakaoAPI;

@CrossOrigin
@Controller
public class HomeController {
	
	@Autowired
    MemberRepository memberRepository;
 
	private Member tmpMember;
	
	@Autowired
	private JwtService jwtService;
	
    @Autowired
    private KakaoAPI kakao;
    
//    @RequestMapping(value="/")
//    public String index() {
//        
//        return "index";
//    }
//    @GetMapping("/info/{id}")
//	public Object getuserinfo(@PathVariable long id) {
//		Optional<Member> member = memberRepository.findById(id);
//
//		return member.get();
//	}
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
        
        
        // db에 넣기
        Optional<Member> member = memberRepository.findByEmailAndCompany(mem.getEmail(), mem.getCompany());
        if (member.equals(Optional.empty())) { // 신규 유저일때
        	memberRepository.save(mem);	// db에 등록
		} else {
			mem.setIdx(member.get().getIdx());
		}
        
        tmpMember = mem;
        return "redirect:http://localhost:8081/";
    }

//  
    @ResponseBody 
    @RequestMapping(value="/userinfo")
    public Object getuserinfo() {
		ResponseEntity response = null;
		String token = jwtService.create("member", tmpMember, "user");
		final BasicResponse result = new BasicResponse();
		result.object = tmpMember;
		result.status = true;
		result.data = token;
		response = new ResponseEntity<>(result, HttpStatus.OK);
    	return response;
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

