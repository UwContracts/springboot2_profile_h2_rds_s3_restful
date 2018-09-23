package org.aviation.control.queue.domain.repository;

import java.util.List;
import java.util.Optional;

import org.aviation.control.queue.domain.entity.AircraftSizeEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.springframework.data.repository.CrudRepository;

public interface AircraftSizeRepository extends CrudRepository<AircraftSizeEntity, String> {
	
	List<AircraftSizeEntity> findAllByActiveIsTrue();

	Optional<AircraftSizeEntity> findByNameAndActiveIsTrue(AircraftSize name);
	
}
