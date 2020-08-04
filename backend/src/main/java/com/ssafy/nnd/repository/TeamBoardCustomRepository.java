package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.nnd.dto.TeamBoard;

public interface TeamBoardCustomRepository<T> {
	List<TeamBoard> findTeamBoardList(List<String> query, List<String> category, List<String> skills, final Pageable pageable);
	
	void delete(T entity);
}
