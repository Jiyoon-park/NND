package com.ssafy.nnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.nnd.dto.MemberRating;

public interface MemberRatingRepository extends JpaRepository<MemberRating, Long> {
//	@Query(value = "select idx, round(avg(commitcnt)) as commitcnt,round(avg(teamworkship)) as teamworkship,round(avg(issuecnt)) as issuecnt,round(avg(attendrate)) as attendrate,round(avg(satisfaction)) as satisfaction from memberrating \r\n" + 
//			"left join teamregist on idx=memberidx\r\n" + 
//			"where teamregist.teamboardno=:teamboardno\r\n" + 
//			"group by memberrating.idx", nativeQuery = true)
//	List<MemberRating> findMemberRatingByTeamboardNo(@Param("teamboardno") Long teamboardno);

//	Optional<MemberRating> findByIdx(Long idx);

	Optional<MemberRating> findByIdxAndTeamboardNoAndRatedIdx(Long idx, Long teamboardNo, Long ratedIdx);
	
	Optional<List<MemberRating>> findByIdx(Long idx);
}
