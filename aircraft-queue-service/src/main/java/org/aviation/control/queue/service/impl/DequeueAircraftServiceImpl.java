package org.aviation.control.queue.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.RollbackException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.aviation.control.queue.domain.entity.DequeuedAircraftEntity;
import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.repository.DequeuedAircraftRepository;
import org.aviation.control.queue.domain.repository.EnqueuedAircraftRepository;
import org.aviation.control.queue.service.DequeueAircraftService;
import org.aviation.control.queue.service.dto.AircraftDto;
import org.aviation.control.queue.service.util.AircraftServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class DequeueAircraftServiceImpl implements DequeueAircraftService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DequeueAircraftServiceImpl.class);

	private static final Sort DEQUEUED_AC_ORDER = Sort.by(new Order(Direction.DESC, "dequeuedTimestamp"));
	
    @Value("${db_locking_retry_count:5}")
	private int retryCount;
	
    @Value("${db_locking_retry_gap:30}")
	private int retryGap;
	
	@Autowired
	private EnqueuedAircraftRepository enqueueRepository;
	
	@Autowired
	private DequeuedAircraftRepository dequeueRepository;
	
	@Autowired
	private AircraftServiceHelper dequeueAircraftServiceHelper;
	
	@Autowired
	private AircraftServiceHelper acHelper;
	
	@Override
	@Transactional(value=TxType.REQUIRED)
	public AircraftDto dequeueAircraft() {
		
		AircraftDto result = null;
		
		for (int i = 0; i < retryCount; i++) {	// Try to dequeue in a max of (retryCount) times
			
			// Find the first enqueued aircraft based on its type, size and time.
	    	EnqueuedAircraftEntity enqueuedEntity = enqueueRepository.findFirstByOrderByAircraftTypeValueDescAircraftSizeValueDescEnqueuedTimestampAsc();
	    	if (enqueuedEntity != null) {
				try {
					
					// Convert enqueued aircraft to save into dequeued AC table, only 1 thread will success as PK is unique.
					Optional<DequeuedAircraftEntity> entityOpt = dequeueAircraftServiceHelper.saveDequeuedAircraftEntity(enqueuedEntity);
					if (entityOpt.isPresent()) {
						enqueueRepository.delete(enqueuedEntity); // Remove the enqueued AC so that it won't picked again.
						
						result = acHelper.convertToAirCraftDto(entityOpt.get());
						break;
					}
				} catch (RollbackException e) {
					LOGGER.info("Aircraft is already dequeued by another Thread: {}", enqueuedEntity);
					Random rand = new Random();
					try {
						Thread.sleep(rand.nextInt(retryGap) + 1); // Concurrent conflict occurred, pause to let another thread to finish.
					} catch (InterruptedException e1) {
					}
				} 
	    	} else {
	    		break; // Return when there is no more AC in queue.
	    	}
		}
		return result;
	}

	@Override
	@Transactional(value=TxType.SUPPORTS)
	public List<AircraftDto> listAllDequeuedAircraft() {
		
		List<DequeuedAircraftEntity> entityList = (List<DequeuedAircraftEntity>) dequeueRepository.findAll(DEQUEUED_AC_ORDER);
		
		return entityList.stream().map(entity -> {
			return acHelper.convertToAirCraftDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(value=TxType.SUPPORTS)
	public List<AircraftDto> listDequeuedAircraft(int page, int size) {

		List<DequeuedAircraftEntity> entityList = dequeueRepository.findAll(PageRequest.of(page, size, DEQUEUED_AC_ORDER)).stream().collect(Collectors.toList());
		
		return entityList.stream().map(entity -> {
			return acHelper.convertToAirCraftDto(entity);
		}).collect(Collectors.toList());
	}
	
}
