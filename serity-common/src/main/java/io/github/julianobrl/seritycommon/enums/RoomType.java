package io.github.julianobrl.seritycommon.enums;

public enum RoomType {
	
	KINDERGARTEN("KINDERGARTEN"),
	ELEMENTARY_SCHOOL("ELEMENTARY_SCHOOL"),
	HIGH_SCHOOL("HIGH_SCHOOL"),
	COLLEGE("COLLEGE");
	
	String definition;
	
	RoomType(String definition){
		this.definition = definition;
	}
	
	@Override
	public String toString() {
		return definition;
	}
	
}