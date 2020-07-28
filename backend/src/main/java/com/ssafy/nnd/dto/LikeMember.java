package com.ssafy.nnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@Builder
public class LikeMember implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long likeno;

	@Column
	public int mboard;

	@Column
	public int mno;

	@Column(insertable = false, updatable = false)
	private LocalDateTime createdate;
	public LikeMember() {
		// TODO Auto-generated constructor stub
	}
	public LikeMember(Long likeno, int mboard, int mno, LocalDateTime createdate) {
		super();
		this.likeno = likeno;
		this.mboard = mboard;
		this.mno = mno;
		this.createdate = createdate;
	}
	public Long getLikeno() {
		return likeno;
	}
	public void setLikeno(Long likeno) {
		this.likeno = likeno;
	}
	public int getMboard() {
		return mboard;
	}
	public void setMboard(int mboard) {
		this.mboard = mboard;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public LocalDateTime getCreatedate() {
		return createdate;
	}
	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}




}


