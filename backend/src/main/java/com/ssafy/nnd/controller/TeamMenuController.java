//package com.ssafy.nnd.controller;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.StringTokenizer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.nnd.dto.Member;
//import com.ssafy.nnd.dto.MemberRating;
//import com.ssafy.nnd.dto.TeamBoard;
//import com.ssafy.nnd.dto.TeamPost;
//import com.ssafy.nnd.repository.MemberRatingRepository;
//import com.ssafy.nnd.repository.MemberRepository;
//import com.ssafy.nnd.repository.TeamBoardRepository;
//import com.ssafy.nnd.repository.TeamPostRepository;
//
//@CrossOrigin
//@RestController
//public class TeamMenuController {
//
//	@Autowired
//	TeamPostRepository teampostRepository;
//
//	@Autowired
//	MemberRatingRepository memberRatingRepository;
//
//	@Autowired
//	TeamBoardRepository teamBoardRepository;
//
//	@Autowired
//	MemberRepository memberRepository;
//	
//
//	// 팀 공지사항 불러오기
//	@GetMapping("teammenu/notice/{teamboardno}")   //teamboardno
//	public List<TeamPost> getAllTeamNotice(@PathVariable Long teamboardno, Pageable pageable) {
//		if (teamboardno == 0) {
//			return null; // 정해진 팀이 없습니다.
//		} else {
//
//			return teampostRepository.findByTeamboardNoOrderByTeamPostNoDesc(teamboardno, pageable);
//		}
//	}
//
//	// 팀 멤버 목록 불러오기
//	@GetMapping("teammenu/member/{teamboardno}")  //teamboardno
//	public List<Member> getTeamMember(@PathVariable Long teamboardno) {
//
//		if (teamboardno == 0) {
//			return null; // 정해진 팀이 없습니다.
//		} else {
//
//			return memberRepository.findByTeamboardno(teamboardno);
//		}
//	}
//
//	// 팀 공지사항 글쓰기
//	@PutMapping("teammenu/notice/save/{teamboardno}")  //teamboardno
//	public String saveTeamNotice(@PathVariable Long teamboardno, TeamPost teamnotice) {
//
//		teamnotice.setTeamboardNo(teamboardno);
//		teampostRepository.save(teamnotice);
//		return "save success";
//	}
//	
//	// 팀 공지사항 수정
//	@PostMapping("teammenu/notice/update")
//	public String updateTeamNotice(@RequestBody TeamPost newTeamnotice) {
//		
//		Long teampostno = newTeamnotice.getTeamPostNo();
//		Optional<TeamPost> teamnotice = teampostRepository.findById(teampostno);
//		teamnotice.get().setTitle(newTeamnotice.getTitle());
//		teamnotice.get().setContent(newTeamnotice.getContent());
//		
//		teampostRepository.save(teamnotice.get());
//		return "update success";
//	}
//
//	// 팀 공지사항 삭제
//	@DeleteMapping("teammenu/notice/delete/{teamnoticeno}") 
//	public String deleteTeamNotice(@PathVariable Long teamnoticeno) {
//		teampostRepository.deleteById(teamnoticeno);
//		return "delete success";
//	}
//
//	// 멤버 추가
//	@PutMapping("teammenu/member/save/{teamboardno}/{memberidx}")   //teamboardno, 추가된 사람 idx
//	public String saveTeamMember(@PathVariable Long teamboardno, @PathVariable Long memberidx) {
//		Optional<Member> member = memberRepository.findById(memberidx); // 추가할 팀 멤버 객체
//		Optional<TeamBoard> team = teamBoardRepository.findById(teamboardno); // 팀장 팀정보
//		Long groupMemberCount = memberRepository.countTeamMemberByTeamboardNo(team.get().getTeamboardNo());
//		// 현재 팀의 멤버 수 가져오기
//
//		if (member.get().getTeamboardno() == 0 && team.get().getGroupSize() > groupMemberCount) {
//			String currentTeamMember = team.get().getMemberEmails();
//			String changedTeamMember = currentTeamMember.substring(0, currentTeamMember.length() - 1) + ", \""
//					+ member.get().getEmail() + "\"]";
//			// '"oks2238@naver.com", "hjh@naver.com"' +', "뉴멤버"'
//			team.get().setMemberEmails(changedTeamMember);
//
//			member.get().setTeamboardno(teamboardno);
//			teamBoardRepository.save(team.get());
//			memberRepository.save(member.get());
//			return "member save succes";
//		} else {
//			return "member save fail";
//		}
//	}
//
//	// 멤버 삭제
//	@PostMapping("teammenu/member/delete/{idx}/{memberidx}")   // 버튼 눌린사람의 idx
//	public String deleteTeamMeber(@PathVariable Long idx, @PathVariable Long memberidx) {
//		Optional<Member> chief = memberRepository.findById(idx); // 팀장 멤버 객체
//		Optional<Member> member = memberRepository.findById(memberidx); // 추가할 팀 멤버 객체
//		Optional<TeamBoard> team = teamBoardRepository.findById(chief.get().getTeamboardno()); // 팀장 팀정보
//		
//		String curmembers = team.get().getMemberEmails();
//		String changedmember = "[\""+chief.get().getEmail()+"\"";
//		StringTokenizer st = new StringTokenizer(curmembers,"[,\"] ");
//		
//		while(st.hasMoreTokens()) {
//			String temp = st.nextToken();
//			if(!temp.equals(member.get().getEmail())&&!temp.equals(chief.get().getEmail())) {
//				changedmember+=", ";
//				changedmember+="\""+temp+"\"";
//			}
//		}
//		changedmember+="]";
//		team.get().setMemberEmails(changedmember);
//		member.get().setTeamboardno(Long.parseLong("0"));
//		
//		teamBoardRepository.save(team.get());
//		memberRepository.save(member.get());
//		return "delete member success";
//	}
//	// 멤버 평가
//	
//	@PutMapping("teammenu/rating")   // 평가대상자 idx 꼭 넣어서 보내주세요
//	public String memberRating(@RequestBody MemberRating memberrate) {
//		memberRatingRepository.save(memberrate);
//		return "member rating success";
//	}
//	
//	// 마감 버튼     // 마감되면 git, jira issue, 출결 가져와서 디비에 넣기
//	
//	@PostMapping("teammenu/disband/{teamboardno}")   //teamboardno
//	public String memberDisband(@PathVariable Long teamboardno) {
//		Optional<TeamBoard> team = teamBoardRepository.findById(teamboardno);
//		
//		//teamboard mebersemail 해산
//		team.get().setMemberEmails("[\"disband\"]");
//		teamBoardRepository.save(team.get());
//		//멤버별 teamboardno 0 으로 바꾸기 
//		List<Member> members = memberRepository.findByTeamboardno(teamboardno);
//		
//		for (int i = 0; i < members.size(); i++) {
//			Member member = members.get(i);
//			member.setTeamboardno(Long.parseLong("0"));
//			memberRepository.save(member);
//		}
//		return "disband success";
//	}
//}
