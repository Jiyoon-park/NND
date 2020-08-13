package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.TeamDiary;



public interface TeamDiaryRepository extends JpaRepository<TeamDiary,Long>{
	List<TeamDiary> findByTeamboardno(Long teamboardno);
	TeamDiary findByColor(String color);
//
//	List<TeamBoard> findLikeboardByMno(@Param("mno") Long mno);
//	

}



