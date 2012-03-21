DecisionTable ist eine Komponente zur Entscheidungsfindung 
mittels einer Entscheidungstabelle.

Zur erl�uterung der Entscheidungstabelle siehe
https://de.wikipedia.org/wiki/Entscheidungstabelle

Eine Regel (Spalte) besteht aus mehreren Condition-Objekten (die Bedinungen) 
und Aktionen. Sind f�r eine Regel alle Bedingungen erf�llt, werden die 
dieser Regel  zugeh�rigen Aktionen zur�ckgegeben. Die Aktionen sind dabei
ein Array von Objects. 

Um zu sehen wie die Entscheidungstabelle dann mittels Facts genutzt werden 
kann, verweise ich auf die Testklasse UseCases

