package com.phesse.decisionTable;

import java.util.HashMap;
import java.util.Map;

public class Fact {
	private Map<String, Boolean> values = new HashMap<String, Boolean>();
	
	public Boolean getValue(String key) {
		if (values.containsKey(key) == false)
			throw new RuntimeException("unkown key");
		
		return values.get(key);
	}
	
	public void setValue(String key, Boolean value) {
		values.put(key, value);
	}
}