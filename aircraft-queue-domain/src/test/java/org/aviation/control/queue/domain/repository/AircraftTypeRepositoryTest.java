package org.aviation.control.queue.domain.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.aviation.control.queue.domain.AbstractRepositoryTest;
import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AircraftTypeRepositoryTest extends AbstractRepositoryTest {
	
    @Autowired
    private AircraftTypeRepository repository;

    @Test
    public void findAllByActiveIsTrue() {
    	Optional<AircraftTypeEntity> product = repository.findById("100");
    	assertThat(product.isPresent()).isTrue();
    	assertThat(product.get().getId()).isEqualTo("100");
    	
    	List<AircraftTypeEntity> logList = repository.findAllByActiveIsTrue();
    	assertThat(logList.isEmpty()).isFalse();
    	
    	logList.forEach( type -> {
        	assertThat(type.getActive()).isTrue();
        	assertThat(type.getName()).isNotNull();
    	});
    	
    }
    
    @Test
    public void findByNameAndActiveIsTrue() {
    	Optional<AircraftTypeEntity> entityOpt = repository.findByNameAndActiveIsTrue(AircraftType.EMERGENCY);
    	
    	assertThat(entityOpt.isPresent()).isTrue();
    	assertThat(entityOpt.get().getName()).isEqualTo(AircraftType.EMERGENCY);
    }
    
}