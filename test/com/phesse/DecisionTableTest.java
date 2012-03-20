package com.phesse;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DecisionTableTest {
	private static final String KEY = "key";
	private static final String ACTION2 = "b";
	private static final String ACTION1 = "a";

	@Test public void OneRuleWithValidConditionWouldReturnActionOfRule() {
		Condition condition = new Condition(KEY, true);
		
		Rule rule = new Rule(new Condition[]{condition}, new String[]{ACTION1});
		
		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule);

		Fact fact = new Fact();
		fact.setValue(KEY, true);
		String[] actions = (String[]) decisionTable.execute(fact);
		
		assertArrayEquals(new String[]{ACTION1}, actions);
	}

	@Test public void OneRuleWithInvalidConditionWouldReturnNull() {
		Condition condition = new Condition(KEY, true);
		
		Rule rule = new Rule(new Condition[]{condition}, new String[]{ACTION1});
		
		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule);
		
		Fact fact = new Fact();
		fact.setValue(KEY, false);
		String[] actions = (String[]) decisionTable.execute(fact);
		
		assertNull(actions);
	}

	@Test public void ActionOfRuleWithValidConditionWouldReturned() {
		//first rule
		Condition condition = new Condition(KEY, true);
		
		Rule rule1 = new Rule(
			new Condition[]{condition}, 
			new String[]{ACTION1}
		);
		
		//second rule
		Condition condition2 = new Condition(KEY, false);
		
		Rule rule2 = new Rule(
			new Condition[]{condition2}, 
			new String[]{ACTION2}
		);

		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule1);
		decisionTable.addRule(rule2);
		
		Fact fact = new Fact();
		fact.setValue(KEY, false);
		String[] actions = (String[]) decisionTable.execute(fact);
		
		assertArrayEquals(new String[]{ACTION2}, actions);
	}

	@Test public void AllActionsOfARuleWithValidConditionWouldBeReturned() {
		Condition condition = new Condition(KEY, true);

		Rule rule1 = new Rule(
				new Condition[]{condition}, 
				new String[]{ACTION1, ACTION2}
			);

		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule1);
		
		Fact fact = new Fact();
		fact.setValue(KEY, true);
		
		String[] actions = (String[]) decisionTable.execute(fact);
		assertArrayEquals(new String[]{ACTION1, ACTION2}, actions);
	}
	
	@Test public void ActionOfRuleWithAllValidConditionsWouldReturned() {
		//first rule
		Condition condition = new Condition(KEY, true);
		Condition condition2 = new Condition(KEY, true);
		
		Rule rule1 = new Rule(
			new Condition[]{condition, condition2}, 
			new String[]{ACTION1}
		);
		
		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule1);
		
		Fact fact = new Fact();
		fact.setValue(KEY, true);
		String[] actions = (String[]) decisionTable.execute(fact);
		
		assertArrayEquals(new String[]{ACTION1}, actions);
	}
}