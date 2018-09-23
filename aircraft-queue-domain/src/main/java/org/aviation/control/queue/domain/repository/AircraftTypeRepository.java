package org.aviation.control.queue.domain.repository;

import java.util.List;
import java.util.Optional;

import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.springframework.data.repository.CrudRepository;

public interface AircraftTypeRepository extends CrudRepository<AircraftTypeEntity, String> {

	List<AircraftTypeEntity> findAllByActiveIsTrue();

	Optional<AircraftTypeEntity> findByNameAndActiveIsTrue(AircraftType name);
	
}
