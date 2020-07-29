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
public class Member implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idx;

    @Column
    public String name;

    @Column
    public String email;
    
    @Column
    public String profile;
    
	@Column
    public String password;
    
    @Column
    public String company;
    
    @Column
    public String gitaddr;
    
    @Column
    public String memberstack;

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getGitaddr() {
		return gitaddr;
	}

	public void setGitaddr(String gitaddr) {
		this.gitaddr = gitaddr;
	}

	public String getMemberstack() {
		return memberstack;
	}

	public void setMemberstack(String memberstack) {
		this.memberstack = memberstack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [idx=").append(idx).append(", name=").append(name).append(", email=").append(email)
				.append(", profile=").append(profile).append(", company=").append(company).append("]");
		return builder.toString();
	}
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String name, String email, String password, String profile, String company) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.company = company;
	}

	
	

}


