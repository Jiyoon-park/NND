package com.ssafy.nnd.repository;

import java.util.List;

import com.ssafy.nnd.dto.TeamBoard;

public interface TeamBoardCustomRepository<T> {
	List<TeamBoard> findTeamBoardList(List<String> query, List<String> category, List<String> skills);
	
	void delete(T entity);
}
