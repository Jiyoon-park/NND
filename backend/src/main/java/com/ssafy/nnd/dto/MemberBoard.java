package com.ssafy.nnd.dto;
//import com.example.oauth2.domain.BoardType;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "memberboard")
public class MemberBoard {

	@Id
	@Column(name = "boardno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long boardNo; // 게시글 번호

	@Column
	private Long idx;

	@Column
	private String email; // 작성자 이메일

	@Column
	private String name;

	@Column
	private String title; // 제목

	@Column
	private String content; // 내용

	@Column
	private String imageurl; // 이미지 링크

	@Column(name = "category")
	private String category; // 내용스택

	@Column(name = "techstack")
	private String techStack; // 내용스택

	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;

	@Column
	private int likecnt; // 개수

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

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

	public String getTechStack() {
		return techStack;
	}

	public void setTechStack(String techStack) {
		this.techStack = techStack;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public MemberBoard() {
		// TODO Auto-generated constructor stub
	}

	public MemberBoard(Long boardNo, Long idx, String email, String name, String title, String content, String imageurl,
			String category, String techStack, LocalDateTime createDate, int likecnt) {
		super();
		this.boardNo = boardNo;
		this.idx = idx;
		this.email = email;
		this.name = name;
		this.title = title;
		this.content = content;
		this.imageurl = imageurl;
		this.category = category;
		this.techStack = techStack;
		this.createDate = createDate;
		this.likecnt = likecnt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberBoard [boardNo=").append(boardNo).append(", idx=").append(idx).append(", email=")
				.append(email).append(", name=").append(name).append(", title=").append(title).append(", content=")
				.append(content).append(", imageurl=").append(imageurl).append(", category=").append(category)
				.append(", techStack=").append(techStack).append(", createDate=").append(createDate)
				.append(", likecnt=").append(likecnt).append("]");
		return builder.toString();
	}

}
