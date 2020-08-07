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
import com.ssafy.nnd.repository.LetterRepository;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;

@CrossOrigin
@Controller
public class LetterController {
	
	@Autowired
	LetterRepository letterRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	TeamBoardRepository teamBoardRepository;
	
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
	public @ResponseBody List<Map<String,Object>> getLetterBySend(@PathVariable Long idx) {
		List<Object> letter = letterRepository.findBySendIdx(idx);
		
		List<Map<String, Object>> datalist = new ArrayList<Map<String,Object>>();

		for (int i = 0; i <letter.size() ; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[])letter.get(i);
			map.put("name",temp[1]);
			map.put("profile",temp[3]);
			map.put("letterNo", temp[9]);
			map.put("sendIdx", temp[10]);
			map.put("receiveIdx", temp[11]);
			map.put("content", temp[12]);
			map.put("createDate", temp[13]);
			map.put("read", temp[14]);
			datalist.add(map);
		}
		return datalist;
	}
	
	// 받는 사람 기준으로 검색
	@GetMapping("/letter/list/receive/{idx}")
	public @ResponseBody List<Map<String,Object>> getLetterByReceive(@PathVariable Long idx) {
		List<Object> letter = letterRepository.findByReceiveIdx(idx);
		List<Map<String, Object>> datalist = new ArrayList<Map<String,Object>>();

		for (int i = 0; i <letter.size() ; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[])letter.get(i);
			map.put("name",temp[1]);
			map.put("profile",temp[3]);
			map.put("letterNo", temp[9]);
			map.put("sendIdx", temp[10]);
			map.put("receiveIdx", temp[11]);
			map.put("content", temp[12]);
			map.put("createDate", temp[13]);
			map.put("read", temp[14]);
			datalist.add(map);
		}
		return datalist;
	}
	
	// C
	@PutMapping("/letter/create")
	public @ResponseBody String createLetter(@RequestBody Letter letter) {
		System.out.println(letter.toString());
		try {
			letterRepository.save(letter);
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@PostMapping("/letter/update/{letterno}")
    public @ResponseBody Letter updateLetter(@PathVariable Long letterno)
    {
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
	   @PostMapping("letter/teamaccept/{sendidx}/{receiveidx}")
	   public @ResponseBody String teamAccept(@PathVariable Long sendidx, @PathVariable Long receiveidx) {
	      
	      Optional<TeamBoard> team = teamBoardRepository.findByIdx(receiveidx);
	      Optional<Member> member = memberRepository.findById(sendidx);
	      Long groupMemberCount = memberRepository.countTeamMemberByTeamboardNo(team.get().getTeamboardNo());
	      
	      if(member.get().getTeamboardno() == 0 && team.get().getGroupSize()>groupMemberCount ){  //현재 팀이 없거나 모집인원을 넘지 않은 경우
	    	  
	    	  String currentTeamMember = team.get().getMemberEmails();
	    	  String changedTeamMember = currentTeamMember.substring(0, currentTeamMember.length()-1) + ", \"" +member.get().getEmail() + "\"]";
	    	  // '"oks2238@naver.com", "hjh@naver.com"' +', "뉴멤버"'
	    	  team.get().setMemberEmails(changedTeamMember);
	    	  member.get().setTeamboardno(team.get().getTeamboardNo());   // member별 팀 등록
	    	  teamBoardRepository.save(team.get());
	    	  memberRepository.save(member.get());
	    	  return "success";
	    	  
	      }else { // 현재 팀이 있거나 꽉 차있을 경우
	    	  
	    	  return "fail";
	      }
	   }
	   //개인이 팀장의 스카웃을 수락할 경우
	   @PostMapping("letter/memberaccept/{sendidx}/{receiveidx}")
	   public @ResponseBody String memberAccept(@PathVariable Long sendidx, @PathVariable Long receiveidx) {
	      
	      Optional<TeamBoard> team = teamBoardRepository.findByIdx(sendidx);
	      Optional<Member> member = memberRepository.findById(receiveidx);
	      Long groupMemberCount = memberRepository.countTeamMemberByTeamboardNo(team.get().getTeamboardNo());

	      if(member.get().getTeamboardno()==0 && team.get().getGroupSize()>groupMemberCount) {  // 팀이 결정되지 않은 경우
	    	  String currentTeamMember = team.get().getMemberEmails();
	    	  String changedTeamMember = currentTeamMember.substring(0, currentTeamMember.length()-1) + ", \"" +member.get().getEmail() + "\"]";
	    	  // '"oks2238@naver.com", "hjh@naver.com"' +', "뉴멤버"'
	    	  team.get().setMemberEmails(changedTeamMember);
	    	  member.get().setTeamboardno(team.get().getTeamboardNo());  // member별 팀 등록
	    	  teamBoardRepository.save(team.get());
	    	  memberRepository.save(member.get());
	    	  return "success";
	    	  
	      }else {
	    	  return "fail";
	      }
	   }

}
