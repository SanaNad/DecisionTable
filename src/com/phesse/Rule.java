package com.phesse;

public class Rule {
	private Object[] actions = null;
	private Condition[] conditions = null;
	
	public Rule(Condition[] conditions, Object[] actions) {
		this.conditions = conditions;
		this.actions = actions;
	}
	
	public boolean isValid(Fact fact) {
		for(Condition condition : conditions) {
			if (!condition.isValid(fact))
				return false;
		}
		
		return true;
	}
	
	public Object[] getActions() {
		return actions;
	}
}