package org.aviation.control.queue.service.util;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.aviation.control.queue.domain.entity.DequeuedAircraftEntity;
import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.domain.repository.DequeuedAircraftRepository;
import org.aviation.control.queue.service.AircraftSizeService;
import org.aviation.control.queue.service.AircraftTypeService;
import org.aviation.control.queue.service.dto.AircraftDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AircraftServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(AircraftServiceHelper.class);

	@Autowired
	private AircraftSizeService aircraftSizeService;
	
	@Autowired
	private AircraftTypeService aircraftTypeService;
	
	@Autowired
	private DequeuedAircraftRepository repository;
	
	/**
	 * Convert EnqueuedAircraftEntity to DequeuedAircraftEntity and save the DequeuedAircraftEntity.
	 * <br>
	 * Since the DequeuedAircraftEntity sharing the same PK, only one saving thread will be successful.
	 * Use the table PK constraint to handle concurrent request, only the persisted DequeuedAircraftEntity will be returned. 
	 * <br>
	 * @param eqAircraft
	 * @return persisted DequeuedAircraftEntity, empty if entity is already persisted by another thread.
	 * @throw RollbackException if another concurrent thread is trying to save the same record.
	 */
	@Transactional(value=TxType.REQUIRES_NEW)
	public Optional<DequeuedAircraftEntity> saveDequeuedAircraftEntity(EnqueuedAircraftEntity eqAircraft) {
		
		Optional<DequeuedAircraftEntity> result = Optional.empty();
		
		if (repository.findById(eqAircraft.getId()).isPresent()) {
			LOGGER.info("Aircraft is already dequeued: {}", eqAircraft);
			
		} else {
			
			DequeuedAircraftEntity dqAircraft = new DequeuedAircraftEntity();
			BeanUtils.copyProperties(eqAircraft, dqAircraft);
			
			result = Optional.of(repository.save(dqAircraft));
		}
		return result;
	}

	/**
	 * Construct an EnqueuedAircraftEntity by 4 parameters.
	 * @param aircraftName
	 * @param type
	 * @param size
	 * 
	 * @return EnqueuedAircraftEntity
	 */
	public EnqueuedAircraftEntity convertToEnqueuedAircraftEntity(String aircraftName, AircraftType type, AircraftSize size) {
		EnqueuedAircraftEntity result = new EnqueuedAircraftEntity();
		
		result.setAircraftName(aircraftName);
		result.setAircraftSize(aircraftSizeService.getAircraftSizeEntityBySize(size));
		result.setAircraftType(aircraftTypeService.getAircraftTypeEntityByType(type));

		return result;
	}

	/**
	 * Convert an AirCraftDto object to an EnqueuedAircraftEntity.
	 * 
	 * @param dto
	 * @return EnqueuedAircraftEntity
	 */
	public EnqueuedAircraftEntity convertToEnqueuedAircraftEntity(AircraftDto dto) {
		return this.convertToEnqueuedAircraftEntity(dto.getName(), dto.getType(), dto.getSize());
	}
	
	/**
	 * Convert an EnqueuedAircraftEntity to AircraftDto.
	 * 
	 * @param entity - an EnqueuedAircraftEntity
	 * @return an AircraftDto
	 */
	public AircraftDto convertToAirCraftDto(EnqueuedAircraftEntity entity) {
		AircraftDto result = new AircraftDto();
		
		result.setId(entity.getId());
		result.setName(entity.getAircraftName());
		result.setSize(entity.getAircraftSize().getName());
		result.setType(entity.getAircraftType().getName());
		result.setEnqueueTime(entity.getEnqueuedTimestamp());
		
		return result;
	}
	
	/**
	 * Convert an DequeuedAircraftEntity to AircraftDto.
	 * 
	 * @param entity - an DequeuedAircraftEntity
	 * @return an AircraftDto
	 */
	public AircraftDto convertToAirCraftDto(DequeuedAircraftEntity entity) {
		AircraftDto result = new AircraftDto();
		
		result.setId(entity.getId());
		result.setName(entity.getAircraftName());
		result.setSize(entity.getAircraftSize().getName());
		result.setType(entity.getAircraftType().getName());
		result.setEnqueueTime(entity.getEnqueuedTimestamp());
		result.setDequeueTime(entity.getDequeuedTimestamp());
		
		return result;
	}
	
}
