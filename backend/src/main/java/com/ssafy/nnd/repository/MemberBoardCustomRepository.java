package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.nnd.dto.MemberBoard;

public interface MemberBoardCustomRepository<T> {
	List<T> findMemberBoardList(List<String> query, List<String> category, List<String> skills, final Pageable pageable);
	List<T> findMemberBoardList(List<String> query, List<String> category, List<String> skills, Long mno, final Pageable pageable);
	
}
