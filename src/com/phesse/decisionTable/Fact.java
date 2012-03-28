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

import java.util.HashMap;
import java.util.Map;

public class Fact {
	private Map<String, Boolean> values = new HashMap<String, Boolean>();
	
	protected Boolean getValue(String key) throws UnknownKeyException {
		if (values.containsKey(key) == false)
			throw new UnknownKeyException("unknwon key " + key);
		
		return values.get(key);
	}
	
	public void setValue(String key, Boolean value) {
		values.put(key, value);
	}
}