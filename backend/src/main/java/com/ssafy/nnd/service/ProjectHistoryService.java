package com.ssafy.nnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.nnd.dto.ProjectHistory;
import com.ssafy.nnd.repository.ProjectHistoryRepository;


@Service
public class ProjectHistoryService {

	@Autowired
	private ProjectHistoryRepository projecthistoryRerpository;
	
	public Page<ProjectHistory> findProjectHistoryList(Pageable pageable){
		pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize());
        return projecthistoryRerpository.findAll(pageable);

	}

}
