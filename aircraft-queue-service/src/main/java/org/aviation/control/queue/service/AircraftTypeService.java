package org.aviation.control.queue.service;

import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.enums.AircraftType;

public interface AircraftTypeService {
	
	AircraftTypeEntity getAircraftTypeEntityByType(AircraftType type);

}
