package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.nnd.dto.TeamBoard;

public interface TeamBoardCustomRepository<T> {
	List<T> findTeamBoardList(List<String> query, List<String> category, List<String> skills, final Pageable pageable);
	List<T> findTeamBoardList(List<String> query, List<String> category, List<String> skills, Long mno, final Pageable pageable);
	
}
