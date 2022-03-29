package io.github.julianobrl.seritycommon.enums;

public enum TicketType {
	
	SYSTEM("SYSTEM"),
	HARDWARE("HARDWARE"),
	OTHER("OTHER");
	
	String definition;
	
	TicketType(String definition){
		this.definition = definition;
	}
	
	@Override
	public String toString() {
		return definition;
	}

}

