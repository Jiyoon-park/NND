package kr.demonic.jpa.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;


@Table(name="tb_member")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;
}
