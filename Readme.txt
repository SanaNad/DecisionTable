DecisionTable ist eine Komponente zur Entscheidungsfindung 
mittels einer Entscheidungstabelle.

Zur erläuterung der Entscheidungstabelle siehe
https://de.wikipedia.org/wiki/Entscheidungstabelle

Eine Regel (Spalte) besteht aus mehreren Condition-Objekten (die Bedinungen) 
und Aktionen. Sind für eine Regel alle Bedingungen erfüllt, werden die 
dieser Regel  zugehörigen Aktionen zurückgegeben. Die Aktionen sind dabei
ein Array von Objects. 

Um zu sehen wie die Entscheidungstabelle dann mittels Facts genutzt werden 
kann, verweise ich auf die Testklasse DecisionTableTest

Beispiel 1

------------|-------|-------|
			| R1	| 	R2  |
-----------------------------
is_young    | true  | false |
------------|-------|-------|
Action_1    | x     |       | 
------------|-------|-------|
Action_2    |       | x     | 
------------|-------|-------|

********
** Code zum Aufbau der Tabelle für Beispiel 1:
********
Condition condition = new Condition("is_young", true);
Rule rule1 = new Rule(	new Condition[]{condition}, new String[]{"Action_1"});

Condition condition2 = new Condition("is_young", false);
Rule rule2 = new Rule(new Condition[]{condition2},	new String[]{"Action_2"});

DecisionTable decisionTable = new DecisionTable();
decisionTable.addRule(rule1);
decisionTable.addRule(rule2);

********
** Code zum Benutzen der Tabelle für Beispiel 1:
********
Fact fact = new Fact();
fact.setValue("is_young", false);
String[] actions = (String[]) decisionTable.execute(fact); //["Action_2"]

Fact fact = new Fact();
fact.setValue("is_young", true);
String[] actions = (String[]) decisionTable.execute(fact); //["Action_1"]


Beispiel 2

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

********
** Code zum Aufbau der Tabelle für Beispiel 2:
********
Condition condition1 = new Condition("is_young", true);
Condition condition2 = new Condition("need_money", true);
Rule rule1 = new Rule(	new Condition[]{condition1, condition2}, new String[]{"Action_1"});

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
	new String[]{"Action_1", "Action_2"}
);

DecisionTable decisionTable = new DecisionTable();
decisionTable.addRule(rule1);
decisionTable.addRule(rule2);
decisionTable.addRule(rule3);

********
* Code zum Benutzen der Tabelle für Beispiel 2:
********
Fact fact = new Fact();
fact.setValue("is_young", true);
fact.setValue("need_money", true);
String[] actions = (String[]) decisionTable.execute(fact); //["Action_1"]

Fact fact = new Fact();
fact.setValue("is_young", true);
fact.setValue("need_money", false);
String[] actions = (String[]) decisionTable.execute(fact); //["Action_2"]

Fact fact = new Fact();
fact.setValue("is_young", false);
String[] actions = (String[]) decisionTable.execute(fact); //["Action_2", "Action_3"]