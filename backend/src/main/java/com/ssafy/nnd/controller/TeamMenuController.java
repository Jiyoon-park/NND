package com.ssafy.nnd.controller;

import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.MemberRating;
import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.dto.TeamPost;
import com.ssafy.nnd.dto.TeamRegist;
import com.ssafy.nnd.repository.MemberRatingRepository;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;
import com.ssafy.nnd.repository.TeamPostRepository;
import com.ssafy.nnd.repository.TeamRegistRepository;

@CrossOrigin
@RestController
public class TeamMenuController {

	@Autowired
	TeamPostRepository teampostRepository;

	@Autowired
	MemberRatingRepository memberRatingRepository;

	@Autowired
	TeamBoardRepository teamBoardRepository;

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	TeamRegistRepository teamregistRepository;

	// 팀 게시글 불러오기
	@GetMapping("teammenu/post/{teamboardno}")   //teamboardno
	public List<TeamPost> getAllTeamNotice(@PathVariable Long teamboardno, Pageable pageable) {
		if (teamboardno == 0) {
			return null; // 정해진 팀이 없습니다.
		} else {

			return teampostRepository.findByTeamboardNoOrderByTeamPostNoDesc(teamboardno, pageable);
		}
	}

	// 팀 멤버 목록 불러오기
	@GetMapping("teammenu/member/{teamboardno}")  //teamboardno
	public List<Object> getTeamMember(@PathVariable Long teamboardno) {

		if (teamboardno == 0) {
			return null; // 정해진 팀이 없습니다.
		} else {
			///////////////////////////////////////////////////// map 으로 다시보내야됨
			return teamregistRepository.findMemberByTeamboardNo(teamboardno);
		}
	}

	// 팀 게시글 글쓰기
	@PutMapping("teammenu/post/save/{teamboardno}")  //teamboardno
	public String saveTeamPost(@PathVariable Long teamboardno, TeamPost teampost) {

		teampost.setTeamboardNo(teamboardno);
		teampostRepository.save(teampost);
		return "save success";
	}
	
	// 팀 게시글 수정
	@PostMapping("teammenu/post/update")
	public String updateTeamPost(@RequestBody TeamPost newTeampost) {
		
		Long teampostno = newTeampost.getTeamPostNo();
		Optional<TeamPost> teampost = teampostRepository.findById(teampostno);
		teampost.get().setTitle(newTeampost.getTitle());
		teampost.get().setContent(newTeampost.getContent());
		
		teampostRepository.save(teampost.get());
		return "update success";
	}

	// 팀 게시글 삭제
	@DeleteMapping("teammenu/post/delete/{teamnoticeno}") 
	public String deleteTeamPost(@PathVariable Long teampostno) {
		teampostRepository.deleteById(teampostno);
		return "delete success";
	}

	// 멤버 추가
	@PutMapping("teammenu/member/save/{teamboardno}/{memberidx}")   //teamboardno, 추가된 사람 idx
	public String saveTeamMember(@PathVariable Long teamboardno, @PathVariable Long memberidx) {
		Optional<Member> member = memberRepository.findById(memberidx); // 추가할 팀 멤버 객체
		// 현재 팀의 멤버 수 가져오기
		TeamRegist memberRegist = new TeamRegist();
		memberRegist.setMemberIdx(memberidx);
		memberRegist.setMemberEmail(member.get().getEmail());
		teamregistRepository.save(memberRegist);
		
		return "member save success";
	}

	// 멤버 삭제
	@PostMapping("teammenu/member/delete/{teamboardno}/{memberidx}")   // 버튼 눌린사람의 idx
	public String deleteTeamMeber(@PathVariable Long teamboardno, @PathVariable Long memberidx) {
		
		teamregistRepository.deleteByTeamboardNoAndMemberIdx(teamboardno, memberidx);
		
		return "delete member success";
	}
	// 멤버 평가
	
	@PutMapping("teammenu/rating")   // 평가대상자 idx 꼭 넣어서 보내주세요
	public String memberRating(@RequestBody MemberRating memberrate) {
		memberRatingRepository.save(memberrate);
		
		return "member rating success";
	}
	
	// 마감 버튼     // 마감되면 git, jira issue, 출결 가져와서 디비에 넣기
	
	@PostMapping("teammenu/disband/{teamboardno}")   //teamboardno
	public String memberDisband(@PathVariable Long teamboardno) {
		
		return "disband success";
	}
}
