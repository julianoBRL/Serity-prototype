package io.github.julianobrl.seritycommon.enums;

public enum TicketStatus {
	
	OPEN("OPEN"),
	IN_PROGRESS("OPEN"),
	CLOSED("OPEN"),
	RESOLVED("OPEN"),
	CANCELED("OPEN"),
	RECUSED("OPEN");
	
	String definition;
	
	TicketStatus(String definition){
		this.definition = definition;
	}
	
	@Override
	public String toString() {
		return definition;
	}
}
