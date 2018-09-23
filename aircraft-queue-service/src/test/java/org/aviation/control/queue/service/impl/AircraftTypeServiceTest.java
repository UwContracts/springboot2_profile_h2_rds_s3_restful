package org.aviation.control.queue.service.impl;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.service.AircraftTypeService;
import org.aviation.control.queue.service.config.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AircraftTypeServiceTest extends AbstractServiceTest {
	
    @Autowired
    private AircraftTypeService service;

    @Test
    public void testGetAircraftTypeEntityByType() {

    	Arrays.stream(AircraftType.values()).forEach( type -> {
        	AircraftTypeEntity entity = service.getAircraftTypeEntityByType(type);
        	assertThat(entity).isNotNull();
        	assertThat(entity.getName()).isEqualTo(type);
    	});
    	
    }
    
}