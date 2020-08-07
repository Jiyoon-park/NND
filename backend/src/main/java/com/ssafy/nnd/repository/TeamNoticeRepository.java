package com.ssafy.nnd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.TeamNotice;




public interface TeamNoticeRepository extends JpaRepository<TeamNotice,Long>, TeamBoardCustomRepository<Object>{
	
	List<TeamNotice> findByTeamBoardNoOrderByTeamNoticeNoDesc(Long teamboardno,Pageable pageable);
	
}
