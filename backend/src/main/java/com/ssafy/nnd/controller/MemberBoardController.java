package com.ssafy.nnd.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.ssafy.nnd.dto.MemberBoard;
import com.ssafy.nnd.repository.MemberBoardRepository;
import com.ssafy.nnd.repository.MemberRepository;

@CrossOrigin
@RestController
public class MemberBoardController {

	@Autowired
	MemberBoardRepository memberBoardRepository;

	@Autowired
	MemberRepository memberRepository;
	
    @GetMapping("/memberboard/list")
    public List<MemberBoard> getAllMemberBoard(@RequestParam("page") Long page,@RequestParam("size") Long size, final Pageable pageable){
    	return memberBoardRepository.findAllByOrderByBoardNoDesc(pageable);
    }
    
    @GetMapping("/memberboard/{id}")
    public MemberBoard getMemberBoard(@PathVariable String id){
    	Long postID = Long.parseLong(id);
    	
    	Optional<MemberBoard> memberBoard = memberBoardRepository.findById(postID);
    	System.out.println(memberBoard.get().getEmail());
    	return memberBoard.get();
    }
    
    // mode값에 따라서 전체검색 / 좋아요 누른것만 검색
    /* (mode에 따라서 쿼리가 결정되게끔 구현)
     * 1. mode: 1 => 전체검색     
     * 2. mode: 2 => 좋아요 누른것만 검색
     * */
    @PutMapping("/memberboard/search/{mno}")
    public List<Object> searchMemberBoard(@RequestParam("page") Long page, @RequestParam("mode") Long mode, @RequestParam("size") Long size, @RequestBody Map<String, Object> map, @PathVariable Long mno, final Pageable pageable) {
    	
    	List<String> query = (List<String>) map.get("query");
    	List<String> category = (List<String>) map.get("category");
    	List<String> skills = (List<String>) map.get("skills");
    	System.out.println("query : " + query);
    	System.out.println("category : " + category);
    	System.out.println("skills : " + skills);
    	return memberBoardRepository.findMemberBoardList(query, category, skills, mno, mode, pageable);
    }
    
    @PostMapping("/memberboard/update/{boardno}")
    public MemberBoard updateMemberBoard(@PathVariable String boardno, @RequestBody MemberBoard newmemberBoard)
    {
    	Long postID = Long.parseLong(boardno);
    	Optional<MemberBoard> memberBoard = memberBoardRepository.findById(postID);
    	memberBoard.get().setTitle(newmemberBoard.getTitle());
    	memberBoard.get().setContent(newmemberBoard.getContent());
    	memberBoard.get().setCategory(newmemberBoard.getCategory());
    	memberBoard.get().setTechStack(newmemberBoard.getTechStack());
    	
    	System.out.println(memberBoard.toString());
    	memberBoardRepository.save(memberBoard.get());
    	return memberBoard.get();
    }
    
    @PutMapping("/memberboard/save/{idx}")
    public String createMemberBoard(@PathVariable Long idx, @RequestBody MemberBoard memberBoard){
		Optional<Member> member = memberRepository.findMemberByIdx(idx);
    	memberBoard.setIdx(member.get().getIdx());
    	memberBoard.setEmail(member.get().getEmail());
    	memberBoard.setName(member.get().getName());
		System.out.println(memberBoard.toString());
    	MemberBoard newmemberBoard = memberBoardRepository.save(memberBoard);
    	
    	return newmemberBoard.getBoardNo().toString();
	}

	@DeleteMapping("/memberboard/delete/{boardno}")
    public String deleteMemberBoard(@PathVariable Long boardno){
    	memberBoardRepository.deleteById(boardno);
    	return "Delete Success!";
    }
    
}
