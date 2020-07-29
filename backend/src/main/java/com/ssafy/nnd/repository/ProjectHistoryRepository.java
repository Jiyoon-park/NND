package com.ssafy.nnd.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.ProjectHistory;


public interface ProjectHistoryRepository extends JpaRepository<ProjectHistory, Long> {

}

