package org.aviation.control.queue.service.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.aviation.control.queue.domain.entity.AircraftSizeEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.repository.AircraftSizeRepository;
import org.aviation.control.queue.service.AircraftSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AircraftSizeServiceImpl implements AircraftSizeService {

	@Autowired
	private AircraftSizeRepository repository;
	
	@Override
	@Transactional(value=TxType.SUPPORTS)
	public AircraftSizeEntity getAircraftSizeEntityBySize(AircraftSize size) {
		return repository.findByNameAndActiveIsTrue(size).get();
	}

}
