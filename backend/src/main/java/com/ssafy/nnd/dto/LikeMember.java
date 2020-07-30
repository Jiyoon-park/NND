package com.ssafy.nnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table (name="likemember")
@Builder
public class LikeMember implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long likeno;

	@Column
	public Long mboard;

	@Column
	public Long mno;

	@Column(insertable = false, updatable = false)
	private LocalDateTime createdate;
	public LikeMember() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getLikeno() {
		return likeno;
	}
	public void setLikeno(Long likeno) {
		this.likeno = likeno;
	}
	
	public Long getMboard() {
		return mboard;
	}

	public void setMboard(Long mboard) {
		this.mboard = mboard;
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
		return "LikeMember [likeno=" + likeno + ", mboard=" + mboard + ", mno=" + mno + ", createdate=" + createdate
				+ "]";
	}


	

}


