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
@Table(name = "teamboard")
public class TeamBoard {

	@Id
	@Column(name = "teamboardno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamboardNo; // 게시글 번호

	@Column
	private Long idx;

	@Column
	private String email; // 작성자 이메일

	@Column(name = "teamname")
	private String teamName; // 팀이름

	@Column(name = "memberemails")
	private String memberEmails;

	@Column(name = "groupsize")
	private int groupSize; // 총인원

	@Column(name = "deadline")
	private String deadLine; // 마감기한

	@Column
	private String title; // 제목

	@Column
	private String content; // 본문내용

	@Column
	private String imageurl; // 이미지 링크

	@Column(name = "kakaolink")
	private String kakaoLink;

	@Column(name = "techstack")
	private String techStack; // 기술스택태그

	@Column(name = "category")
	private String category; // 내용스택태그

	@Column(name = "likecnt")
	private int likeCnt; // 개수

	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;

	@Column
	private String name;

	public TeamBoard() {
		// TODO Auto-generated constructor stub
	}

	public TeamBoard(Long teamboardNo, Long idx, String email, String teamName, String memberEmails, int groupSize,
			String deadLine, String title, String content, String imageurl, String kakaoLink, String techStack,
			String category, int likeCnt, LocalDateTime createDate, String name) {
		super();
		this.teamboardNo = teamboardNo;
		this.idx = idx;
		this.email = email;
		this.teamName = teamName;
		this.memberEmails = memberEmails;
		this.groupSize = groupSize;
		this.deadLine = deadLine;
		this.title = title;
		this.content = content;
		this.imageurl = imageurl;
		this.kakaoLink = kakaoLink;
		this.techStack = techStack;
		this.category = category;
		this.likeCnt = likeCnt;
		this.createDate = createDate;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTeamboardNo() {
		return teamboardNo;
	}

	public String getKakaoLink() {
		return kakaoLink;
	}

	public void setKakaoLink(String kakaoLink) {
		this.kakaoLink = kakaoLink;
	}

	public String getMemberEmails() {
		return memberEmails;
	}

	public void setMemberEmails(String memberEmails) {
		this.memberEmails = memberEmails;
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TeamBoard [teamboardNo=").append(teamboardNo).append(", idx=").append(idx).append(", email=")
				.append(email).append(", teamName=").append(teamName).append(", memberEmails=").append(memberEmails)
				.append(", groupSize=").append(groupSize).append(", deadLine=").append(deadLine).append(", title=")
				.append(title).append(", content=").append(content).append(", imageurl=").append(imageurl)
				.append(", kakaoLink=").append(kakaoLink).append(", techStack=").append(techStack).append(", category=")
				.append(category).append(", likeCnt=").append(likeCnt).append(", createDate=").append(createDate)
				.append(", name=").append(name).append("]");
		return builder.toString();
	}

}
