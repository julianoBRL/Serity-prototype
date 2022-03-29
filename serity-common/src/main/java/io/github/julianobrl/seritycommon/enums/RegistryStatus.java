package io.github.julianobrl.seritycommon.enums;

public enum RegistryStatus {
	NOT_OPENED("NOT_OPENED"),
	OPENED("OPENED"),
	DONE("DONE");
	
	String definition;
	
	RegistryStatus(String definition){
		this.definition = definition;
	}
	
	@Override
	public String toString() {
		return definition;
	}
}
