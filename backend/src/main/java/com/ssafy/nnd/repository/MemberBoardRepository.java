package com.ssafy.nnd.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.MemberBoard;


public interface MemberBoardRepository extends JpaRepository<MemberBoard, Long> {
	List<MemberBoard> findAllByOrderByBoardNoDesc();
	
	@Query(value="SELECT * FROM memberboard JOIN likemember ON boardno=mboard WHERE mno = :mno ",nativeQuery=true)
	List<MemberBoard> findLikeboardByMno(@Param("mno") Long mno);
}

