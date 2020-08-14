package com.ssafy.nnd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.nnd.dto.Letter;
import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.dto.TeamRegist;
import com.ssafy.nnd.repository.LetterRepository;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;
import com.ssafy.nnd.repository.TeamRegistRepository;

@CrossOrigin
@Controller
public class LetterController {

	@Autowired
	LetterRepository letterRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	TeamBoardRepository teamBoardRepository;

	@Autowired
	TeamRegistRepository teamregistRepository;

	
	@GetMapping("/letter/member/teamlist/{memberidx}") //현재 꼬시기 버트을 누르는 member의 idx
	public @ResponseBody List<Map<String,Object>> getAllTeamList(@PathVariable Long memberidx) {
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
	// R
	// 모든 메시지
	@GetMapping("/letter/list/all")
	public @ResponseBody List<Letter> getAllLetter() {
		return letterRepository.findAll();
	}

	@GetMapping("/letter/list/{letterno}")
	public @ResponseBody Optional<Letter> getAllLetter(@PathVariable Long letterno) {
		return letterRepository.findById(letterno);
	}

	// 보내는 사람 기준으로 검색
	@GetMapping("/letter/list/send/{idx}")
	public @ResponseBody List<Map<String, Object>> getLetterBySend(@PathVariable Long idx) {
		List<Object> letter = letterRepository.findBySendIdx(idx);

		List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < letter.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[]) letter.get(i);
			map.put("name", temp[1]);
			map.put("profile", temp[3]);
			map.put("letterNo", temp[8]);
			map.put("sendIdx", temp[9]);
			map.put("receiveIdx", temp[10]);
			map.put("content", temp[11]);
			map.put("createDate", temp[12]);
			map.put("read", temp[13]);
			map.put("letterType", temp[14]);
			map.put("teamboardNo",temp[15] );
			datalist.add(map);
		}
		return datalist;
	}

	// 받는 사람 기준으로 검색
	@GetMapping("/letter/list/receive/{idx}")
	public @ResponseBody List<Map<String, Object>> getLetterByReceive(@PathVariable Long idx) {
		List<Object> letter = letterRepository.findByReceiveIdx(idx);
		List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < letter.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[]) letter.get(i);
			map.put("name", temp[1]);
			map.put("profile", temp[3]);
			map.put("letterNo", temp[8]);
			map.put("sendIdx", temp[9]);
			map.put("receiveIdx", temp[10]);
			map.put("content", temp[11]);
			map.put("createDate", temp[12]);
			map.put("read", temp[13]);
			map.put("letterType", temp[14]);
			map.put("teamboardNo",temp[15] );
			datalist.add(map);
		}
		return datalist;
	}

	// C
	@PutMapping("/letter/create/{type}")
	public @ResponseBody String createLetter(@PathVariable String type, @RequestBody Letter letter) {

		System.out.println(letter.toString());
		try {
			if (type.equals("tboard")) {
				letter.setLetterType("tboard");
			} else if (type.equals("mboard")) {
				letter.setLetterType("mboard");
			}
			letterRepository.save(letter);
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/letter/update/{letterno}")
	public @ResponseBody Letter updateLetter(@PathVariable Long letterno) {
		Optional<Letter> letter = letterRepository.findById(letterno);
		letter.get().setRead(1);
		letterRepository.save(letter.get());
		return letter.get();
	}

	// D
	@DeleteMapping("/letter/delete/{id}")
	public @ResponseBody String deleteLetter(@PathVariable String id) {
		int postID = Integer.parseInt(id);
		try {
			letterRepository.deleteById((long) postID);
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}

	// 팀장이 개인 신청사항을 수락할 경우
	@PostMapping("letter/teamaccept/{sendidx}/{teamboardno}")
	public @ResponseBody String teamAccept(@PathVariable Long sendidx, @PathVariable Long teamboardno) {

		
		Optional<TeamBoard> team = teamBoardRepository.findById(teamboardno);
		Optional<Member> member = memberRepository.findById(sendidx);
		
		List<Object> teamList = teamregistRepository.findTeamByIdx(sendidx);
		boolean check = true;
		int curgroupsize= team.get().getMemCnt();
		int groupsize = team.get().getGroupSize();
		String message ="success";
		if(curgroupsize>=groupsize) {
			check=false;
			return message="already full";
		}
		
		for (int i = 0; i < teamList.size(); i++) {
			Object[] temp = (Object[]) teamList.get(i);
			if(Long.parseLong(temp[0].toString())==teamboardno) {
				check=false;
			}
		}
		if(check) {
			TeamRegist memberRegist = new TeamRegist();
			memberRegist.setTeamboardNo(team.get().getTeamboardNo());
			memberRegist.setMemberIdx(member.get().getIdx());
			memberRegist.setMemberEmail(member.get().getEmail());
			teamregistRepository.save(memberRegist);
			team.get().setMemCnt(++curgroupsize);
			teamBoardRepository.save(team.get());
			return message;
		}
		else {
			message="already joinded";
			return message;
		}
	}

	// 개인이 팀장의 스카웃을 수락할 경우
	@PostMapping("letter/memberaccept/{teamboardno}/{receiveidx}")
	public @ResponseBody String memberAccept(@PathVariable Long teamboardno, @PathVariable Long receiveidx) {

		Optional<TeamBoard> team = teamBoardRepository.findById(teamboardno);
		Optional<Member> member = memberRepository.findById(receiveidx);
		
		List<Object> teamList = teamregistRepository.findTeamByIdx(receiveidx);
		boolean check = true;
		int curgroupsize= team.get().getMemCnt();
		int groupsize = team.get().getGroupSize();
		String message = "success";
		if(curgroupsize>=groupsize) {
			check=false;
			return message="already full";
		}
		
		for (int i = 0; i < teamList.size(); i++) {
			Object[] temp = (Object[]) teamList.get(i);
			if(Long.parseLong(temp[0].toString())==teamboardno) {
				check=false;
			}
		}
		if(check) {
			
		TeamRegist memberRegist = new TeamRegist();
		memberRegist.setTeamboardNo(team.get().getTeamboardNo());
		memberRegist.setMemberIdx(member.get().getIdx());
		memberRegist.setMemberEmail(member.get().getEmail());
		teamregistRepository.save(memberRegist);
		team.get().setMemCnt(++curgroupsize);
		teamBoardRepository.save(team.get());
		
		return message;
		}else {
			message="already joined";
			return message;
		}

	}
	
	@GetMapping("letter/check/overlap/{memberidx}/{receiveidx}/{type}/{teamboardno}")
	public @ResponseBody String checkOverlap(@PathVariable Long memberidx, @PathVariable Long receiveidx, @PathVariable String type,@PathVariable Long teamboardno) {
		try {
			Optional<Letter> letter =letterRepository.findBySendIdxAndReceiveIdxAndLetterTypeAndTeamboardNo(memberidx, receiveidx, type, teamboardno);
			letter.get();
			return "overlap letter";
		} catch (Exception e) {	
			return "success";
		}
	}

}
