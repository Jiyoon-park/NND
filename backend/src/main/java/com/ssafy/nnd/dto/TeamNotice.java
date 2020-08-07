package com.ssafy.nnd.dto;
//import com.example.oauth2.domain.BoardType;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "teamnotice")
public class TeamNotice {

	@Id
	@Column (name = "teamnoticeno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long teamNoticeNo; // 팀 공지사항 번호

	@Column
	private  Long teamBoardNo;  // 외래키  팀 번호
	
	@Column
	private String title; // 글 제목
	
	@Column
	private String content;   //내용

	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;  //작성 시간

	public Long getTeamNoticeNo() {
		return teamNoticeNo;
	}

	public void setTeamNoticeNo(Long teamNoticeNo) {
		this.teamNoticeNo = teamNoticeNo;
	}

	public Long getTeamBoardNo() {
		return teamBoardNo;
	}

	public void setTeamBoardNo(Long teamBoardNo) {
		this.teamBoardNo = teamBoardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public TeamNotice() {
		// TODO Auto-generated constructor stub
	}
	
	public TeamNotice(Long teamNoticeNo, Long teamBoardNo, String title, String content, LocalDateTime createDate) {
		super();
		this.teamNoticeNo = teamNoticeNo;
		this.teamBoardNo = teamBoardNo;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "TeamNotice [teamNoticeNo=" + teamNoticeNo + ", teamBoardNo=" + teamBoardNo + ", title=" + title
				+ ", content=" + content + ", createDate=" + createDate + "]";
	}
	
	
	
}
