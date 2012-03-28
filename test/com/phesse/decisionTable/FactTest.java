package com.phesse.decisionTable;

import org.junit.Test;

public class FactTest {
	@Test(expected = UnknownKeyException.class)
	public void getValueForunknownKeyThrowsException() throws UnknownKeyException{
		Fact fact = new Fact();
		fact.setValue("foo", true);
		fact.getValue("bar");
	}
}
