package org.aviation.control.queue.service.util;

import java.util.ArrayList;
import java.util.List;

import org.aviation.control.queue.domain.entity.AircraftSizeEntity;
import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.springframework.stereotype.Service;

@Service
public class AircraftSampleDataProvider {

	public List<AircraftSizeEntity> getAircraftSize() {
		
		List<AircraftSizeEntity> entityList = new ArrayList<>();
		AircraftSizeEntity entity = new AircraftSizeEntity();
		entity.setActive(true);
		entity.setId("100");
		entity.setName(AircraftSize.SMALL);
		entity.setValue("100");
		entity.setDescription("2018-08-23");
		entityList.add(entity);
		
		entity = new AircraftSizeEntity();
		entity.setActive(true);
		entity.setId("200");
		entity.setName(AircraftSize.LARGE);
		entity.setValue("200");
		entity.setDescription("2018-08-23");
		entityList.add(entity);
		
		return entityList;
	}
	
	public List<AircraftTypeEntity> getAircraftType() {
		
		List<AircraftTypeEntity> entityList = new ArrayList<>();
		AircraftTypeEntity entity = new AircraftTypeEntity();
		entity.setActive(true);
		entity.setId("100");
		entity.setName(AircraftType.CARGO);
		entity.setValue("100");
		entity.setDescription("2018-08-23");
		entityList.add(entity);
		
		entity = new AircraftTypeEntity();
		entity.setActive(true);
		entity.setId("200");
		entity.setName(AircraftType.PASSENGER);
		entity.setValue("200");
		entity.setDescription("2018-08-23");
		entityList.add(entity);
		
		entity = new AircraftTypeEntity();
		entity.setActive(true);
		entity.setId("300");
		entity.setName(AircraftType.VIP);
		entity.setValue("300");
		entity.setDescription("2018-08-23");
		entityList.add(entity);
		
		entity = new AircraftTypeEntity();
		entity.setActive(true);
		entity.setId("400");
		entity.setName(AircraftType.EMERGENCY);
		entity.setValue("400");
		entity.setDescription("2018-08-23");
		entityList.add(entity);
		
		return entityList;
	}
	
}
