// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.web.curation.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uid;

    @JsonIgnore
    private String password;
    private String email;
    private String tel;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createDate;

    public User() {
		// TODO Auto-generated constructor stub
	}
    
	public User(String uid, String email, String password, String tel) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.tel = tel;
		
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [uid=").append(uid).append(", password=").append(password).append(", email=").append(email)
				.append(", tel=").append(tel).append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}
	
}
