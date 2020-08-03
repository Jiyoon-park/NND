package com.ssafy.nnd.repository;

import java.util.List;

import com.ssafy.nnd.dto.MemberBoard;

public interface MemberBoardCustomRepository<T> {
	List<MemberBoard> findMemberBoardList();
	
	void delete(T entity);
}
