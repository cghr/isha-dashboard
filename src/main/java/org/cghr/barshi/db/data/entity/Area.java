package org.cghr.barshi.db.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area {
	@Id
	@Column(name = "areaId")
	private Integer id = null;

	@Column(name = "name")
	private String name = null;

	@Column(name = "landmark")
	private String landmark = null;

	@Column(name = "pincode")
	private String pincode = null;
	
	protected Area() {
		super();
	}
	
	public Area(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Area && ((Area) obj).getId() == getId()) {
			return true;
		} else {
			return false;
		}
	}
}
