package org.aviation.control.queue.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.domain.util.AircraftTypeConverter;
import org.hibernate.annotations.Type;

@Entity
@Table(name="AIRCRAFT_TYPE", indexes = {
		@Index(name = "IDX_AC_TYPE_NAME", columnList = "TYPE_NAME, ACTIVE"),
		@Index(name = "IDX_AC_TYPE_VALUE", columnList = "TYPE_VALUE, ACTIVE")
})
public class AircraftTypeEntity implements Serializable {

	private static final long serialVersionUID = 8036011099999637703L;

	@Id 
	@Column(name="ID", length=50)
	private String id;

	@Column(name="TYPE_NAME", length=100, nullable=false)
	@Convert(converter = AircraftTypeConverter.class)
	private AircraftType name;
	
	@Column(name="TYPE_VALUE", length=100, nullable=false)
	private String value;
	
	@Column(name="DESCRIPTION", length=50, nullable=true)
	private String description;
	
	@Column(name="ACTIVE", length=1, nullable=false)
	@Type(type="yes_no")
	private Boolean active = true;
	
	@OneToMany(mappedBy="aircraftType", fetch=FetchType.LAZY)
	private List<EnqueuedAircraftEntity> enqueuedAircrafts;
	
	@OneToMany(mappedBy="aircraftType", fetch=FetchType.LAZY)
	private List<DequeuedAircraftEntity> dequeuedAircrafts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AircraftType getName() {
		return name;
	}

	public void setName(AircraftType name) {
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

	public List<EnqueuedAircraftEntity> getEnqueuedAircrafts() {
		return enqueuedAircrafts;
	}

	public void setEnqueuedAircrafts(List<EnqueuedAircraftEntity> enqueuedAircrafts) {
		this.enqueuedAircrafts = enqueuedAircrafts;
	}

	public List<DequeuedAircraftEntity> getDequeuedAircrafts() {
		return dequeuedAircrafts;
	}

	public void setDequeuedAircrafts(List<DequeuedAircraftEntity> dequeuedAircrafts) {
		this.dequeuedAircrafts = dequeuedAircrafts;
	}
	
}
