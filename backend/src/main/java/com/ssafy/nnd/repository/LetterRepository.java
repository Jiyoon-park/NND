package com.ssafy.nnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.Letter;

public interface LetterRepository extends JpaRepository<Letter, Long>  {
	
	@Query(value = "SELECT * FROM member RIGHT JOIN (SELECT * FROM letter WHERE sendidx=:idx) letter ON receiveidx = idx",nativeQuery=true)
	List<Object> findBySendIdx(@Param("idx") Long idx);
	
	@Query(value = "SELECT * FROM member RIGHT JOIN (SELECT * FROM letter WHERE receiveidx=:idx) letter ON sendidx=idx",nativeQuery=true)
	List<Object> findByReceiveIdx(@Param("idx") Long idx);
	
	Optional<Letter> findBySendIdxAndReceiveIdxAndLetterTypeAndTeamboardNo(Long sendidx, Long receiveidx, String lettertype, Long teamboardno);
}
