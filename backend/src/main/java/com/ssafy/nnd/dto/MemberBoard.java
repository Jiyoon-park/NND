package com.ssafy.nnd.dto;
//import com.example.oauth2.domain.BoardType;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "memberboard")
public class MemberBoard {

	@Id
	@Column (name = "boardno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long boardNo; // 게시글 번호

	@Column
	private String email; // 작성자 이메일

	@Column
	private String title; // 제목

	@Column
	private String content; // 내용

	@Column(name = "contentstack")
	private String contentStack; // 내용스택

	@Column(name = "techstack")
	private String techStack; // 내용스택

	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;

	@Column
	private int likecnt; // 개수


	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getContentStack() {
		return contentStack;
	}

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}

	public void setContentStack(String contentStack) {
		this.contentStack = contentStack;
	}

	

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public int getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}

	@Override
	public String toString() {
		return "MemberBoard [boardNo=" + boardNo + ", email=" + email + ", title=" + title + ", content=" + content
				+ ", contentStack=" + contentStack + ", techStack=" + techStack + ", createDate=" + createDate
				+ ", likecnt=" + likecnt + "]";
	}

	

	
}
