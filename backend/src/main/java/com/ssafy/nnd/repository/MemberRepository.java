package com.ssafy.nnd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByEmailAndPassword(String email, String password);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findByEmailAndCompany(String email, String company);

}

