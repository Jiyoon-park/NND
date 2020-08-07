package com.ssafy.nnd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
    Optional<Member> findMemberByEmailAndPassword(String email, String password);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findMemberByEmailAndCompany(String email, String company);
    Optional<Member> findMemberByIdx(long idx);
  //SELECT COUNT(*) FROM 테이블
  	@Query(value= " select count(*) from member where teamboardno = :tboardno",nativeQuery = true)
  	Long countTeamMemberByTeamboardNo(@Param("tboardno") Long tboardno);
  
}

