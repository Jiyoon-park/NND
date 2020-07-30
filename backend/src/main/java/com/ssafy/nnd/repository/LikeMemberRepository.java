package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.LikeMember;


public interface LikeMemberRepository extends JpaRepository<LikeMember,Long>{

//	Optional<LikeMember> findByMno(Long mno);
	List<LikeMember> findByMno(Long mno);
//	LikeMember deleteByMnoAndMboard(Long mno ,Long mboard);
}
