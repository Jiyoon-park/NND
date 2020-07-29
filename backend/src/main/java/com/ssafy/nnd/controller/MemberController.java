package com.ssafy.nnd.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.MemberBoard;
import com.ssafy.nnd.dto.SignupRequest;
import com.ssafy.nnd.repository.MemberRepository;

@CrossOrigin
@RestController
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

	@GetMapping("/account/login")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<Member> memberOpt = memberRepository.findMemberByEmailAndPassword(email, password);

		System.out.println("memberOpt: " + memberOpt.toString());


		if (memberOpt.isPresent()) {
	        return "redirect:http://localhost:8081/";
		} else { //로그인 성공
	        return "redirect:http://localhost:8081/home";
		}

	}
	
	@PostMapping("/account/signup")
	public Object signup(@Valid @RequestBody SignupRequest request) {
		Optional<Member> memberOpt = memberRepository.findMemberByEmail(request.getEmail());
		Member member= new Member(request.getName(),request.getEmail(), request.getPassword(),request.getProfile(),request.getCompany());
		
		if (memberOpt.isPresent()) { //  있는 회원입니다.
			System.out.println("exist!!!");
	        return "redirect:http://localhost:8081/";
		} else {
			System.out.println(request.getEmail() + " "+ request.getPassword());
			memberRepository.save(member);
	        return "redirect:http://localhost:8081/";
		}
	}
    
}


