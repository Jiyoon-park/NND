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
@Table(name = "teamdiary")
public class TeamDiary {

	@Id
	@Column(name = "teamdiaryno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamdiaryNo; // 게시글 번호

	@Column
	private Long teamboardno; // 제목

	@Column
	private String title; // 제목

	@Column
	private String color; // 본문내용
	
	@Column
	private String start;
	
	@Column
	private String end; // 기술스택태그
	
	@Column
	private boolean timed; // 내용스택태그

	public TeamDiary() {
		// TODO Auto-generated constructor stub
	}

	public Long getTeamdiaryNo() {
		return teamdiaryNo;
	}

	public void setTeamdiaryNo(Long teamdiaryNo) {
		this.teamdiaryNo = teamdiaryNo;
	}


	public Long getTeamboardno() {
		return teamboardno;
	}

	public void setTeamboardno(Long teamboardno) {
		this.teamboardno = teamboardno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public boolean isTimed() {
		return timed;
	}

	public void setTimed(boolean timed) {
		this.timed = timed;
	}

	public TeamDiary(Long teamdiaryNo, String title, String color, String start, String end, boolean timed) {
		super();
		this.teamdiaryNo = teamdiaryNo;
		this.title = title;
		this.color = color;
		this.start = start;
		this.end = end;
		this.timed = timed;
	}

}
