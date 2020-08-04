package com.ssafy.nnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.TeamBoard;



public interface TeamBoardRepository extends JpaRepository<TeamBoard,Long>, TeamBoardCustomRepository<TeamBoard>{

	List<TeamBoard> findAllByOrderByTeamboardNoDesc(Pageable pageable);
	Optional<TeamBoard> findByTeamboardNo(Long teamboardno);

	@Query(value="SELECT * FROM teamboard JOIN liketeam ON teamboardno=tboard WHERE mno = :mno ",nativeQuery=true)
	List<TeamBoard> findLikeboardByMno(@Param("mno") Long mno);
	List<TeamBoard> findTeamBoardList(List<String> query, List<String> category, List<String> skills);
}
