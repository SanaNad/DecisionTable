DecisionTable ist eine Komponente zur Entscheidungsfindung 
mittels einer Entscheidungstabelle.

Zur Erläuterung der Entscheidungstabelle siehe
https://de.wikipedia.org/wiki/Entscheidungstabelle

Eine Regel (Spalte) besteht aus mehreren Condition-Objekten (die Bedinungen) 
und Aktionen. Sind für eine Regel alle Bedingungen erfüllt, werden die 
dieser Regel  zugehörigen Aktionen zurückgegeben. Die Aktionen sind dabei
ein Array von Objects. 

Um zu sehen wie die Entscheidungstabelle dann mittels Facts genutzt werden 
kann, verweise ich auf die Testklasse UseCases.

