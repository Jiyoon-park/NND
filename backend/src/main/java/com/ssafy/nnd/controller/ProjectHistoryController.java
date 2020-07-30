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
	
	@GetMapping("/projecthistory/list/{idx}")
    public Optional<ProjectHistory> getAllProjectHistory(@PathVariable Long idx){
    	return projectHistoryRepository.findByIdx(idx);
    }

	@PostMapping("/projecthistory/update/{historyno}")
    public ProjectHistory updateProjectHistory(@PathVariable Long historyno, @RequestBody ProjectHistory newprojectHistory)
    {
    	Optional<ProjectHistory> projecthistory = projectHistoryRepository.findById(historyno);
    	projecthistory.get().setProjectName(newprojectHistory.getProjectName());
    	projecthistory.get().setSummary(newprojectHistory.getSummary());
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
	 
	 
	 ////////////////////////////////////////////////////////////////////수정해야됨
	 @DeleteMapping("/projecthistory/delete/{historyno}")
	    public String deleteProjectHistory(@PathVariable Long historyno){
	    	projectHistoryRepository.deleteById(historyno);
	    	
	    	return "Delete Success!";
	    }
}
