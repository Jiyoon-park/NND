package com.ssafy.nnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.LikeMember;
import com.ssafy.nnd.dto.TeamBoard;



public interface LikeMemberRepository extends JpaRepository<LikeMember,Long>{

//	TeamBoard findByTeamboardno(Long teamboardno);
//	TeamBoard findByMember(Member member);
	
}
