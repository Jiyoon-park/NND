package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.TeamPost;




public interface TeamPostRepository extends JpaRepository<TeamPost,Long>{
	
	List<TeamPost> findByTeamboardNoOrderByTeamPostNoDesc(Long teamboardno);
	
}
