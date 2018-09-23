package org.aviation.control.queue.service;

import java.util.List;

import org.aviation.control.queue.service.dto.AircraftDto;

public interface DequeueAircraftService {

	AircraftDto dequeueAircraft();
	
	List<AircraftDto> listAllDequeuedAircraft();
	
	List<AircraftDto> listDequeuedAircraft(int page, int size);
	
}
