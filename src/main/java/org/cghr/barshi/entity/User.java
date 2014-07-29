package org.cghr.barshi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "id")
	private Integer id = null;

	@Column(name = "username", unique = true)
	private String username = null;

	@Transient
	private String clearPassword = null;

	@Column(name = "password")
	private String password = null;

	@Column(name = "name")
	private String name = null;

	@Column(name = "role")
	private String role = null;

	@Transient
	private boolean isPasswordSet = false;

	@PrePersist
	private void setPassword() {
		if (getClearPassword() != null && getClearPassword().length() > 0) {
			setPassword(getClearPassword());
		}
	}

	protected User() {
		super();
	}

	public User(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getClearPassword() {
		return this.clearPassword;
	}

	public void setClearPassword(String clearPassword) {
		this.clearPassword = clearPassword;
	}

	private String getPassword() {
		return this.password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User && ((User) obj).getId() == getId()) {
			return true;
		} else {
			return false;
		}
	}
}
