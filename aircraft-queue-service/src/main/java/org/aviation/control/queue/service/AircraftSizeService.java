package org.aviation.control.queue.service;

import org.aviation.control.queue.domain.entity.AircraftSizeEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;

public interface AircraftSizeService {
	
	AircraftSizeEntity getAircraftSizeEntityBySize(AircraftSize type);

}
