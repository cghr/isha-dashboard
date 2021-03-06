package org.cghr.barshi.db.reports.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.cghr.barshi.db.entity.User;

@Entity
@Table(name = "rep_user")
public class UserReportsEntity {
	@EmbeddedId
	private ReportsId id = new ReportsId();

	@Transient
	private String clearPassword = null;

	@Embedded
	private User user = new User();

	@Column(name = "isPresent")
	boolean isPresent = false;

	@Column(name = "absent_reason")
	private String absentReason = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "")
	private Set<EnumerationReportEntity> enumerationReport = new HashSet<EnumerationReportEntity>();

	protected UserReportsEntity() {
		super();
	}

	public UserReportsEntity(int id, Date reportDate, User user) {
		this.id.setId(id);
		this.id.setReportDate(reportDate);
		this.user = user;
	}
	
	public Collection<EnumerationReportEntity> getEnumerationReportEntity() {
		Set<EnumerationReportEntity> enumerationReportEntitySet = new HashSet<EnumerationReportEntity>();
		enumerationReportEntitySet.addAll(enumerationReport);
		
		return enumerationReportEntitySet;
	}

	public ReportsId getId() {
		return this.id;
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

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public boolean isPresent() {
		return this.isPresent;
	}

	public void setAbsentReason(String absentReason) {
		this.absentReason = absentReason;
	}

	public String getAbsentReason() {
		return this.absentReason;
	}
	
	public User getSurveyor() {
		return this.user;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj instanceof UserReportsEntity) {
			return this.id.equals(((UserReportsEntity) obj).getId());
		} else {
			return false;
		}
	}
}
