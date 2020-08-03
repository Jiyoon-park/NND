package com.ssafy.nnd.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.nnd.dto.MemberBoard;

public class MemberBoardCustomRepositoryImpl implements MemberBoardCustomRepository {
	
	@Autowired
	EntityManager entityManager;

	@Override
	@Query(nativeQuery = true)
	public List<MemberBoard> findMemberBoardList() {
		String query = "";
		System.out.println("custom findMemberBoardList");
		
		query = "select boardno, idx, email, title, content, contentstack, techstack, createdate, likecnt, name from memberboard where (contentstack = \'스터디\' or contentstack = \'공모전\') and (techstack = \'JAVA\') and (title like \'%싹쓰리%\' or content like \'%싹쓰리%\')";
		
		return entityManager.createQuery(query, MemberBoard.class).getResultList();
	}

	@Override
	public void delete(Object entity) {
		System.out.println("custom delete");
		entityManager.remove(entity);
	}

}
