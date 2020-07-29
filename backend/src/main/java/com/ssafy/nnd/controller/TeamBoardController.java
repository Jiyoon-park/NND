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

import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.repository.TeamBoardRepository;

@CrossOrigin
@RestController
public class TeamBoardController {

	@Autowired
    TeamBoardRepository teamBoardRepository;

    @GetMapping("/teamboard/list")
    public List<TeamBoard> getAllMemberBoard(){
    	return teamBoardRepository.findAll();
    }
    
//    @GetMapping("/teamboard/{id}")
//    public TeamBoard getTeamBoard(@PathVariable String id){
//    	Long postID = Long.parseLong(id);
//    	
//    	Optional<TeamBoard> teamBoard = teamBoardRepository.findById(postID);
//    	System.out.println(teamBoard.get().getEmail());
//    	return teamBoard.get();
//    }
    
    @PostMapping("/teamboard/update/{idx}")
    public TeamBoard updateMemberBoard(@PathVariable String idx, @RequestBody TeamBoard newteamBoard)
    {
    	Long postID = Long.parseLong(idx);
    	Optional<TeamBoard> teamBoard = teamBoardRepository.findByIdx(postID);
    	teamBoard.get().setTeamName(newteamBoard.getTeamName());
    	teamBoard.get().setMemberEmails(newteamBoard.getMemberEmails());
    	teamBoard.get().setGroupSize(newteamBoard.getGroupSize());
    	teamBoard.get().setDeadLine(newteamBoard.getDeadLine());
    	teamBoard.get().setTitle(newteamBoard.getTitle());
    	teamBoard.get().setContent(newteamBoard.getContent());
    	teamBoard.get().setTechStack(newteamBoard.getTechStack());
    	teamBoard.get().setContentStack(newteamBoard.getContentStack());
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

    @DeleteMapping("/teamboard/delete/{idx}")
    public String deleteTeamBoard(@PathVariable String idx){
    	Long postID = Long.parseLong(idx);
    	teamBoardRepository.deleteById(postID);
    	
    	return "Delete Success!";
    }

}
