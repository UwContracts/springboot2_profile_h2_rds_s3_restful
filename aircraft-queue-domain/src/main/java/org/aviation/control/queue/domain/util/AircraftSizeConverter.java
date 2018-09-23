package org.aviation.control.queue.domain.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.aviation.control.queue.domain.enums.AircraftSize;

@Converter(autoApply = true)
public class AircraftSizeConverter implements AttributeConverter<AircraftSize, String> {
	
	@Override
	public String convertToDatabaseColumn(AircraftSize status) {
		return status != null ? status.toString() : null;
	}

	@Override
	public AircraftSize convertToEntityAttribute(String status) {
		return AircraftSize.parseAircraftSize(status);
	}
	
}
