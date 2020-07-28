package com.ssafy.nnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
//	Member findByEmailAndCompany(String email, String company);
	Member findByEmail(String email);
}

