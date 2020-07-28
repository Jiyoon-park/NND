package com.ssafy.nnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.repository.TeamBoardRepository;


@Service
public class TeamBoardService {

	@Autowired
	private TeamBoardRepository teamboardRepository;
	
	public Page<TeamBoard> findTeamBoardList(Pageable pageable){
		pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize());
        return teamboardRepository.findAll(pageable);

	}
	
//	public TeamBoard findTeamBoardByTeamboardno(Long teamboardno) {
//        return teamboardRepository.findByTeamboardno(teamboardno);
//    }
//	
//	public void teamboardSave(TeamBoard teamboard) {
//		teamboardRepository.save(teamboard);
//	}
//
//	public void teambaordDelete(TeamBoard teamboard) {
//		teamboardRepository.delete(teamboard);
//	}
//	
//	public void teamboardUpdate(TeamBoard teamboard) {
//		
//	}
}
