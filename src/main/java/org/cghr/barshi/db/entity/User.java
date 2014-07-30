package org.cghr.barshi.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.cghr.barshi.db.entityinterface.UserInterface;

@Embeddable
public class User implements UserInterface {
	
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
	
	@PrePersist
	private void setPassword() {
		if (getClearPassword() != null && getClearPassword().length() > 0) {
			setPassword(getClearPassword());
		}
	}

	public String getClearPassword() {
		return this.clearPassword;
	}

	@Override
	public void setClearPassword(String clearPassword) {
		this.clearPassword = clearPassword;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getRole() {
		return this.role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}
}
