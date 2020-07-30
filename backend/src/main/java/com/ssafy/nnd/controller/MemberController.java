package com.ssafy.nnd.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.BasicResponse;
import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.MemberBoard;
import com.ssafy.nnd.dto.SignupRequest;
import com.ssafy.nnd.repository.MemberRepository;

@CrossOrigin
@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

	@GetMapping("/member/login")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<Member> memberOpt = memberRepository.findMemberByEmailAndPassword(email, password);

		System.out.println("memberOpt: " + memberOpt.toString());

		ResponseEntity response = null;

		if (memberOpt.isPresent()) {
			System.out.println("suces");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else { //로그인 성공
			System.out.println("fail");
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			//추가 사항 비밀번호 아이디 중에 뭐가 틀렸는지 알려주기
		}
		return response;
	}
	
	
	@PostMapping("/signup")
	public Object signup(@Valid @RequestBody SignupRequest request) {
		Optional<Member> memberOpt = memberRepository.findMemberByEmail(request.getEmail());
		Member member= new Member(request.getName(),request.getEmail(), request.getPassword(),request.getProfile(),"null",request.getGitaddr(),request.getMemberstack());
		
		ResponseEntity response = null;

		if (memberOpt.isPresent()) { //  있는 회원입니다.
			System.out.println("exist!!!");
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("suces");
			memberRepository.save(member);
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return response;
	}
    
}


