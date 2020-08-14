package com.ssafy.nnd.dto;
//import com.example.oauth2.domain.BoardType;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "teamregist")
public class TeamRegist {

	@Id
	@Column (name = "teamregistno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long teamRegistNo; // 팀 공지사항 번호

	@Column (name = "teamboardno")
	private  Long teamboardNo;  // 팀 고유 번호
	
	@Column (name = "memberidx")
	private Long memberIdx; // 멤버 idx
	
	@Column (name="memberemail")
	private String memberEmail;   //멤버 email
	
	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;  //가입 시간
	
	@Column
	private Long rated;

	public TeamRegist() {
		// TODO Auto-generated constructor stub
	}

	public Long getTeamRegistNo() {
		return teamRegistNo;
	}

	public void setTeamRegistNo(Long teamRegistNo) {
		this.teamRegistNo = teamRegistNo;
	}

	public Long getTeamboardNo() {
		return teamboardNo;
	}

	public void setTeamboardNo(Long teamboardNo) {
		this.teamboardNo = teamboardNo;
	}

	public Long getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(Long memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Long getRated() {
		return rated;
	}

	public void setRated(Long rated) {
		this.rated = rated;
	}

	@Override
	public String toString() {
		return "TeamRegist [teamRegistNo=" + teamRegistNo + ", teamboardNo=" + teamboardNo + ", memberIdx=" + memberIdx
				+ ", memberEmail=" + memberEmail + ", createDate=" + createDate + ", rated=" + rated + "]";
	}
	

	
	
	
}
