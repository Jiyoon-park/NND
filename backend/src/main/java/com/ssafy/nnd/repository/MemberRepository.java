package com.ssafy.nnd.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.ProjectHistory;
import com.ssafy.nnd.dto.TeamBoard;


public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
    Optional<Member> findMemberByEmailAndPassword(String email, String password);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findMemberByEmailAndCompany(String email, String company);
    Optional<Member> findMemberByIdx(long idx);
    
    @Query(value="SELECT profile, name FROM member where email = :email", nativeQuery=true)
    Optional<Object> findProfileLinkByEmail(String email);
    
    @Query(value="SELECT email FROM member",nativeQuery=true)
	List<String> findAllMemberEmail();
}

