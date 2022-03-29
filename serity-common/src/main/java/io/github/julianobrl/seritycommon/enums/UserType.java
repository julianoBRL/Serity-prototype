package io.github.julianobrl.seritycommon.enums;

public enum UserType {
	ADMIN("ADMIN"),
	TEACHER("TEACHER"),
	STUDENT("STUDENT"),
	SUPPORT("SUPPORT"),
	SECRETARY("SECRETARY"),
	FINANTIAL("FINANTIAL"),
	RH("RH");
	
	String definition;
	
	UserType(String definition){
		this.definition = definition;
	}
	
	@Override
	public String toString() {
		return definition;
	}
	
}
