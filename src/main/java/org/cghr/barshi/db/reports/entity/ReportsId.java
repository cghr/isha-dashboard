package org.cghr.barshi.db.reports.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

import org.cghr.barshi.db.entity.Team;

@Embeddable
public class ReportsId implements Serializable {
	
	/**
	 * serialVersionUID for serializability
	 */
	private static final long serialVersionUID = -1400148065994178838L;

	@Column(name = "id", columnDefinition="BIGINT(10)")
	private Integer id = null;

	@Column(name = "reportDate", columnDefinition="DATE")
	private Date reportDate = null;
	
	public ReportsId(int id, Date date) {
		this.id = id;
		this.reportDate = getAdjustedDate(date);
	}

	public ReportsId() {
		super();
	}
	
	@PrePersist
	protected void adjustDate() {
		reportDate = getAdjustedDate(reportDate);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public void setReportDate(Date reportDate) {
		this.reportDate = getAdjustedDate(reportDate);
	}
	
	public Date getDate() {
		return this.reportDate;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} else if(obj instanceof ReportsId) {
			if(this.reportDate == null) {
				throw new RuntimeException("Illegal Operation. reportDate is null");
			}
			boolean idsEqual = this.id.equals(((ReportsId) obj).getId());
			
			Date thisDate = getAdjustedDate(reportDate);
			
			Date date = getAdjustedDate(((ReportsId) obj).getDate());
			
			long diff = thisDate.getTime() - date.getTime();
			
			return idsEqual && diff == 0;
		} else {
			return false;
		}
	}
	
	private Date getAdjustedDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		
		return calendar.getTime();
	}

	@Override
	public int hashCode() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(reportDate);
		
		return cal.get(Calendar.DAY_OF_MONTH) + this.id;
	}
}
