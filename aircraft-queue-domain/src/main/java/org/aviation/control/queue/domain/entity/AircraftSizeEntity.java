package org.aviation.control.queue.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.util.AircraftSizeConverter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="AIRCRAFT_SIZE", indexes = {
		@Index(name = "IDX_AC_SIZE_NAME", columnList = "SIZE_NAME, ACTIVE"),
		@Index(name = "IDX_AC_SIZE_VALUE", columnList = "SIZE_VALUE, ACTIVE")
})
public class AircraftSizeEntity implements Serializable {

	private static final long serialVersionUID = 5783123378074143260L;

	@Id 
	@Column(name="ID", length=50)
	private String id;

	@Column(name="SIZE_NAME", length=100, nullable=false)
	@Convert(converter = AircraftSizeConverter.class)
	private AircraftSize name;
	
	@Column(name="SIZE_VALUE", length=100, nullable=false)
	private String value;
	
	@Column(name="DESCRIPTION", length=50)
	private String description;
	
	@Column(name="ACTIVE", length=1, nullable=false)
	@Type(type="yes_no")
	private Boolean active = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AircraftSize getName() {
		return name;
	}

	public void setName(AircraftSize name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
