package com.ssafy.nnd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.TeamBoard;
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
	public List<TeamNotice> getAllTeamNotice(@PathVariable Long idx, Pageable pageable) {
		Optional<Member> member = memberRepository.findById(idx);
		Long teamboardno = member.get().getTeamboardno();

		if (teamboardno == 0) {
			return null; // 정해진 팀이 없습니다.
		} else {

			return teamNoticeRepository.findByTeamBoardNoOrderByTeamNoticeNoDesc(teamboardno, pageable);
		}
	}

	// 팀 멤버 목록 불러오기
	@GetMapping("teammenu/member/{idx}")
	public List<Member> getTeamMember(@PathVariable Long idx) {
		Optional<Member> member = memberRepository.findById(idx);
		Long teamboardno = member.get().getTeamboardno();

		if (teamboardno == 0) {
			return null; // 정해진 팀이 없습니다.
		} else {

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
	@PutMapping("teammenu/member/save/{idx}/{memberidx}")
	public String saveTeamMember(@PathVariable Long idx, @PathVariable Long memberidx) {
		Optional<Member> chief = memberRepository.findById(idx); // 팀장 멤버 객체
		Optional<Member> member = memberRepository.findById(memberidx); // 추가할 팀 멤버 객체
		Optional<TeamBoard> team = teamBoardRepository.findByIdx(chief.get().getTeamboardno()); // 팀장 팀정보
		Long groupMemberCount = memberRepository.countTeamMemberByTeamboardNo(team.get().getTeamboardNo());
		// 현재 팀의 멤버 수 가져오기

		if (member.get().getTeamboardno() == 0 && team.get().getGroupSize() > groupMemberCount) {
			String currentTeamMember = team.get().getMemberEmails();
			String changedTeamMember = currentTeamMember.substring(0, currentTeamMember.length() - 1) + ", \""
					+ member.get().getEmail() + "\"]";
			// '"oks2238@naver.com", "hjh@naver.com"' +', "뉴멤버"'
			team.get().setMemberEmails(changedTeamMember);

			member.get().setTeamboardno(chief.get().getTeamboardno());
			teamBoardRepository.save(team.get());
			memberRepository.save(member.get());
			return "member save succes";
		} else {
			return "member save fail";
		}
	}

	// 멤버 삭제
//	@PostMapping("teammenu/member/delete/{memberidx}")
//	public String deleteTeamMeber(@PathVariable Long memberidx) {
//		Optional<Member> member = memberRepository.findById(memberidx);
//		member.get().setTeamboardno(Long.parseLong("0"));
//		memberRepository.save(member.get());
//		return "delete member success";
//	}
	// 멤버 평가

	// 마감 버튼 비활성화

}
