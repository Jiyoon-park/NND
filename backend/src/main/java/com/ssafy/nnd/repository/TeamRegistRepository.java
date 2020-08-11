package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.TeamRegist;




public interface TeamRegistRepository extends JpaRepository<TeamRegist,Long>{
	
	@Query(value="select teamboard.teamboardno, teamname from teamboard JOIN teamregist ON teamboard.teamboardno=teamregist.teamboardno WHERE memberidx= :idx",nativeQuery=true)
	List<Object> findTeamByIdx(@Param("idx") Long idx);
	
	@Query(value="select member.idx, member.name from member JOIN teamregist ON member.idx=teamregist.memberidx WHERE memberidx= :idx",nativeQuery=true)
	List<Object> findMemberByTeamboardNo(@Param("idx") Long idx);
	
	@Query(value="",nativeQuery=true)
	void deleteByTeamboardNoAndMemberIdx(@Param("teamboardno") Long teamboardno, @Param("memberidx") Long memberidx);
}
