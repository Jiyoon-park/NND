package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.LikeTeam;



public interface LikeTeamRepository extends JpaRepository<LikeTeam,Long>{

//	Optional<LikeTeam> findByMno(Long mno);
	List<LikeTeam> findByMno(Long mno);
//	LikeTeam deleteByLikeno(Long likeno);
	
}
