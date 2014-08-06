package org.cghr.barshi.db.reports.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name = "rep_report")
public class ReportEntity {
	@Id
	private Date id = new Date();
	
	@Column(name = "isOk")
	private Boolean isOk = null;
	
	@Column(name = "isSynchronized")
	private Boolean isSynchronized = null;
	
	@Column(name = "problem")
	private String problem = null;
	
	protected ReportEntity() {
		super();
	}
	
	public ReportEntity(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		this.id = cal.getTime();
	}
	
	public Date getId() {
		return this.id;
	}
	
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
	public Boolean isOk() {
		return this.isOk;
	}
	
	public void setSynchronized(Boolean isSynchronized) {
		this.isSynchronized = isSynchronized;
	}
	
	public Boolean isSynchronized() {
		return this.isSynchronized;
	}
	
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	public String getProblem() {
		return this.problem;
	}
	
	@Transient
	private String validationError = null;
	
	public boolean isValid() {
		if(isOk == null) {
			validationError = "Please select an option for report validity";
			return false;
		}
		
		
		if(!isOk) {
			if(isSynchronized == null || !isSynchronized) {
				validationError = "Please synchronize all the surveyor's laptops before submitting the report";
				return false;
			}
			
			if(problem == null || problem.length() < 10) {
				validationError = "Please describe the problem";
				return false;
			}
		}
		
		return true;
	}
	
	public String getValidationError() {
		return this.validationError;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} else if (obj instanceof ReportEntity) {
			return id.equals(((ReportEntity) obj).getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
