package org.cghr.barshi.db.data.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.cghr.barshi.db.entity.User;

@Entity
@Table(name = "user")
public class UserDataEntity {
	@Id
	@Column(name = "id")
	private Integer id = null;
	
	@Transient
	private String clearPassword = null;

	@Embedded
	private User user = new User();
	
	protected UserDataEntity() {
		super();
	}

	public UserDataEntity(int id) {
		this.id = id;
		user.setId(id);
	}
	
	@PostLoad
	public void setUserId() {
		user.setId(this.id);
	}

	public Integer getId() {
		return id;
	}

	public String getClearPassword() {
		return user.getClearPassword();
	}

	public void setClearPassword(String clearPassword) {
		user.setClearPassword(clearPassword);
	}

	private String getPassword() {
		return user.getPassword();
	}

	private void setPassword(String password) {
		user.setPassword(password);
	}

	public String getUsername() {
		return user.getUsername();
	}

	public void setUsername(String username) {
		user.setUsername(username);
	}

	public String getName() {
		return user.getName();
	}

	public void setName(String name) {
		user.setName(name);
	}

	public String getRole() {
		return user.getRole();
	}

	public void setRole(String role) {
		user.setRole(role);
	}
	
	public User getUser() {
		return this.user;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserDataEntity && ((UserDataEntity) obj).getId() == getId()) {
			return true;
		} else {
			return false;
		}
	}
}
