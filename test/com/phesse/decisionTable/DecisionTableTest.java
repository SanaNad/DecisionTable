/*
   Copyright 2012 Patrick Hesse

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.phesse.decisionTable;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.phesse.decisionTable.Condition;
import com.phesse.decisionTable.DecisionTable;
import com.phesse.decisionTable.Fact;
import com.phesse.decisionTable.Rule;

public class DecisionTableTest {
	private static final String KEY = "key";
	private static final String ACTION2 = "b";
	private static final String ACTION1 = "a";

	@Test public void OneRuleWithValidConditionWouldReturnActionOfRule() throws UnknownKeyException {
		Condition condition = new Condition(KEY, true);
		
		Rule rule = new Rule(new Condition[]{condition}, new String[]{ACTION1});
		
		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule);

		Fact fact = new Fact();
		fact.setValue(KEY, true);
		String[] actions = (String[]) decisionTable.execute(fact);
		
		assertArrayEquals(new String[]{ACTION1}, actions);
	}

	@Test public void OneRuleWithInvalidConditionWouldReturnNull() throws UnknownKeyException {
		Condition condition = new Condition(KEY, true);
		
		Rule rule = new Rule(new Condition[]{condition}, new String[]{ACTION1});
		
		DecisionTable decisionTable = new DecisionTable();
		decisionTable.addRule(rule);
		
		Fact fact = new Fact();
		fact.setValue(KEY, false);
		String[] actions = (String[]) decisionTable.execute(fact);
		
		assertNull(actions);
	}

	@Test public void ActionOfRuleWithValidConditionWouldReturned() throws UnknownKeyException {
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

	@Test public void AllActionsOfARuleWithValidConditionWouldBeReturned() throws UnknownKeyException {
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
	
	@Test public void ActionOfRuleWithAllValidConditionsWouldReturned() throws UnknownKeyException {
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