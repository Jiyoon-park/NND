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
@Table(name="projecthistory")
public class ProjectHistory{
	
	@Id
	@Column (name = "historyno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long historyNo;			// 자동생성번호

	@Column
	private Long idx;
	
    @Column (name="projectname")
    private String projectName;	//플젝 이름, 주제

    @Column 
    private String summary;   //요약 한줄설명
    
    @Column 
    private String content;		// 프로젝트 소개
    
    @Column (name="gitlink")
    private String gitLink;		// 프로젝트 관련 git link
    
    @Column  (name ="usedstack")
    private String usedStack;   // 프로젝트에 사용한 기술스택

	public ProjectHistory() {
		// TODO Auto-generated constructor stub
	}
 
	public Long getHistoryNo() {
		return historyNo;
	}

	public void setHistoryNo(Long historyNo) {
		this.historyNo = historyNo;
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGitLink() {
		return gitLink;
	}

	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}

	public String getUsedStack() {
		return usedStack;
	}

	public void setUsedStack(String usedStack) {
		this.usedStack = usedStack;
	}

	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "ProjectHistory [historyNo=" + historyNo + ", idx=" + idx + ", projectName=" + projectName + ", summary="
				+ summary + ", content=" + content + ", gitLink=" + gitLink + ", usedStack=" + usedStack + "]";
	}

	

	
    
	
}
