package com.ssafy.nnd.controller;

import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.dto.TeamRegist;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;
import com.ssafy.nnd.repository.TeamRegistRepository;

@CrossOrigin
@RestController
public class TeamBoardController {

	@Autowired
    TeamBoardRepository teamBoardRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	TeamRegistRepository teamregistRepository;

	@GetMapping("teamboard/list/{teamboardno}")
	public Object getOneTeamboard(@PathVariable Long teamboardno) {
		return teamBoardRepository.findById(teamboardno);
	}
	
    @GetMapping("/teamboard/list")
    public List<TeamBoard> getAllMemberBoard(@RequestParam("page") Long page,@RequestParam("size") Long size, final Pageable pageable){
//    	System.out.println(pageable);
    	return teamBoardRepository.findAllByOrderByTeamboardNoDesc(pageable);
    }
    
    // mode값에 따라서 전체검색 / 좋아요 누른것만 검색
    /* (mode에 따라서 쿼리가 결정되게끔 구현)
     * 1. mode: 1 => 전체검색     
     * 2. mode: 2 => 좋아요 누른것만 검색
     * */
    @PutMapping("/teamboard/search/{mno}")
    public List<Object> searchTeamBoard(@PathVariable Long mno, @RequestBody Map<String, Object> map, @RequestParam("page") Long page, @RequestParam("mode") Long mode, @RequestParam("size") Long size, final Pageable pageable) {
    	
    	List<String> query = (List<String>) map.get("query");
    	List<String> category = (List<String>) map.get("category");
    	List<String> skills = (List<String>) map.get("skills");
    	System.out.println("query : " + query);
    	System.out.println("category : " + category);
    	System.out.println("skills : " + skills);
    	
    	return teamBoardRepository.findTeamBoardList(query, category, skills, mno, mode, pageable);
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
    
    @PutMapping("/teamboard/save/{idx}") // 글쓴이 idx
    public String createTeamBoard(@PathVariable Long idx, @RequestBody TeamBoard teamBoard){
    	Optional<Member> member = memberRepository.findById(idx);
    	String leaderEmail = member.get().getEmail();
    	
    	//teamboard에 팀장의 정보 저장
    	teamBoard.setIdx(member.get().getIdx());
    	teamBoard.setEmail(leaderEmail);
    	teamBoard.setName(member.get().getName());
    	teamBoard.setMemCnt(1);
    	
    	//1차저장 teamboardno 만들기 
    	System.out.println(teamBoard.toString());
    	TeamBoard newmemberBoard = teamBoardRepository.save(teamBoard);
    	
    	TeamRegist leaderRegist = new TeamRegist();
    	leaderRegist.setTeamboardNo(newmemberBoard.getTeamboardNo());
    	leaderRegist.setMemberIdx(member.get().getIdx());
    	leaderRegist.setMemberEmail(leaderEmail);
    	teamregistRepository.save(leaderRegist);
    	
    	//팀장, 멤버들의 idx 와 이메일을 teamregist에 넣는다
    	int count=1;
    	String membersEmail = newmemberBoard.getMemberEmails(); 
    	if(membersEmail.equals("[]")) {
    		
    		teamregistRepository.save(leaderRegist);
    	}else {
    		StringTokenizer st = new StringTokenizer(membersEmail,"[,\"] ");
    		
    		while(st.hasMoreTokens()) {
    			String email = st.nextToken();
    			Optional<Member> teammember = memberRepository.findMemberByEmail(email);
    			TeamRegist memberRegist = new TeamRegist();
    			memberRegist.setTeamboardNo(newmemberBoard.getTeamboardNo());
    			memberRegist.setMemberIdx(teammember.get().getIdx());
    			memberRegist.setMemberEmail(teammember.get().getEmail());
    			newmemberBoard.setMemCnt(++count);
    			teamBoardRepository.save(newmemberBoard);
    			teamregistRepository.save(memberRegist);
    		}
    		
    	}
    	
    	return newmemberBoard.getTeamboardNo().toString();	// teamBoardNo를 반환해 firebase에 등록하는 url도 완성할 수 있도록 한다.
    }

    @DeleteMapping("/teamboard/delete/{teamboardno}")
    public String deleteTeamBoard(@PathVariable Long teamboardno){
    	teamBoardRepository.deleteById(teamboardno);
    	
    	return "Delete Success!";
    }

}
