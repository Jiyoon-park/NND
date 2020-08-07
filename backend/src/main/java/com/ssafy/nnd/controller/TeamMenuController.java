package com.ssafy.nnd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.TeamNotice;
import com.ssafy.nnd.repository.MemberRatingRepository;
import com.ssafy.nnd.repository.MemberRepository;
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
	
	@Autowired
	MemberRepository memberRepository;
	
	// 팀 공지사항 불러오기 
	@GetMapping("teammenu/notice/{idx}")
	public List<TeamNotice> getAllTeamNotice(@PathVariable Long idx ,Pageable pageable){
		Optional<Member> member = memberRepository.findById(idx);
		Long teamboardno = member.get().getTeamboardno();
		
		if(teamboardno==0) {
			return null;  // 정해진 팀이 없습니다.
		}else {
			
			return teamNoticeRepository.findByTeamBoardNoOrderByTeamNoticeNoDesc(teamboardno, pageable);
		}
	}
	
	// 팀 멤버 목록 불러오기
	@GetMapping("teammenu/member/{idx}")
	public List<Member> getTeamMember(@PathVariable Long idx){
		Optional<Member> member = memberRepository.findById(idx);
		Long teamboardno = member.get().getTeamboardno();
		
		if(teamboardno==0) {
			return null;  // 정해진 팀이 없습니다.
		}else {
			
			return memberRepository.findByTeamboardno(teamboardno);
		}
	}
	
	// 팀 공지사항 글쓰기
	@PutMapping("teammenu/notice/save/{idx}")
	public String saveTeamNotice(@PathVariable Long idx, TeamNotice teamnotice) {
		
		Optional<Member> member = memberRepository.findById(idx);
		Long teamboardno = member.get().getTeamboardno();
		
		teamnotice.setTeamBoardNo(teamboardno);
		teamNoticeRepository.save(teamnotice);
		return "save success";
	}
	
	// 팀 공지사항 삭제
	@DeleteMapping("teammenu/notice/delete/{teamnoticeno}")
	public String deleteTeamNotice(@PathVariable Long teamnoticeno) {
		teamNoticeRepository.deleteById(teamnoticeno);
		return "delete success";
	}
	
	// 멤버 추가
	
	// 멤버 삭제
	
	// 멤버 평가
	
	// 마감 버튼 비활성화 
	
	
}
