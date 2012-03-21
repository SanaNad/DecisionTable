package com.phesse.decisionTable;

import java.util.ArrayList;
import java.util.List;

public class DecisionTable {
	private List<Rule> rules = new ArrayList<Rule>();
	
	public Object[] execute(Fact fact) {
		for(Rule rule : rules) {
			if (rule.isValid(fact)) {
				return rule.getActions();
			}
		}
		
		return null;
	}

	public void addRule(Rule rule) {
		rules.add(rule);
	}
}