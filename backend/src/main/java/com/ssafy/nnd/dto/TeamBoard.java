package com.ssafy.nnd.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="teamboard")
public class TeamBoard{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long teamboardno; //게시글 번호

	@Column
	private String email; // 작성자 이메일

	@Column
	private String teamname; // 팀이름
	
	@Column
	private int groupsize; // 총인원
	
	@Column
	private String deadline; // 마감기한
	
	@Column
	private String title; // 제목
	
	@Column
	private String content; // 본문내용

	@Column
	private String techstack; // 기술스택태그

	@Column
	private String contentstack; // 내용스택태그

	@Column
	private int likecnt; // 개수
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime createdate;
//
//	@OneToOne(fetch=FetchType.LAZY)
//	private Member member;
//

	public Long getTeamboardno() {
		return teamboardno;
	}

	public void setTeamboardno(Long teamboardno) {
		this.teamboardno = teamboardno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getGroupsize() {
		return groupsize;
	}


	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public void setGroupsize(int groupsize) {
		this.groupsize = groupsize;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
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


	public String getTechstack() {
		return techstack;
	}

	public void setTechstack(String techstack) {
		this.techstack = techstack;
	}

	public String getContentstack() {
		return contentstack;
	}

	public void setContentstack(String contentstack) {
		this.contentstack = contentstack;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public int getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}

	public TeamBoard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TeamBoard [teamboardno=" + teamboardno + ", email=" + email + ", teamname=" + teamname + ", groupsize="
				+ groupsize + ", deadline=" + deadline + ", title=" + title + ", content=" + content + ", techstack="
				+ techstack + ", contentstack=" + contentstack + ", likecnt=" + likecnt + ", createdate=" + createdate
				+ "]";
	}

	
}
