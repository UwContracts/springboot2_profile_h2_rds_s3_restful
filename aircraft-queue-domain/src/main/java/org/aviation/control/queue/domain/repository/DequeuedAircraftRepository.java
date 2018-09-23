package org.aviation.control.queue.domain.repository;

import org.aviation.control.queue.domain.entity.DequeuedAircraftEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DequeuedAircraftRepository extends PagingAndSortingRepository<DequeuedAircraftEntity, String> {

}
