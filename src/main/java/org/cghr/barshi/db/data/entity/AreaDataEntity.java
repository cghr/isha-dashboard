package org.cghr.barshi.db.data.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.cghr.barshi.db.entity.Area;
import org.cghr.barshi.db.entityinterface.AreaInterface;

@Entity
@Table(name = "area")
public class AreaDataEntity {
	@Id
	@Column(name = "areaId")
	private Integer id = null;

	@Embedded
	private Area area = new Area();
	
	protected AreaDataEntity() {
		super();
	}
	
	public AreaDataEntity(int id) {
		this.id = id;
		area.setId(id);
	}
	
	@PostLoad
	public void setAreaId() {
		area.setId(this.id);
	}

	public Integer getId() {
		return id;
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
	
	public Area getArea() {
		return area;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AreaDataEntity && ((AreaDataEntity) obj).getId() == getId()) {
			return true;
		} else {
			return false;
		}
	}
}
