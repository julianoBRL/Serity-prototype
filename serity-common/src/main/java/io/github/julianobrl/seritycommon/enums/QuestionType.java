package io.github.julianobrl.seritycommon.enums;

public enum QuestionType {
	ONE("ONE"),
	TEXT("TEXT"),
	MULTIPLE("MULTIPLE"),
	PICTURE("PICTURE"),
	AUDIO("AUDIO");
	
	String definition;
	
	QuestionType(String definition){
		this.definition = definition;
	}
	
	@Override
	public String toString() {
		return definition;
	}
}
