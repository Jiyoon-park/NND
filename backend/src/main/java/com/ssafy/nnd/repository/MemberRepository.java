package com.ssafy.nnd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
    Optional<Member> findMemberByEmailAndPassword(String email, String password);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findByEmailAndCompany(String email, String company);
    Optional<Member> findMemberByIdx(long idx);
}

