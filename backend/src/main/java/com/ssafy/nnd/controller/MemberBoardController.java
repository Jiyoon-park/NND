package com.ssafy.nnd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.MemberBoard;
import com.ssafy.nnd.repository.MemberBoardRepository;

@CrossOrigin
@RestController
public class MemberBoardController {

    @Autowired
    MemberBoardRepository memberBoardRepository;

    @GetMapping("/memberboard/list")
    public List<MemberBoard> getAllMemberBoard(){
    	
    	
    	return memberBoardRepository.findAll();
    }
    
    @GetMapping("/member/{id}")
    public MemberBoard getMemberBoard(@PathVariable String id){
    	Long postID = Long.parseLong(id);
    	
    	Optional<MemberBoard> memberBoard = memberBoardRepository.findById(postID);
    	System.out.println(memberBoard.get().getEmail());
    	return memberBoard.get();
    }
    
    @PostMapping("/member/update/{id}")
    public MemberBoard updateMemberBoard(@PathVariable String id, @RequestBody MemberBoard newmemberBoard)
    {
    	Long postID = Long.parseLong(id);
    	Optional<MemberBoard> memberBoard = memberBoardRepository.findById(postID);
    	memberBoard.get().setTitle(newmemberBoard.getTitle());
    	memberBoard.get().setContent(newmemberBoard.getContent());
    	memberBoard.get().setContentStack(newmemberBoard.getContentStack());
    	memberBoard.get().setTechStack(newmemberBoard.getTechStack());
    	
    	System.out.println(memberBoard.toString());
    	memberBoardRepository.save(memberBoard.get());
    	return memberBoard.get();
    }
    
    @PutMapping("/member/save")
    public MemberBoard createMemberBoard(@RequestBody MemberBoard memberBoard){
    	System.out.println(memberBoard.toString());
    	MemberBoard newmemberBoard = memberBoardRepository.save(memberBoard);
    	return newmemberBoard;
    }

    @DeleteMapping("/member/delete/{id}")
    public String deleteMemberBoard(@PathVariable String id){
    	Long postID = Long.parseLong(id);
    	memberBoardRepository.deleteById(postID);
    	
    	return "Delete Success!";
    }
    
}


