package com.phesse.decisionTable;

import static org.junit.Assert.*;

import org.junit.Test;

public class UseCases {
	/**
	    ------------|-------|-------|
                    | R1	| 	R2  |
		-----------------------------
		is_young    | true  | false |
		------------|-------|-------|
		Action_1    | x     |       | 
		------------|-------|-------|
		Action_2    |       | x     | 
		------------|-------|-------|
	 */
	@Test public void useCase1() {
		/********
		** Aufbau der Tabelle
		********/
		Condition condition = new Condition("is_young", true);
		Rule rule1 = new Rule(	new Condition[]{condition}, new String[]{"Action_1"});

		Condition condition2 = new Condition("is_young", false);
		Rule rule2 = new Rule(new Condition[]{condition2},	new String[]{"Action_2"});

		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule1);
		decisionTable.addRule(rule2);

		/********
		** Benutzen der Tabelle
		********/
		Fact fact = new Fact();
		fact.setValue("is_young", true);
		String[] actions = (String[]) decisionTable.execute(fact); 
		assertArrayEquals(new String[]{"Action_1"}, actions);

		fact = new Fact();
		fact.setValue("is_young", false);
		actions = (String[]) decisionTable.execute(fact);
		assertArrayEquals(new String[]{"Action_2"}, actions);
	}
	
	/**
        ------------|-------|-------|-------|
                    | R1	| 	R2  | 	R2  |
		-------------------------------------
		is_young    | true  | true  | false |
		------------|-------|-------|--------
		need_money  | true  | false | -     | 
		------------|-------|-------|--------
		Action_1    | x     |       |       | 
		------------|-------|-------|--------
		Action_2    |       | x     | x     |
		------------|-------|-------|--------
		Action_3    |       |       | x     |
		------------|-------|-------|--------
	 */
	@Test public void useCase2() {
		// Aufbau der Tabelle 
		Rule rule1 = new Rule(
			new Condition[]{
				new Condition("is_young", true),
				new Condition("need_money", true)
			},	
			new String[]{"Action_1"}
		);

		Rule rule2 = new Rule(
			new Condition[]{
				new Condition("is_young", true),
				new Condition("need_money", false)
			},	
			new String[]{"Action_2"}
		);
		Rule rule3 = new Rule(
			new Condition[]{
				new Condition("is_young", false)
			},	
			new String[]{"Action_2", "Action_3"}
		);

		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule1);
		decisionTable.addRule(rule2);
		decisionTable.addRule(rule3);

		//Benutzen der Tabelle 
		Fact fact = new Fact();
		fact.setValue("is_young", true);
		fact.setValue("need_money", true);
		String[] actions = (String[]) decisionTable.execute(fact);
		assertArrayEquals(new String[]{"Action_1"}, actions);

		fact = new Fact();
		fact.setValue("is_young", true);
		fact.setValue("need_money", false);
		actions = (String[]) decisionTable.execute(fact);
		assertArrayEquals(new String[]{"Action_2"}, actions);

		fact = new Fact();
		fact.setValue("is_young", false);
		actions = (String[]) decisionTable.execute(fact); 
		assertArrayEquals(new String[]{"Action_2", "Action_3"}, actions);
	}
}
