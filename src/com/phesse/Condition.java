package com.phesse;

public class Condition {
	private String key;
	private Boolean value;
	
	public Condition(String key, Boolean value) {
		this.key = key;
		this.value = value;
	}

	public boolean isValid(Fact fact) {
		return fact.getValue(key) == value;
	}
	
}