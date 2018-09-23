package org.aviation.control.queue.domain.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.aviation.control.queue.domain.AbstractRepositoryTest;
import org.aviation.control.queue.domain.entity.AircraftSizeEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AircraftSizeRepositoryTest extends AbstractRepositoryTest {
	
    @Autowired
    private AircraftSizeRepository repository;

    @Test
    public void findAllByActiveIsTrue() {
    	Optional<AircraftSizeEntity> product = repository.findById("100");
    	assertThat(product.isPresent()).isTrue();
    	assertThat(product.get().getId()).isEqualTo("100");
    	
    	List<AircraftSizeEntity> logList = repository.findAllByActiveIsTrue();
    	assertThat(logList.isEmpty()).isFalse();
    	
    	logList.forEach( type -> {
        	assertThat(type.getActive()).isTrue();
        	assertThat(type.getName()).isNotNull();
    	});
    	
    }
    
    @Test
    public void findByNameAndActiveIsTrue() {
    	Optional<AircraftSizeEntity> entityOpt = repository.findByNameAndActiveIsTrue(AircraftSize.LARGE);
    	
    	assertThat(entityOpt.isPresent()).isTrue();
    	assertThat(entityOpt.get().getName()).isEqualTo(AircraftSize.LARGE);
    }
    
}