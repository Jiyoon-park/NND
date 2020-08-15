package com.ssafy.nnd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.MemberRating;
import com.ssafy.nnd.dto.TeamDiary;
import com.ssafy.nnd.dto.TeamPost;
import com.ssafy.nnd.dto.TeamRegist;
import com.ssafy.nnd.repository.MemberRatingRepository;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;
import com.ssafy.nnd.repository.TeamDiaryRepository;
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

	@Autowired
	TeamDiaryRepository teamDiaryRepository;


	// 팀 게시글 불러오기
	@GetMapping("teammenu/post/{teamboardno}") // teamboardno
	public List<TeamPost> getAllTeamPost(@PathVariable Long teamboardno) {
		if (teamboardno == 0) {
			return null; // 정해진 팀이 없습니다.
		} else {

			return teampostRepository.findByTeamboardNoOrderByTeamPostNoDesc(teamboardno);
		}
	}

	// 팀 멤버 목록 불러오기
	@GetMapping("teammenu/member/{teamboardno}") // teamboardno
	public List<Map<String, Object>> getTeamMember(@PathVariable Long teamboardno) {
		List<Object> teamMemberList = teamregistRepository.findMemberByTeamboardNo(teamboardno);
		List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < teamMemberList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[]) teamMemberList.get(i);
			map.put("memberIdx", temp[0]);
			map.put("memberName", temp[1]);
			map.put("rated",temp[2]);
			datalist.add(map);
		}
		return datalist;
	}

	// 팀 게시글 글쓰기
	@PutMapping("teammenu/post/save") 
	public String saveTeamPost(@RequestBody TeamPost teampost) {

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
	@DeleteMapping("teammenu/post/delete/{teampostno}")
	public String deleteTeamPost(@PathVariable Long teampostno) {
		teampostRepository.deleteById(teampostno);
		return "delete success";
	}

	// 멤버 추가
	@PutMapping("teammenu/member/save/{teamboardno}/{memberidx}") // teamboardno, 추가된 사람 idx
	public String saveTeamMember(@PathVariable Long teamboardno, @PathVariable Long memberidx) {
		Optional<Member> member = memberRepository.findById(memberidx); // 추가할 팀 멤버 객체
		// 현재 팀의 멤버 수 가져오기
		TeamRegist memberRegist = new TeamRegist();
		memberRegist.setTeamboardNo(teamboardno);
		memberRegist.setMemberIdx(memberidx);
		memberRegist.setMemberEmail(member.get().getEmail());
		teamregistRepository.save(memberRegist);

		return "member save success";
	}

	// 멤버 삭제
	@DeleteMapping("teammenu/member/delete/{teamboardno}/{memberidx}") // 버튼 눌린사람의 idx
	public String deleteTeamMeber(@PathVariable Long teamboardno, @PathVariable Long memberidx) {

		try {
			teamregistRepository.deleteByTeamboardNoAndMemberIdx(teamboardno, memberidx);
			return "delete member success";
		} catch (Exception e) {
			return "delete member fail";
		}
	}
	
	// 멤버 평가
	@PutMapping("teammenu/rating/{teamboardno}") // 평가대상자 idx 꼭 넣어서 보내주세요
	public String memberRating(@PathVariable Long teamboardno, @RequestBody MemberRating memberrate) {
		
		Optional<TeamRegist>teamregist = teamregistRepository.findByTeamboardNoAndMemberIdx(teamboardno,memberrate.getIdx()); 
		try {
			//데이터 있으면 2로 나눠서 다시 수정하기
			Optional<MemberRating> origindata = memberRatingRepository.findByIdx(memberrate.getIdx());
			origindata.get().setAttendRate((origindata.get().getAttendRate()+memberrate.getAttendRate())/2);
			origindata.get().setCommitCnt((origindata.get().getCommitCnt()+memberrate.getCommitCnt())/2);
			origindata.get().setTeamworkship((origindata.get().getTeamworkship()+memberrate.getTeamworkship())/2);
			origindata.get().setIssueCnt((origindata.get().getIssueCnt()+memberrate.getIssueCnt())/2);
			origindata.get().setSatisfaction((origindata.get().getSatisfaction()+memberrate.getSatisfaction())/2);
			teamregist.get().setRated(Long.parseLong("1"));
			memberRatingRepository.save(origindata.get());
			return "rating success";
		} catch (Exception e) {
			//데이터 없으면 그냥 새로 넣기
			teamregist.get().setRated(Long.parseLong("1"));
			memberRatingRepository.save(memberrate);
			return "rating success";
		}
	}

	// 마감 버튼 // 마감되면 git, jira issue, 출결 가져와서 디비에 넣기

	@DeleteMapping("teammenu/disband/{teamboardno}") // teamboardno
	public String memberDisband(@PathVariable Long teamboardno) {

		teamregistRepository.deleteAllByTeamboardNo(teamboardno);

		return "disband success";
	}

	// 팀 목록 불러오기 letter controller의 메소드와 동일 , 주소값만 다름
	@GetMapping("teammenu/teamlist/{memberidx}") //현재 꼬시기 버트을 누르는 member의 idx
	public  List<Map<String,Object>> getAllTeamList(@PathVariable Long memberidx) {
		List<Object> teamList = teamregistRepository.findTeamByIdx(memberidx);

		List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < teamList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[]) teamList.get(i);
			map.put("teamboardNo", temp[0]);
			map.put("teamName", temp[1]);
			datalist.add(map);
		}
		return datalist;
	}
	//모든 멤버 평가점수 가져오기  
	//리턴 리스트
	@GetMapping("teammenu/rating/list/{teamboardno}")
	public List<Map<String,Object>> getAllRatingList(@PathVariable Long teamboardno) {
		List<MemberRating> memberRating = memberRatingRepository.findMemberRatingByTeamboardNo(teamboardno);
		List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < memberRating.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			MemberRating temp = memberRating.get(i);
			Optional<Member> memberinfo = memberRepository.findById(temp.getIdx());

			map.put("name",memberinfo.get().getName());
			map.put("commitCnt", temp.getCommitCnt());
			map.put("issueCnt", temp.getIssueCnt());
			map.put("attendRate", temp.getAttendRate());
			map.put("satisfaction", temp.getSatisfaction());
			map.put("teamworkship", temp.getTeamworkship());

			datalist.add(map);
		}
		return datalist;
	}
	//개인 멤버 평가점수 가져오기
	@GetMapping("teammenu/rating/list/{teamboardno}/{memberidx}")
	public MemberRating getMemberRatingList(@PathVariable Long teamboardno, @PathVariable Long memberidx) {
		try {
			List<MemberRating> memberRatingList = memberRatingRepository.findMemberRatingByTeamboardNo(teamboardno);
			for (int i = 0; i < memberRatingList.size(); i++) {
				Long temp = memberRatingList.get(i).getIdx();
				if(temp==memberidx) {
					return memberRatingList.get(i);
				}
			}
		} catch (Exception e) {
		}
		return null;

	}

	////////////////////////////////////////팀 다이어리//////////////////////////////////
	@GetMapping("/teammenu/diary/list/{teamboardno}")
	public @ResponseBody List<TeamDiary> getAllTeamDiary(@PathVariable Long teamboardno){
		System.out.println(teamboardno);
		return teamDiaryRepository.findByTeamboardno(teamboardno);
	}

	@DeleteMapping("/teammenu/diary/delete/{teamdiaryNo}")
	public @ResponseBody String deleteTeamDiary(@PathVariable Long teamdiaryNo){
		teamDiaryRepository.deleteById(teamdiaryNo);
		return "Delete Success!";
	}

	@PutMapping("/teammenu/diary/create/{teamboardno}")
	public @ResponseBody TeamDiary createTeamDiary(@PathVariable Long teamboardno,@RequestBody TeamDiary teamDiary){
		teamDiary.setTeamboardno(teamboardno);
		teamDiaryRepository.save(teamDiary);
		return teamDiaryRepository.findByColor(teamDiary.getColor());
	}


	@PostMapping("/teammenu/diary/update/{teamdiaryNo}")
	public @ResponseBody String updateTeamDiary(@PathVariable Long teamdiaryNo,@RequestBody TeamDiary newteamDiary)
	{
		Optional<TeamDiary> teamdiary = teamDiaryRepository.findById(teamdiaryNo);
		teamdiary.get().setTitle(newteamDiary.getTitle());
		teamdiary.get().setTimed(true);
		teamdiary.get().setStart(newteamDiary.getStart());
		teamdiary.get().setEnd(newteamDiary.getEnd());
		teamdiary.get().setColor(newteamDiary.getColor());
		teamDiaryRepository.save(teamdiary.get());
		return "update Success";
	}


}
