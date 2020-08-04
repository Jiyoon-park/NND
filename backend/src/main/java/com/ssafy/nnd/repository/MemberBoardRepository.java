package com.ssafy.nnd.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.MemberBoard;


public interface MemberBoardRepository extends JpaRepository<MemberBoard, Long>, MemberBoardCustomRepository<MemberBoard> {
	List<MemberBoard> findAllByOrderByBoardNoDesc(Pageable pageable);
	
	@Query(value="SELECT * FROM memberboard JOIN likemember ON boardno=mboard WHERE mno = :mno ",nativeQuery=true)
	List<MemberBoard> findLikeboardByMno(@Param("mno") Long mno);

	// custom (필터 검색 시 사용되는 method)
	List<MemberBoard> findMemberBoardList(List<String> query, List<String> category, List<String> skills);
}

