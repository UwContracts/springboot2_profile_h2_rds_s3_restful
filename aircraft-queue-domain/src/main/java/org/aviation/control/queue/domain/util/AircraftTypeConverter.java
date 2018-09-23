package org.aviation.control.queue.domain.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aviation.control.queue.domain.enums.AircraftType;

@Converter(autoApply = true)
public class AircraftTypeConverter implements AttributeConverter<AircraftType, String> {
	
	@Override
	public String convertToDatabaseColumn(AircraftType status) {
		return status != null ? status.toString() : null;
	}

	@Override
	public AircraftType convertToEntityAttribute(String status) {
		return AircraftType.parseAircraftType(status);
	}
	
}
