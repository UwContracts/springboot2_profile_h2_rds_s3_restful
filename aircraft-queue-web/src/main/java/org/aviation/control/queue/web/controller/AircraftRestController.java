package org.aviation.control.queue.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.service.DequeueAircraftService;
import org.aviation.control.queue.service.EnqueueAircraftService;
import org.aviation.control.queue.service.SystemBootService;
import org.aviation.control.queue.service.dto.AircraftDto;
import org.aviation.control.queue.service.util.AircraftServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AircraftRestController {
    
	@Autowired
	private AircraftServiceHelper aircraftServiceHelper;
	
	@Autowired
	private DequeueAircraftService dequeueService;
	
	@Autowired
	private EnqueueAircraftService enqueueService;
	
	@Autowired
	private SystemBootService systemBootService;
	
	@RequestMapping(value = "/boot", method = {RequestMethod.GET, RequestMethod.POST}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
    public Map<String, String> productList() {
		
		Map<String, String> result = new HashMap<>();
		result.put("result", systemBootService.start() ? "SUCCESS" : "FAIL");
		
        return result;
    }
	
	@RequestMapping(value = "/enqueue", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
	public AircraftDto enqueue(
			@RequestParam(name = "name", required = false) String name, 
			@RequestParam(name = "type", required = false) String type, 
			@RequestParam(name = "size", required = false) String size) {
    	
        AircraftType aircraftType = AircraftType.parseAircraftType(type);
        if (aircraftType == null) {
        	throw new IllegalArgumentException("Invalid aircraft type - " + type);
        }
        AircraftSize aircraftSize = AircraftSize.parseAircraftSize(size);
        if (aircraftSize == null) {
        	throw new IllegalArgumentException("Invalid aircraft size - " + size);
        }
        
        EnqueuedAircraftEntity entity = enqueueService.enqueueAircraft(name, aircraftType, aircraftSize);
        
    	return aircraftServiceHelper.convertToAirCraftDto(entity);
    }

	@RequestMapping(value = "/dequeue", method = {RequestMethod.GET, RequestMethod.POST}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
	public AircraftDto dequeue() {
    	
        return dequeueService.dequeueAircraft();
    }

	@RequestMapping(value = "/enqueuedAircraftList", method = { RequestMethod.GET, RequestMethod.POST }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
	public List<AircraftDto> enqueuedAircraftList() {
    	
        return enqueueService.listAllEnqueuedAircraft();
    }

	@RequestMapping(value = "/dequeuedAircraftList", method = {RequestMethod.GET, RequestMethod.POST}, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })
	public List<AircraftDto> dequeuedAircraftList() {
    	
        return dequeueService.listAllDequeuedAircraft();
    }

}
