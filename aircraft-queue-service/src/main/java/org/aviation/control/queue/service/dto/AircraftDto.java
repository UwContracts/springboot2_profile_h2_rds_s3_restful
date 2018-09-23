package org.aviation.control.queue.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;

public class AircraftDto implements Serializable {
	
	private static final long serialVersionUID = 5358073944893517844L;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	private String id;
	
	private String name;
	
	private AircraftType type;
	
	private AircraftSize size;
	
	private String enqueueTime;
	
	private String dequeueTime;

	public String getId() {
		return id;
	}

	public void setId(String aircraftId) {
		this.id = aircraftId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AircraftType getType() {
		return type;
	}

	public void setType(AircraftType type) {
		this.type = type;
	}

	public AircraftSize getSize() {
		return size;
	}

	public void setSize(AircraftSize size) {
		this.size = size;
	}

	public String getEnqueueTime() {
		return enqueueTime;
	}

	public void setEnqueueTime(Timestamp ts) {
		this.enqueueTime = FORMATTER.format(ts.toLocalDateTime());
	}

	public void setEnqueueTime(String enqueueTime) {
		this.enqueueTime = enqueueTime;
	}

	public String getDequeueTime() {
		return dequeueTime;
	}

	public void setDequeueTime(String dequeueTime) {
		this.dequeueTime = dequeueTime;
	}
	
	public void setDequeueTime(Timestamp ts) {
		this.dequeueTime = FORMATTER.format(ts.toLocalDateTime());
	}
	
}
