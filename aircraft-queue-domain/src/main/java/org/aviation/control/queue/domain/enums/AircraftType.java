package org.aviation.control.queue.domain.enums;

public enum AircraftType {
	
	EMERGENCY, VIP, PASSENGER, CARGO;
	
	public static AircraftType fromValue(String v) {
		return valueOf(v);
	}
	
	public static AircraftType parseAircraftType(String v) {
		for (AircraftType type : AircraftType.values()) {
			if (type.name().equalsIgnoreCase(v)) {
				return type;
			}
		}
		return null;
	}
	
}
