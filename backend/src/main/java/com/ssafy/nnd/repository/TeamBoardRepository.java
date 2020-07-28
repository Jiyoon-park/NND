package com.ssafy.nnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.TeamBoard;



public interface TeamBoardRepository extends JpaRepository<TeamBoard,Long>{

//	TeamBoard findByTeamboardno(Long teamboardno);
//	TeamBoard findByMember(Member member);
	
}
