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
	
	@Column (name = "memberidx")
	private Long memberIdx;
	
	@Column
	private String writer;
	
	@Column
	private String title; // 글 제목
	
	@Column
	private String content;   //내용
	
	@Column
	private int notice;  // 공지사항 여부 true false(1,0)
	
	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;  //작성 시간

	public TeamPost() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getTeamPostNo() {
		return teamPostNo;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public void setTeamPostNo(Long teamPostNo) {
		this.teamPostNo = teamPostNo;
	}

	@Override
	public String toString() {
		return "TeamPost [teamPostNo=" + teamPostNo + ", teamboardNo=" + teamboardNo + ", memberIdx=" + memberIdx
				+ ", writer=" + writer + ", title=" + title + ", content=" + content + ", notice=" + notice
				+ ", createDate=" + createDate + "]";
	}

	
}
