package com.ssafy.nnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.TeamBoard;



public interface TeamBoardRepository extends JpaRepository<TeamBoard,Long>{

	List<TeamBoard> findAllByOrderByTeamboardNoDesc();
	Optional<TeamBoard> findByTeamboardNo(Long teamboardno);
}
