package com.ssafy.nnd.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.MemberBoard;


public interface MemberBoardRepository extends JpaRepository<MemberBoard, Long> {
}

