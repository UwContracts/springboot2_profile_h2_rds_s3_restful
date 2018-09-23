package org.aviation.control.queue.domain.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="DEQUEUED_AIRCRAFT", indexes = {
		@Index(name = "IDX_AC_DEQUEUE_ORDER", columnList = "ENQUEUED_TIMESTAMP, AIRCRAFT_TYPE_ID, AIRCRAFT_SIZE_ID")
})
public class DequeuedAircraftEntity extends AbstractUUIDBaseEntity {

	private static final long serialVersionUID = 3795693348288329249L;

	@Id 
	@Column(name="ID", length=50)
	private String id;

	@Column(name="DEQUEUED_TIMESTAMP")
	@CreationTimestamp
	private Timestamp dequeuedTimestamp;
	
	@Column(name="ENQUEUED_TIMESTAMP")
	private Timestamp enqueuedTimestamp;
	
	@Column(name="AIRCRAFT_NAME", length=500)
	private String aircraftName;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "AIRCRAFT_TYPE_ID", nullable = false)
	private AircraftTypeEntity aircraftType;
	
	@ManyToOne(cascade=CascadeType.MERGE)
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
	public Timestamp getDequeuedTimestamp() {
		return dequeuedTimestamp;
	}
	public void setDequeuedTimestamp(Timestamp dequeuedTimestamp) {
		this.dequeuedTimestamp = dequeuedTimestamp;
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
