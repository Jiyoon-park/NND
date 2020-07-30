package com.ssafy.nnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table (name="liketeam")
@Builder
public class LikeTeam implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long likeno;

	@Column
	public Long tboard;

	@Column
	public Long mno;

	@Column(insertable = false, updatable = false)
	private LocalDateTime createdate;

	public LikeTeam() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getLikeno() {
		return likeno;
	}

	public void setLikeno(Long likeno) {
		this.likeno = likeno;
	}

	
	public Long getTboard() {
		return tboard;
	}

	public void setTboard(Long tboard) {
		this.tboard = tboard;
	}

	public Long getMno() {
		return mno;
	}

	public void setMno(Long mno) {
		this.mno = mno;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "LikeTeam [likeno=" + likeno + ", tboard=" + tboard + ", mno=" + mno + ", createdate=" + createdate
				+ "]";
	}
	
	
}


