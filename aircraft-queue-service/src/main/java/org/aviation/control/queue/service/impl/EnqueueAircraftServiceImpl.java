package org.aviation.control.queue.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.domain.repository.EnqueuedAircraftRepository;
import org.aviation.control.queue.service.EnqueueAircraftService;
import org.aviation.control.queue.service.dto.AircraftDto;
import org.aviation.control.queue.service.util.AircraftServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class EnqueueAircraftServiceImpl implements EnqueueAircraftService {

	private static final Sort ENQUEUED_AC_ORDER = Sort.by(
		    new Order(Direction.DESC, "aircraftType.value"),
		    new Order(Direction.DESC, "aircraftSize.value"),
		    new Order(Direction.ASC, "enqueuedTimestamp")
		  );
	
	@Autowired
	private EnqueuedAircraftRepository repository;
	
	@Autowired
	private AircraftServiceHelper aircraftServiceHelper;
	
	@Override
	@Transactional(value=TxType.REQUIRED)
	public EnqueuedAircraftEntity enqueueAircraft(String aircraftName, AircraftType type, AircraftSize size) {
		
		EnqueuedAircraftEntity entity = aircraftServiceHelper.convertToEnqueuedAircraftEntity(aircraftName, type, size);
		
		repository.save(entity);
		
		return entity;
	}

	@Override
	@Transactional(value=TxType.SUPPORTS)
	public List<AircraftDto> listAllEnqueuedAircraft() {
		
		List<EnqueuedAircraftEntity> entityList = (List<EnqueuedAircraftEntity>) repository.findAll(ENQUEUED_AC_ORDER);
		
		return entityList.stream().map(entity -> {
			return aircraftServiceHelper.convertToAirCraftDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(value=TxType.SUPPORTS)
	public List<AircraftDto> listEnqueuedAircraft(int page, int size) {

		List<EnqueuedAircraftEntity> entityList = repository.findAll(PageRequest.of(page, size, ENQUEUED_AC_ORDER)).stream().collect(Collectors.toList());
		
		return entityList.stream().map(entity -> {
			return aircraftServiceHelper.convertToAirCraftDto(entity);
		}).collect(Collectors.toList());
	}
	
}
