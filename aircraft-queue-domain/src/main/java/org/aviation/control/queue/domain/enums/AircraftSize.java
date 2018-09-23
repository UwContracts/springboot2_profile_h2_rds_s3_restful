package org.aviation.control.queue.domain.enums;

public enum AircraftSize {
	
	LARGE, SMALL;
	
	public static AircraftSize fromValue(String v) {
		return valueOf(v);
	}
	
	public static AircraftSize parseAircraftSize(String v) {
		for (AircraftSize size : AircraftSize.values()) {
			if (size.name().equalsIgnoreCase(v)) {
				return size;
			}
		}
		return null;
	}
	
}
