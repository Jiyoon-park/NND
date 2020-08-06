package com.ssafy.nnd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;

import springfox.documentation.spring.web.json.Json;

@CrossOrigin
@RestController
public class TeamBoardController {

	@Autowired
    TeamBoardRepository teamBoardRepository;
	
	@Autowired
	MemberRepository memberRepository;

    @GetMapping("/teamboard/list")
    public List<TeamBoard> getAllMemberBoard(@RequestParam("page") Long page,@RequestParam("size") Long size, final Pageable pageable){
//    	System.out.println(pageable);
    	return teamBoardRepository.findAllByOrderByTeamboardNoDesc(pageable);
    }
    
    // 좋아요 누른거 + 안누른거 다 검색
    @PutMapping("/teamboard/search")
	public List<Object> searchTeamBoard(@RequestBody Map<String, Object> map, @RequestParam("page") Long page, @RequestParam("size") Long size, final Pageable pageable) {
		
		List<String> query = (List<String>) map.get("query");
		List<String> category = (List<String>) map.get("category");
		List<String> skills = (List<String>) map.get("skills");
		System.out.println("query : " + query);
		System.out.println("category : " + category);
		System.out.println("skills : " + skills);
		return teamBoardRepository.findTeamBoardList(query, category, skills, pageable);
	}
    
    // 좋아요 누른거 만 검색
    @PutMapping("/teamboard/search/{mno}")
    public List<Object> searchTeamBoard(@PathVariable Long mno, @RequestBody Map<String, Object> map, @RequestParam("page") Long page, @RequestParam("size") Long size, final Pageable pageable) {
    	
    	List<String> query = (List<String>) map.get("query");
    	List<String> category = (List<String>) map.get("category");
    	List<String> skills = (List<String>) map.get("skills");
    	System.out.println("query : " + query);
    	System.out.println("category : " + category);
    	System.out.println("skills : " + skills);
    	
    	return teamBoardRepository.findTeamBoardList(query, category, skills, mno, pageable);
    }
    
//    @GetMapping("/teamboard/{id}")
//    public TeamBoard getTeamBoard(@PathVariable String id){
//    	Long postID = Long.parseLong(id);
//    	
//    	Optional<TeamBoard> teamBoard = teamBoardRepository.findById(postID);
//    	System.out.println(teamBoard.get().getEmail());
//    	return teamBoard.get();
//    }
    
    @PostMapping("/teamboard/update/{teamboardno}")
    public TeamBoard updateMemberBoard(@PathVariable String teamboardno, @RequestBody TeamBoard newteamBoard)
    {
    	Long postID = Long.parseLong(teamboardno);
    	Optional<TeamBoard> teamBoard = teamBoardRepository.findByTeamboardNo(postID);
    	teamBoard.get().setTeamName(newteamBoard.getTeamName());
    	teamBoard.get().setMemberEmails(newteamBoard.getMemberEmails());
    	teamBoard.get().setGroupSize(newteamBoard.getGroupSize());
    	teamBoard.get().setDeadLine(newteamBoard.getDeadLine());
    	teamBoard.get().setTitle(newteamBoard.getTitle());
    	teamBoard.get().setContent(newteamBoard.getContent());
    	teamBoard.get().setTechStack(newteamBoard.getTechStack());
    	teamBoard.get().setCategory(newteamBoard.getCategory());
    	System.out.println(newteamBoard.toString());
    	teamBoardRepository.save(teamBoard.get());
    	return teamBoard.get();
    }
    
    @PutMapping("/teamboard/save/{idx}")
    public String createTeamBoard(@PathVariable Long idx,@RequestBody TeamBoard teamBoard){
    	Optional<Member> member = memberRepository.findMemberByIdx(idx);
    	String chiefEmail = member.get().getEmail();
    	
    	if(member.get().getTeamboardno()==0) {  //팀게시글 작성이 처음인 경우
    		
    	//teamboard에 팀장의 정보 저장
    	teamBoard.setIdx(member.get().getIdx());
    	teamBoard.setEmail(chiefEmail);
    	teamBoard.setName(member.get().getName());
    	
    	Optional<TeamBoard> latestTeamboard = teamBoardRepository.findLatestTeamboardNo();
    	member.get().setTeamboardno(latestTeamboard.get().getTeamboardNo()+1);
    	
    	String membersEmail = teamBoard.getMemberEmails(); 
    	if(membersEmail.equals("[]")) {
    		teamBoard.setMemberEmails("[\""+chiefEmail+"\"]");   
    	}else {
    		teamBoard.setMemberEmails("[\""+chiefEmail+"\","+ membersEmail.substring(1));
    		String tempmembersEmail = membersEmail.substring(1, membersEmail.length()-1);
    		StringTokenizer st = new StringTokenizer(tempmembersEmail,",");
    		
    		while(st.hasMoreTokens()) {
    			String email = st.nextToken();
    			Optional<Member> teammember = memberRepository.findMemberByEmail(email.substring(1,email.length()-1));
    			teammember.get().setTeamboardno(latestTeamboard.get().getTeamboardNo()+1);;
    		}
    		
    	}
    	System.out.println(teamBoard.toString());
    	TeamBoard newmemberBoard = teamBoardRepository.save(teamBoard);
    	
    	return "Success";
    	}
    	else {  // 게시글 작성한 적 있으면 실패
    		return "fail";
    	}
    }

    @DeleteMapping("/teamboard/delete/{teamboardno}")
    public String deleteTeamBoard(@PathVariable Long teamboardno){
    	teamBoardRepository.deleteById(teamboardno);
    	
    	return "Delete Success!";
    }

}
