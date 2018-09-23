package org.aviation.control.queue.domain.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aviation.control.queue.domain.AbstractRepositoryTest;
import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.entity.DequeuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.CollectionUtils;

public class DequeuedAircraftRepositoryTest extends AbstractRepositoryTest {
	
    @Autowired
    private DequeuedAircraftRepository repository;

    @Test
    public void testFindAll() {
    	Optional<DequeuedAircraftEntity> product = repository.findById("100");
    	assertThat(product.isPresent()).isTrue();
    	assertThat(product.get().getId()).isEqualTo("100");
    	
    	Page<DequeuedAircraftEntity> acPage = repository.findAll(PageRequest.of(0, 20, Sort.by(Direction.ASC, "dequeuedTimestamp")));
    	
    	List<DequeuedAircraftEntity> acList = acPage.stream().collect(Collectors.toList());
		assertThat(CollectionUtils.isEmpty(acList)).isFalse();
    	
        assertThat(acList.get(0)).extracting("aircraftType").isNotNull();
        AircraftTypeEntity aircraftType = acList.get(0).getAircraftType();
		assertThat(aircraftType.getName()).isEqualTo(AircraftType.EMERGENCY);
    }
    
}