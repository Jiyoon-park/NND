package com.ssafy.nnd.repository;

import java.util.List;

import com.ssafy.nnd.dto.MemberBoard;

public interface MemberBoardCustomRepository<T> {
	List<MemberBoard> findMemberBoardList(List<String> query, List<String> category, List<String> skills);
	
	void delete(T entity);
}
