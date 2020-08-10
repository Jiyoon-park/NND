package com.ssafy.nnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Contest implements Serializable {

	private String title;

	private String link;

	private String host; //접수예정 접수중 마감임박 마감

	private String poster;

	private String term;

	private String dday;

	private String type; //접수예정 접수중 마감임박 마감



	public Contest(String title, String link, String host, String poster, String term, String dday, String type) {
		super();
		this.title = title;
		this.link = link;
		this.host = host;
		this.poster = poster;
		this.term = term;
		this.dday = dday;
		this.type = type;
	}

	public Contest() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDday() {
		return dday;
	}

	public void setDday(String dday) {
		this.dday = dday;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Contest [title=" + title + ", link=" + link + ", host=" + host + ", poster=" + poster + ", term=" + term
				+ ", dday=" + dday + ", type=" + type + "]";
	}
	
	


}


