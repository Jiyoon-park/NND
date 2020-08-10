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

	private String link; // 홈페이지

	private String start;
	private String end;

	private String dday;

	private String type; //접수예정 접수중 마감임박 마감
	
	/////////////////////detail
	private String poster;

	private String host; //주최

	private String subject; //주관
	private String support; //후원 협찬
	private String apply; //접수방법
	private String qua; //참가자격
	private String price; //1등 시상금
	private String field; //1등 시상금



	public Contest(String title, String link, String start, String end, String dday, String type, String poster,
			String host, String subject, String support, String apply, String qua, String price, String field) {
		super();
		this.title = title;
		this.link = link;
		this.start = start;
		this.end = end;
		this.dday = dday;
		this.type = type;
		this.poster = poster;
		this.host = host;
		this.subject = subject;
		this.support = support;
		this.apply = apply;
		this.qua = qua;
		this.price = price;
		this.field = field;
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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setPoster(String poster) {
		this.poster = poster;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getQua() {
		return qua;
	}

	public void setQua(String qua) {
		this.qua = qua;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Contest [subject=" + subject + ", support=" + support + ", apply=" + apply + ", qua=" + qua + ", price="
				+ price + ", field=" + field + "]";
	}


	
	


}


