package com.ssafy.nnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.nnd.dto.Letter;
import com.ssafy.nnd.dto.Member;

public interface LetterRepository extends JpaRepository<Letter, Long>  {
	Optional<List<Letter>> findBySendIdx(int sendIdx);
	Optional<List<Letter>> findByReceiveIdx(int receiveIdx);
	
}
