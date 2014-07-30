package org.cghr.barshi.db.reports.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.entityinterface.AreaInterface;

@Entity
@Table(name = "rep_area")
public class AreaReportsEntity implements AreaInterface {
	@Id
	@Column(name = "areaId")
	private Integer id = null;

	@Embedded
	private Area area = new Area();
	
	protected AreaReportsEntity() {
		super();
	}
	
	public AreaReportsEntity(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return area.getName();
	}

	@Override
	public void setName(String name) {
		area.setName(name);
	}

	@Override
	public String getLandmark() {
		return area.getLandmark();
	}

	@Override
	public void setLandmark(String landmark) {
		area.setLandmark(landmark);
	}

	@Override
	public String getPincode() {
		return area.getPincode();
	}

	@Override
	public void setPincode(String pincode) {
		area.setPincode(pincode);
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	private Area getArea() {
		return area;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AreaReportsEntity && ((AreaReportsEntity) obj).getId() == getId()) {
			return true;
		} else {
			return false;
		}
	}
}
