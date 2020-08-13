package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.MemberRating;



public interface MemberRatingRepository extends JpaRepository<MemberRating,Long>{
	@Query(value="select ratingno, memberrating.idx, commitcnt, issuecnt, attendrate, satisfaction, teamworkship, memberrating.createdate from memberrating JOIN teamregist ON idx=memberidx WHERE teamregist.teamboardno= :teamboardno",nativeQuery=true)
	List<MemberRating> findMemberRatingByTeamboardNo(@Param("teamboardno") Long teamboardno);
}