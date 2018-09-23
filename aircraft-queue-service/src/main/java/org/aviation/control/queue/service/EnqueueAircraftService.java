package org.aviation.control.queue.service;

import java.util.List;

import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.service.dto.AircraftDto;

public interface EnqueueAircraftService {

	EnqueuedAircraftEntity enqueueAircraft(String aircraftName, AircraftType type, AircraftSize size);
	
	List<AircraftDto> listAllEnqueuedAircraft();
	
	List<AircraftDto> listEnqueuedAircraft(int page, int size);
	
}
