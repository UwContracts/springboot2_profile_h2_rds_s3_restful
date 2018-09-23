package org.aviation.control.queue.service.impl;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.aviation.control.queue.domain.entity.AircraftSizeEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.service.AircraftSizeService;
import org.aviation.control.queue.service.config.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AircraftSizeServiceTest extends AbstractServiceTest {
	
    @Autowired
    private AircraftSizeService service;

    @Test
    public void testGetAircraftSizeEntityBySize() {

    	Arrays.stream(AircraftSize.values()).forEach( size -> {
        	AircraftSizeEntity entity = service.getAircraftSizeEntityBySize(size);
        	assertThat(entity).isNotNull();
        	assertThat(entity.getName()).isEqualTo(size);
    	});
    	
    }
    
}