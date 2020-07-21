package kr.demonic.jpa.persistence;

import kr.demonic.jpa.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * [JPA] Member Repository DB 기능 활용 인터페이스
 */
public interface MemberRepository extends CrudRepository<Member, Long> {
//    이메일로 유저 검색.
    Member findByEmail(@Param("email") String email);
}