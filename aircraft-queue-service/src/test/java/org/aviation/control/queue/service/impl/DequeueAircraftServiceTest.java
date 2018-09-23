package org.aviation.control.queue.service.impl;
import static org.assertj.core.api.Assertions.assertThat;

import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.service.DequeueAircraftService;
import org.aviation.control.queue.service.config.AbstractServiceTest;
import org.aviation.control.queue.service.dto.AircraftDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DequeueAircraftServiceTest extends AbstractServiceTest {
	
    @Autowired
    private DequeueAircraftService service;

    @Test
    public void testDequeueAircraft() {

    	AircraftDto dequeueAircraft = service.dequeueAircraft();
    	
    	assertThat(dequeueAircraft).isNotNull();
    	assertThat(dequeueAircraft.getSize()).isNotNull();
    	assertThat(dequeueAircraft.getSize()).isEqualTo(AircraftSize.LARGE);
    	assertThat(dequeueAircraft.getType()).isNotNull();
    	assertThat(dequeueAircraft.getType()).isEqualTo(AircraftType.EMERGENCY);
    }
    
}