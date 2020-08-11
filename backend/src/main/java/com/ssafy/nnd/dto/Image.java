package com.ssafy.nnd.dto;

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
@Table(name = "image")
@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

	@Id
	@Column(name = "imageno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageNo; // Primary Key

	@Column(name = "boardno")
	private Long boardNo; // board를 구별하기 위한 column 1

	@Column(name = "url")
	private String url; // 이미지를 저장할 url

	@Column(name = "type")
	private String type; // board를 구별하기 위한 column 2

	public Image() {
		// TODO Auto-generated constructor stub
	}

	public Image(Long boardNo, String url, String type) {
		super();
		this.imageNo = imageNo;
		this.boardNo = boardNo;
		this.url = url;
		this.type = type;
	}
	
	public Image(Long imageNo, Long boardNo, String url, String type) {
		super();
		this.imageNo = imageNo;
		this.boardNo = boardNo;
		this.url = url;
		this.type = type;
	}

	public Long getImageNo() {
		return imageNo;
	}

	public void setImageNo(Long imageNo) {
		this.imageNo = imageNo;
	}

	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image [imageNo=").append(imageNo).append(", boardNo=").append(boardNo).append(", url=")
				.append(url).append(", type=").append(type).append("]");
		return builder.toString();
	}

}
