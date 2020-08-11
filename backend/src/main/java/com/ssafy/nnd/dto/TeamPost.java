package com.ssafy.nnd.dto;
//import com.example.oauth2.domain.BoardType;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "teampost")
public class TeamPost {

	@Id
	@Column (name = "teampostno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long teamPostNo; // 팀 공지사항 번호

	@Column (name = "teamboardno")
	private  Long teamboardNo;  // 외래키  팀 번호
	
	@Column
	private String title; // 글 제목
	
	@Column
	private String content;   //내용
	
	@Column
	private int notice;  // 공지사항 여부 true false(1,0)

	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;  //작성 시간

	public Long getTeamPostNo() {
		return teamPostNo;
	}

	public void setTeamPostNo(Long teamPostNo) {
		this.teamPostNo = teamPostNo;
	}

	public Long getTeamboardNo() {
		return teamboardNo;
	}

	public void setTeamboardNo(Long teamboardNo) {
		this.teamboardNo = teamboardNo;
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

	public int getNotice() {
		return notice;
	}

	public void setNotice(int notice) {
		this.notice = notice;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	
	public TeamPost() {
		// TODO Auto-generated constructor stub
	}

	public TeamPost(Long teamPostNo, Long teamboardNo, String title, String content, int notice,
			LocalDateTime createDate) {
		super();
		this.teamPostNo = teamPostNo;
		this.teamboardNo = teamboardNo;
		this.title = title;
		this.content = content;
		this.notice = notice;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "TeamPost [teamPostNo=" + teamPostNo + ", teamboardNo=" + teamboardNo + ", title=" + title + ", content="
				+ content + ", notice=" + notice + ", createDate=" + createDate + "]";
	}
	
}
