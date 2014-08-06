package org.cghr.barshi.db.reports.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.entityinterface.AreaInterface;

@Entity
@Table(name = "rep_area")
public class AreaReportsEntity {
	@EmbeddedId
	private ReportsId id = new ReportsId();

	@Embedded
	private Area area = new Area();
	
	protected AreaReportsEntity() {
		super();
	}
	
	public AreaReportsEntity(int id, Date reportDate, Area area) {
		this.id.setId(id);
		this.id.setReportDate(reportDate);
		this.area = area;
	}

	public ReportsId getId() {
		return this.id;
	}

	public String getName() {
		return area.getName();
	}

	public void setName(String name) {
		area.setName(name);
	}

	public String getLandmark() {
		return area.getLandmark();
	}

	public void setLandmark(String landmark) {
		area.setLandmark(landmark);
	}

	public String getPincode() {
		return area.getPincode();
	}

	public void setPincode(String pincode) {
		area.setPincode(pincode);
	}
	
	private Area getArea() {
		return area;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} else if(obj instanceof AreaReportsEntity) {
			return this.id.equals(((AreaReportsEntity) obj).getId());
		} else {
			return false;
		}
	}
}
