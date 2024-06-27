package br.com.bytequest.userservice.entity;

import br.com.bytequest.userservice.dto.UpdateUserDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password_hash;

	public User() {
	}

	public User(String name, String email, String password_hash) {
		this.name = name;
		this.email = email;
		this.password_hash = password_hash;
	}

	public void update(UpdateUserDTO json){
		this.name = json.name() != null ? json.name() : this.name;
		this.email = json.email() != null ? json.email() : this.email;
		this.password_hash = json.password_hash() != null ? json.password_hash() : this.password_hash;
	}

	public Long getId() {
		return id;
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

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User user)) return false;
		return Objects.equals(id, user.id) && Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email);
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", password_hash='" + password_hash + '\'' +
			'}';
	}


}
