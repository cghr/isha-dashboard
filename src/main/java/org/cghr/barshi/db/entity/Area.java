package org.cghr.barshi.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.cghr.barshi.db.entityinterface.AreaInterface;

@Embeddable
public class Area implements AreaInterface {
	@Transient
	private Integer id = null;
	
	@Column(name = "name")
	private String name = null;

	@Column(name = "landmark")
	private String landmark = null;

	@Column(name = "pincode")
	private String pincode = null;
	
	@Column(name = "districtId")
	private String districtId = null;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getLandmark() {
		return landmark;
	}

	@Override
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@Override
	public String getPincode() {
		return pincode;
	}

	@Override
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String getDistrictId() {
		return this.districtId;
	}

	@Override
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
}
