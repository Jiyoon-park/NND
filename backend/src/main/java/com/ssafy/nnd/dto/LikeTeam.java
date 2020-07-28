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
public class LikeTeam implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long likeno;

	@Column
	public int tboard;

	@Column
	public int mno;

	@Column(insertable = false, updatable = false)
	private LocalDateTime createdate;

	public LikeTeam() {
		// TODO Auto-generated constructor stub
	}
	
	public LikeTeam(Long likeno, int tboard, int mno, LocalDateTime createdate) {
		super();
		this.likeno = likeno;
		this.tboard = tboard;
		this.mno = mno;
		this.createdate = createdate;
	}

	public Long getLikeno() {
		return likeno;
	}

	public void setLikeno(Long likeno) {
		this.likeno = likeno;
	}

	public int getTboard() {
		return tboard;
	}

	public void setTboard(int tboard) {
		this.tboard = tboard;
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


