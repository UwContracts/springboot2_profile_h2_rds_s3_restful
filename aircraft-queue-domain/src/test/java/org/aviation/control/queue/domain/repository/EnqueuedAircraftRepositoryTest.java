package org.aviation.control.queue.domain.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.aviation.control.queue.domain.AbstractRepositoryTest;
import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnqueuedAircraftRepositoryTest extends AbstractRepositoryTest {
	
    @Autowired
    private EnqueuedAircraftRepository repository;

    @Test
    public void testFindFirstByOrderByAircraftTypeValueDescAircraftSizeValueDescEnqueuedTimestampAsc() {
    	Optional<EnqueuedAircraftEntity> product = repository.findById("100");
    	assertThat(product.isPresent()).isTrue();
    	assertThat(product.get().getId()).isEqualTo("100");
    	
    	EnqueuedAircraftEntity entity = repository.findFirstByOrderByAircraftTypeValueDescAircraftSizeValueDescEnqueuedTimestampAsc();
    	
    	assertThat(entity.getAircraftType().getName()).isEqualTo(AircraftType.EMERGENCY);
    	assertThat(entity.getAircraftSize().getName()).isEqualTo(AircraftSize.LARGE);
    }
    
}