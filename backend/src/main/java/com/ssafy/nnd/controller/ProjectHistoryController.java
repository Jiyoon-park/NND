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

import com.ssafy.nnd.dto.ProjectHistory;
import com.ssafy.nnd.dto.TeamBoard;
import com.ssafy.nnd.repository.ProjectHistoryRepository;



@CrossOrigin
@RestController
public class ProjectHistoryController {

	@Autowired
    ProjectHistoryRepository projectHistoryRepository;
	
	@GetMapping("/projecthistory/list")
    public List<ProjectHistory> getAllProjectHistory(){
    	return projectHistoryRepository.findAll();
    }

	@PostMapping("/projecthistory/update/{idx}")
    public ProjectHistory updateProjectHistory(@PathVariable String idx, @RequestBody ProjectHistory newprojectHistory)
    {
    	Long postID = Long.parseLong(idx);
    	Optional<ProjectHistory> projecthistory = projectHistoryRepository.findByIdx(postID);
    	projecthistory.get().setProjectName(newprojectHistory.getProjectName());
    	projecthistory.get().setContent(newprojectHistory.getContent());
    	projecthistory.get().setGitLink(newprojectHistory.getGitLink());
    	projecthistory.get().setUsedStack(newprojectHistory.getUsedStack());
    	System.out.println(newprojectHistory.toString());
    	projectHistoryRepository.save(projecthistory.get());
    	return projecthistory.get();
    }
	
	 @PutMapping("/projecthistory/save")
	    public ProjectHistory createProjectHistory(@RequestBody ProjectHistory projectHistory){
	    	System.out.println(projectHistory.toString());
	    	ProjectHistory newprojectHistory = projectHistoryRepository.save(projectHistory);
	    	return newprojectHistory;
	    }
	 
	 @DeleteMapping("/projecthistory/delete/{idx}")
	    public String deleteProjectHistory(@PathVariable String idx){
	    	Long postID = Long.parseLong(idx);
	    	projectHistoryRepository.deleteById(postID);
	    	
	    	return "Delete Success!";
	    }
}
