package org.aviation.control.queue.domain.repository;

import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnqueuedAircraftRepository extends PagingAndSortingRepository<EnqueuedAircraftEntity, String> {

	EnqueuedAircraftEntity findFirstByOrderByAircraftTypeValueDescAircraftSizeValueDescEnqueuedTimestampAsc();

}
