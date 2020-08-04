package com.ssafy.nnd.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.nnd.dto.TeamBoard;

public class TeamBoardCustomRepositoryImpl implements TeamBoardCustomRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public void delete(Object entity) {
		System.out.println("custom delete");
		entityManager.remove(entity);
	}

	@Override
	@Query(nativeQuery = true)
	public List findTeamBoardList(List query, List category, List skills) {
		System.out.println("custom findTeamBoardList");
		StringBuilder str = new StringBuilder();
		str.append("select m from TeamBoard as m where ");
		
		// query
		// title과 content를 대상으로 검색
		if (query.size() > 0) {
			str.append("(");
			for (String string : (List<String>)query) {
				str.append("title like " + "\'%" + string + "%\'");
				str.append(" OR ");
				str.append("content like " + "\'%" + string + "%\'");
				str.append(" OR ");
				str.append("teamname like " + "\'%" + string + "%\'");
				str.append(" OR ");
			}
			str.append("1 = 0) AND ");
		}
		
		// category
		if (category.size() > 0) {
			str.append("(");
			for (String string : (List<String>)category) {
				str.append("category = " + "\'" + string + "\'");
				str.append(" OR ");
			}
			str.append("1 = 0) AND ");
		}
		
		// skills
		if (skills.size() > 0) {
			str.append("(");
			for (String string : (List<String>)skills) {
				str.append("techstack like " + "\'%" + string + "%\'");
				str.append(" OR ");
			}
			str.append("1 = 0) AND ");
		}
		
		// 맨마지막은 항상 1을 붙여서 AND로 종료되지 않도록 한다.
		str.append("1 = 1");
		System.out.println(str.toString());
		
		return entityManager.createQuery(str.toString(), TeamBoard.class).getResultList();
	}


}
