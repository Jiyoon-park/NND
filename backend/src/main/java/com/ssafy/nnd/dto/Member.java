package com.ssafy.nnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Builder
public class Member implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String name;

    @Column
    private String email;
    
    @Column
    private String profile;
    
	@Column
	private String password;

    @Column
    private String company;
    
    @Column
    private String gitaddr;
    
    @Column
    private String memberstack;

    @Column
    private Long teamboardno;
    
	public Long getTeamboardno() {
		return teamboardno;
	}

	public void setTeamboardno(Long teamboardno) {
		this.teamboardno = teamboardno;
	}

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
		return "Member [idx=" + idx + ", name=" + name + ", email=" + email + ", profile=" + profile + ", password="
				+ password + ", company=" + company + ", gitaddr=" + gitaddr + ", memberstack=" + memberstack
				+ ", teamboardno=" + teamboardno + "]";
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	public Member(Member m) {
		this.idx = m.idx;
		this.name = m.name;
		this.email = m.email;
		this.profile = m.profile;
		this.password = m.password;
		this.company = m.company;
		this.gitaddr = m.gitaddr;
		this.memberstack = m.memberstack;
		this.teamboardno = m.teamboardno;
	}

	public Member(String name, String email, String profile, String password, String company, String gitaddr,
			String memberstack) {
		super();
		this.name = name;
		this.email = email;
		this.profile = profile;
		this.password = password;
		this.company = company;
		this.gitaddr = gitaddr;
		this.memberstack = memberstack;
	}



	

	

}


