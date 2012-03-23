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

public class Rule {
	private Object[] actions = null;
	private Condition[] conditions = null;
	
	public Rule(Condition[] conditions, Object[] actions) {
		this.conditions = conditions;
		this.actions = actions;
	}
	
	public boolean isValid(Fact fact) throws UnknownKeyException {
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