package io.github.julianobrl.seritycommon.enums;

public enum ActivityTypes {
	READ("READ"),
	FILE("FILE"),
	VIDEO("VIDEO"),
	TEST("TEST");
	
	String definition;
	
	ActivityTypes(String definition){
		this.definition = definition;
	}
	
	@Override
	public String toString() {
		return definition;
	}
}
