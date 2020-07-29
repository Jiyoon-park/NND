package com.ssafy.nnd.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Valid
@ToString
public class SignupRequest {
	
    @ApiModelProperty(required = true)
    @NotNull
    String name;
    
    @ApiModelProperty(required = true)
    @NotNull
    String email;
    
    @ApiModelProperty(required = true)
    String profile;
    
    @ApiModelProperty(required = true)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    String password;
    
    @ApiModelProperty(required = true)
    String company;
    
    @ApiModelProperty(required = true)
    String gitaddr;
    
    @ApiModelProperty(required = true)
    String memberstack;
    
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setCompany(String company) {
		this.company = company;
	}


}
