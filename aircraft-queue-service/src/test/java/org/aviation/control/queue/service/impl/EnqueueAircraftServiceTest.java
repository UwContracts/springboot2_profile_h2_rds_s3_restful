package org.aviation.control.queue.service.impl;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.service.EnqueueAircraftService;
import org.aviation.control.queue.service.config.AbstractServiceTest;
import org.aviation.control.queue.service.dto.AircraftDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnqueueAircraftServiceTest extends AbstractServiceTest {
	
    @Autowired
    private EnqueueAircraftService service;

    @Test
    public void listAllEnqueuedAircraft() {

    	List<AircraftDto> acList = service.listAllEnqueuedAircraft();
    	
    	assertThat(acList.isEmpty()).isFalse();
    	
    	AircraftDto dto = acList.get(0);
    	assertThat(dto).isNotNull();
    	assertThat(dto.getSize()).isNotNull();
    	assertThat(dto.getSize()).isEqualTo(AircraftSize.LARGE);
    	assertThat(dto.getType()).isNotNull();
    	assertThat(dto.getType()).isEqualTo(AircraftType.EMERGENCY);
    }
    
    @Test
    public void listEnqueuedAircraft() {

    	List<AircraftDto> acList = service.listEnqueuedAircraft(0, 2);
    	
    	assertThat(acList.isEmpty()).isFalse();
    	assertThat(acList.size()).isEqualTo(2);
    	
    	AircraftDto dto = acList.get(1);
    	assertThat(dto).isNotNull();
    	assertThat(dto.getSize()).isNotNull();
    	assertThat(dto.getSize()).isEqualTo(AircraftSize.SMALL);
    	assertThat(dto.getType()).isNotNull();
    	assertThat(dto.getType()).isEqualTo(AircraftType.EMERGENCY);
    }
    
}