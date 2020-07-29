package com.ssafy.nnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.LikeTeam;
import com.ssafy.nnd.dto.TeamBoard;



public interface LikeTeamRepository extends JpaRepository<LikeTeam,Long>{

//	TeamBoard findByTeamboardno(Long teamboardno);
//	TeamBoard findByMember(Member member);
	
}
