package com.ssafy.nnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.nnd.dto.LikeMember;
import com.ssafy.nnd.dto.LikeTeam;
import com.ssafy.nnd.dto.MemberBoard;
import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.repository.LikeMemberRepository;
import com.ssafy.nnd.repository.LikeTeamRepository;
import com.ssafy.nnd.repository.MemberBoardRepository;
import com.ssafy.nnd.repository.TeamBoardRepository;



/*
 *
 * 좋아요 리스트 추가 / 삭제
 * 좋아유 개수 불러오기 
 * 개인 좋아요 된거 불러오기 
 */
@CrossOrigin
@Controller
public class LikeController {
	@Autowired 
	LikeMemberRepository likememberRepository;
	
	@Autowired 
	LikeTeamRepository liketeamRepository;
	
	@Autowired
	TeamBoardRepository teamboardRepository;
	
	@Autowired
	MemberBoardRepository memberboardRepository;
	
	//개인 게시글 즐겨찾기 추가
	@PutMapping("likemember/save/{mno}/{mboard}")
	public @ResponseBody String createLikeMember(@PathVariable Long mno , @PathVariable Long mboard) {
		System.out.println("회원:"+mno + "게시글 번호:"+mboard);
		LikeMember like = new LikeMember();
//		
//    	Long userIdx = Long.parseLong(idx);
//    	Long no = Long.parseLong(boardno);
		like.setMboard(mboard);
		like.setMno(mno);
		likememberRepository.save(like);
		
		return "success";
	}
	//팀 게시글 즐겨찾기 추가
	@PutMapping("liketeam/save/{mno}/{tboard}")
	public @ResponseBody String createLikeTeam(@PathVariable Long mno , @PathVariable Long tboard) {
		System.out.println("회원:"+mno + "팀 게시글 번호:"+tboard);
		LikeTeam like = new LikeTeam();
		
    	like.setMno(mno);
		like.setTboard(tboard);
		liketeamRepository.save(like);
		
		return "success";
	}
	
	//개인 게시글 즐겨찾기 삭제
	
	@DeleteMapping("likemember/delete/{likeno}")
	public @ResponseBody String deleteLikeMember(@PathVariable Long likeno ) {
		
		
    	likememberRepository.deleteById(likeno);
		System.out.println("삭제됨");
		return "Delete success";
	}
	//팀 게시글 즐겨찾기 삭제
	@DeleteMapping("liketeam/delete/{likeno}")
	public @ResponseBody String deleteLikeTeam(@PathVariable Long likeno) {
    	liketeamRepository.deleteById(likeno);
		System.out.println("삭제됨");
		return "Delete success";
	}
	// 멤보 게시판 즐겨찾기 전체 목록
	@GetMapping("/likemember/list/{mno}")
    public @ResponseBody List<MemberBoard> getLikeMemberBoard(@PathVariable Long mno){
    	return memberboardRepository.findLikeboardByMno(mno);
    }
	
	// 팀게시판 즐겨찾기 전체목록 
	@GetMapping("/liketeam/list/{mno}")
	public @ResponseBody List<TeamBoard> getLikeTeamBoard(@PathVariable Long mno){
		return teamboardRepository.findLikeboardByMno(mno);
	}
	
	
	
//	 @DeleteMapping("/teamboard/delete/{idx}")
//    public String deleteTeamBoard(@PathVariable String idx){
//    	Long postID = Long.parseLong(idx);
//    	teamBoardRepository.deleteById(postID);
//    	
//    	return "Delete Success!";
//    }
}
