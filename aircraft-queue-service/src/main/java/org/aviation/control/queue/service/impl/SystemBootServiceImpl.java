package org.aviation.control.queue.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.domain.repository.EnqueuedAircraftRepository;
import org.aviation.control.queue.service.EnqueueAircraftService;
import org.aviation.control.queue.service.SystemBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemBootServiceImpl implements SystemBootService {

	@Autowired
	private EnqueuedAircraftRepository repository;
	
	@Autowired
	private EnqueueAircraftService service;
	
	@Override
	@Transactional
	public Boolean start() {
		
		Optional<EnqueuedAircraftEntity> opt = repository.findById("100");
		if (!opt.isPresent()) {
			service.enqueueAircraft("Emergency, Large, 100", AircraftType.EMERGENCY, AircraftSize.LARGE);
		}
		opt = repository.findById("200");
		if (!opt.isPresent()) {
			service.enqueueAircraft("Emergency, Small, 200", AircraftType.EMERGENCY, AircraftSize.SMALL);
		}
		opt = repository.findById("300");
		if (!opt.isPresent()) {
			service.enqueueAircraft("VIP, Large, 300", AircraftType.VIP, AircraftSize.LARGE);
		}
		opt = repository.findById("400");
		if (!opt.isPresent()) {
			service.enqueueAircraft("VIP, Small, 400", AircraftType.VIP, AircraftSize.SMALL);
		}
		opt = repository.findById("500");
		if (!opt.isPresent()) {
			service.enqueueAircraft("Passenger, Large, 500", AircraftType.PASSENGER, AircraftSize.LARGE);
		}
		opt = repository.findById("600");
		if (!opt.isPresent()) {
			service.enqueueAircraft("Passenger, Small, 600", AircraftType.PASSENGER, AircraftSize.SMALL);
		}
		opt = repository.findById("700");
		if (!opt.isPresent()) {
			service.enqueueAircraft("Cargo, Large, 700", AircraftType.CARGO, AircraftSize.LARGE);
		}
		opt = repository.findById("800");
		if (!opt.isPresent()) {
			service.enqueueAircraft("Cargo, Small, 800", AircraftType.CARGO, AircraftSize.SMALL);
		}
		
		return true;
	}

}
