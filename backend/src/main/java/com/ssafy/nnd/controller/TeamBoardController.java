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
    
    @GetMapping("/teamboard/{id}")
    public TeamBoard getTeamBoard(@PathVariable String id){
    	Long postID = Long.parseLong(id);
    	
    	Optional<TeamBoard> teamBoard = teamBoardRepository.findById(postID);
    	System.out.println(teamBoard.get().getEmail());
    	return teamBoard.get();
    }
    
    @PostMapping("/teamboard/{id}")
    public TeamBoard updateMemberBoard(@PathVariable String id, @RequestBody TeamBoard newteamBoard)
    {
    	Long postID = Long.parseLong(id);
    	Optional<TeamBoard> teamBoard = teamBoardRepository.findById(postID);
    	teamBoard.get().setEmail(newteamBoard.getEmail());
    	teamBoard.get().setTitle(newteamBoard.getTitle());
    	System.out.println(newteamBoard.toString());
    	teamBoardRepository.save(teamBoard.get());
    	return teamBoard.get();
    }
    
    @PutMapping("/teamboard")
    public TeamBoard createTeamBoard(@RequestBody TeamBoard teamBoard){
    	System.out.println(teamBoard.toString());
    	TeamBoard newmemberBoard = teamBoardRepository.save(teamBoard);
    	return newmemberBoard;
    }

    @DeleteMapping("/teamboard/{id}")
    public String deleteTeamBoard(@PathVariable String id){
    	Long postID = Long.parseLong(id);
    	teamBoardRepository.deleteById(postID);
    	
    	return "Delete Success!";
    }

	

}
