package org.cghr.barshi.db.reports.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbeddedReportsId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2281534899258564360L;

	@Column(name = "reportDate", columnDefinition = "DATE")
	private Date reportDate = null;
	
	@Column(name = "areaId", columnDefinition = "BIGINT(10)")
	private Integer areaId = null;
	
	@Column(name = "userId", columnDefinition = "BIGINT(10)")
	private Integer userId = null;
	
	public EmbeddedReportsId() {
		super();
	}
	
	public EmbeddedReportsId(Date reportDate, int areaId, int userId) {
		this.reportDate = reportDate;
		
		this.areaId = areaId;
		
		this.userId = userId;
	}
	
	public Date getReportDate() {
		return this.reportDate;
	}
	
	public Integer getAreaId() {
		return this.areaId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EmbeddedReportsId) {
			if(reportDate.equals(((EmbeddedReportsId) obj).getReportDate()) && areaId.equals(((EmbeddedReportsId) obj).getAreaId()) && userId.equals(((EmbeddedReportsId) obj).getUserId())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.DAY_OF_YEAR, 0);
		calendar.set(Calendar.YEAR, 2014);
		
		return (int) (this.reportDate.getTime() - calendar.getTimeInMillis());
	}
}
