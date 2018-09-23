package org.aviation.control.queue.service.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.domain.repository.AircraftTypeRepository;
import org.aviation.control.queue.service.AircraftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AircraftTypeServiceImpl implements AircraftTypeService {

	@Autowired
	private AircraftTypeRepository repository;
	
	@Override
	@Transactional(value=TxType.SUPPORTS)
	public AircraftTypeEntity getAircraftTypeEntityByType(AircraftType type) {
		return repository.findByNameAndActiveIsTrue(type).get();
	}

}
