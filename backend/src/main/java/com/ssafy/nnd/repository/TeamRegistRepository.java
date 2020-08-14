package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nnd.dto.TeamRegist;




public interface TeamRegistRepository extends JpaRepository<TeamRegist,Long>{
	
	@Query(value="select teamboard.teamboardno, teamname from teamboard JOIN teamregist ON teamboard.teamboardno=teamregist.teamboardno WHERE memberidx= :idx",nativeQuery=true)
	List<Object> findTeamByIdx(@Param("idx") Long idx);
	
	@Query(value="select member.idx, member.name, rated from member JOIN teamregist ON member.idx=teamregist.memberidx WHERE teamboardno= :teamboardno",nativeQuery=true)
	List<Object> findMemberByTeamboardNo(@Param("teamboardno") Long teamboardno);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM teamregist WHERE teamboardno = :teamboardno AND memberidx = :memberidx",nativeQuery=true)
	void deleteByTeamboardNoAndMemberIdx(@Param("teamboardno") Long teamboardno, @Param("memberidx") Long memberidx);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM teamregist WHERE teamboardno = :teamboardno",nativeQuery=true)
	void deleteAllByTeamboardNo(@Param("teamboardno") Long teamboardno);
	
	TeamRegist findByTeamboardNoAndMemberIdx(Long teamboardno, Long memberidx);
}
