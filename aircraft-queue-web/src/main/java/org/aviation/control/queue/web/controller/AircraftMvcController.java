package org.aviation.control.queue.web.controller;

import org.aviation.control.queue.domain.entity.EnqueuedAircraftEntity;
import org.aviation.control.queue.domain.enums.AircraftSize;
import org.aviation.control.queue.domain.enums.AircraftType;
import org.aviation.control.queue.service.DequeueAircraftService;
import org.aviation.control.queue.service.EnqueueAircraftService;
import org.aviation.control.queue.service.SystemBootService;
import org.aviation.control.queue.service.util.AircraftServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AircraftMvcController {
    
	@Autowired
	private AircraftServiceHelper aircraftServiceHelper;
	
	@Autowired
	private DequeueAircraftService dequeueService;
	
	@Autowired
	private EnqueueAircraftService enqueueService;
	
	@Autowired
	private SystemBootService systemBootService;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String enqueuedAcList(Model model) {
		
        model.addAttribute("enqueuedList", enqueueService.listAllEnqueuedAircraft());
        
        return "enqueuedList";
    }
	
	@RequestMapping(value = "/boot", method = {RequestMethod.GET, RequestMethod.POST})
    public String productList(Model model) {
		
        model.addAttribute("result", systemBootService.start());
        model.addAttribute("enqueuedList", enqueueService.listAllEnqueuedAircraft());
        
        return "bootResult";
    }
	
	@RequestMapping(value = "/enqueue", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String enqueue(
			@RequestParam(name = "name", required = false) String aircraftName, 
			@RequestParam(name = "type", required = false) String type, 
			@RequestParam(name = "size", required = false) String size, 
			Model model) {
    	
        StringBuilder errorMsg = new StringBuilder();
        AircraftType aircraftType = AircraftType.parseAircraftType(type);
        if (aircraftType == null) {
        	errorMsg.append("Invalid Aircraft Type string: " + type);
        }        
        AircraftSize aircraftSize = AircraftSize.parseAircraftSize(size);
        if (aircraftSize == null) {
        	errorMsg.append(" Invalid Aircraft Size string: " + size);
        }
        if (errorMsg.length() > 0) {
            model.addAttribute("errorMsg", errorMsg.toString());
        } else {
            EnqueuedAircraftEntity entity = enqueueService.enqueueAircraft(aircraftName, aircraftType, aircraftSize);
			model.addAttribute("enqueuedAc", aircraftServiceHelper.convertToAirCraftDto(entity));
        }
        
        model.addAttribute("enqueuedList", enqueueService.listAllEnqueuedAircraft());
        
        return "enqueuedList";
    }

	@RequestMapping(value = "/dequeue", method = {RequestMethod.GET, RequestMethod.POST})
	public String dequeue(Model model) {
    	
        model.addAttribute("dequeuedAc", dequeueService.dequeueAircraft());
        model.addAttribute("dequeuedList", dequeueService.listAllDequeuedAircraft());
        
        return "dequeuedList";
    }

}
