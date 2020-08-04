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

import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.repository.TeamBoardRepository;

@CrossOrigin
@RestController
public class TeamBoardController {

	@Autowired
    TeamBoardRepository teamBoardRepository;

    @GetMapping("/teamboard/list")
    public List<TeamBoard> getAllMemberBoard(@RequestParam("page") Long page,@RequestParam("size") Long size, final Pageable pageable){
//    	System.out.println(pageable);
    	return teamBoardRepository.findAllByOrderByTeamboardNoDesc(pageable);
    }
    
    @PutMapping("/teamboard/search")
	public List<TeamBoard> searchTeamBoard(@RequestBody Map<String, Object> map, @RequestParam("page") Long page, @RequestParam("size") Long size, final Pageable pageable) {
		
		List<String> query = (List<String>) map.get("query");
		List<String> category = (List<String>) map.get("category");
		List<String> skills = (List<String>) map.get("skills");
		System.out.println("query : " + query);
		System.out.println("category : " + category);
		System.out.println("skills : " + skills);
		return teamBoardRepository.findTeamBoardList(query, category, skills, pageable);
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
    
    @PutMapping("/teamboard/save")
    public TeamBoard createTeamBoard(@RequestBody TeamBoard teamBoard){
    	System.out.println(teamBoard.toString());
    	TeamBoard newmemberBoard = teamBoardRepository.save(teamBoard);
    	return newmemberBoard;
    }

    @DeleteMapping("/teamboard/delete/{teamboardno}")
    public String deleteTeamBoard(@PathVariable Long teamboardno){
    	teamBoardRepository.deleteById(teamboardno);
    	
    	return "Delete Success!";
    }

}
