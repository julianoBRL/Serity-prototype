package io.github.julianobrl.seritycommon.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
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

	@Override
	public String getAuthority() {
		return definition;
	}
	
}
