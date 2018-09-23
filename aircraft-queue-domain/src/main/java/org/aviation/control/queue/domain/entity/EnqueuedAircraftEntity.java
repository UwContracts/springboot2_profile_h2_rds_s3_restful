package org.aviation.control.queue.domain.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ENQUEUED_AIRCRAFT", indexes = {
		@Index(name = "IDX_AC_ENQUEUE_ORDER", columnList = "AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID, ENQUEUED_TIMESTAMP")
})
public class EnqueuedAircraftEntity extends AbstractUUIDBaseEntity {

	private static final long serialVersionUID = 3604748848705214247L;

	@Id 
	@Column(name="ID", length=50)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	@Column(name="ENQUEUED_TIMESTAMP")
	@CreationTimestamp
	private Timestamp enqueuedTimestamp;
	
	@Column(name="AIRCRAFT_NAME", length=500)
	private String aircraftName;
	
	@ManyToOne
	@JoinColumn(name = "AIRCRAFT_TYPE_ID", nullable = false)
	private AircraftTypeEntity aircraftType;
	
	@ManyToOne
	@JoinColumn(name = "AIRCRAFT_SIZE_ID", nullable = false)
	private AircraftSizeEntity aircraftSize;
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getEnqueuedTimestamp() {
		return enqueuedTimestamp;
	}
	public void setEnqueuedTimestamp(Timestamp enqueuedTimestamp) {
		this.enqueuedTimestamp = enqueuedTimestamp;
	}
	public String getAircraftName() {
		return aircraftName;
	}
	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}
	public AircraftTypeEntity getAircraftType() {
		return aircraftType;
	}
	public void setAircraftType(AircraftTypeEntity aircraftType) {
		this.aircraftType = aircraftType;
	}
	public AircraftSizeEntity getAircraftSize() {
		return aircraftSize;
	}
	public void setAircraftSize(AircraftSizeEntity aircraftSize) {
		this.aircraftSize = aircraftSize;
	}
	
}
