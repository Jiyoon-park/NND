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
	@Column (name = "teamboardno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long teamboardNo; //게시글 번호

	@Column
	private String email; // 작성자 이메일

	@Column (name = "teamname")
	private String teamName; // 팀이름
	
	@Column (name = "groupsize")
	private int groupSize; // 총인원
	
	@Column (name = "deadline")
	private String deadLine; // 마감기한
	
	@Column
	private String title; // 제목
	
	@Column
	private String content; // 본문내용

	@Column (name = "techstack")
	private String techStack; // 기술스택태그
 
	@Column (name = "contentstack")
	private String contentStack; // 내용스택태그

	@Column (name = "likecnt")
	private int likeCnt; // 개수
	
	@Column(name = "createdate" ,insertable = false, updatable = false)
	private LocalDateTime createDate;
//
//	@OneToOne(fetch=FetchType.LAZY)
//	private Member member;
//

	public Long getTeamboardNo() {
		return teamboardNo;
	}

	public void setTeamboardNo(Long teamboardNo) {
		this.teamboardNo = teamboardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
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

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}

	public String getContentStack() {
		return contentStack;
	}

	public void setContentStack(String contentStack) {
		this.contentStack = contentStack;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "TeamBoard [teamboardNo=" + teamboardNo + ", email=" + email + ", teamName=" + teamName + ", groupSize="
				+ groupSize + ", deadLine=" + deadLine + ", title=" + title + ", content=" + content + ", techStack="
				+ techStack + ", contentStack=" + contentStack + ", likeCnt=" + likeCnt + ", createDate=" + createDate
				+ "]";
	}

	

	
	
}
