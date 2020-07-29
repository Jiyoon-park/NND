package com.ssafy.nnd.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "letter")
@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Letter {

	@Id
	@Column(name = "letterno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long letterNo;	// 메시지 식별번호
	
	@Column(name = "sendidx")
	private int sendIdx;	// 보내는 사람 idx
	
	@Column(name = "receiveidx")
	private int receiveIdx;	// 받는 사람 idx
	
	@Column(name = "content")
	private String content;	// 메시지 내용
	
	@Column(name = "createdate", insertable = false, updatable = false)
	private LocalDateTime createDate;	// 메시지 발수신 시각
	
	public Letter() {
		// TODO Auto-generated constructor stub
	}

	public long getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(long letterNo) {
		this.letterNo = letterNo;
	}

	public int getSendIdx() {
		return sendIdx;
	}

	public void setSendIdx(int sendIdx) {
		this.sendIdx = sendIdx;
	}

	public int getReceiveIdx() {
		return receiveIdx;
	}

	public void setReceiveIdx(int receiveIdx) {
		this.receiveIdx = receiveIdx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Letter [letterNo=").append(letterNo).append(", sendIdx=").append(sendIdx)
				.append(", receiveIdx=").append(receiveIdx).append(", content=").append(content).append(", createDate=")
				.append(createDate).append("]");
		return builder.toString();
	}
	
}
