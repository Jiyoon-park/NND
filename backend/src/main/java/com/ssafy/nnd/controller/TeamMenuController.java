package com.ssafy.nnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.TeamNotice;
import com.ssafy.nnd.repository.MemberRatingRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;
import com.ssafy.nnd.repository.TeamNoticeRepository;




@CrossOrigin
@RestController
public class TeamMenuController {


	@Autowired
	TeamNoticeRepository teamNoticeRepository;
	
	@Autowired
	MemberRatingRepository memberRatingRepository;
	
	@Autowired
	TeamBoardRepository teamBoardRepository;
	
	@GetMapping("")
	public List<TeamNotice> getAllTeamNotice(@PathVariable Long teamboardno ,Pageable pageable){
		return teamNoticeRepository.findByTeamBoardNoOrderByTeamNoticeNoDesc(teamboardno, pageable);
	}
}
