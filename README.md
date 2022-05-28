# Lab10-Kingdom-Battles

Vicki Young

2022.04.23

CS 245-03

**NOTES**

- Program takes a number of kingdoms and a list of conflicts between them. Determines and returns how many surviving kingdoms remain.
- Each conflict takes place between 2 kingdoms.
- The winner in a conflict is determined by the kingdom with more territories.
- If the # of territories is the same for both kingdoms, the winner and loser are chosen randomly.
- Defeated kingdoms become part of the territory of the winning and surviving kingdoms.
- Program assumes no repeated conflicts, and that conflicts are not reversible. There are no conflicts with only one kingdom.
- Acceptable number of kingdoms must be 1 <= # of kingdoms <= 1000.
- Acceptable number of kingdoms in a conflict must be 2.


**TIME AND SPACE COMPLEXITY ANALYSIS**

`public survivedKingdoms()`
- TIME: O(e(v + e))
  - loops through # of edges (list of conflicts) between vertices = O(e)
  - calls survivedKingdomsCount() and connectedTerritories() method, each = O(v)
  - calls getRulingKingdom and territoryCount() methods, each = O(v + e)
- SPACE: O(v)
  - initializes boolean list of length v = # of vertices/kingdoms and array of LinkedLists of length v = # of vertices/kingdoms

`protected survivedKingdomsCount()`
- TIME: O(v) 
  - loops through boolean list that is length v = number of vertices/kingdoms
- SPACE: O(1)

`protected survivingKingdoms()`
- TIME: O(1)
- SPACE: O(v)
  - initializes and fills boolean list that is length v = number of vertices/kingdoms

`protected connectedTerritories()`
- TIME: O(v)
  - loops through an array of length v = number of vertices/kingdoms to initialize v linked lists
- SPACE: O(v)
  - initializes an array of linked lists that is length v = number of vertices/kingdoms

`protected getRulingKingdom()`
- TIME: O(v + e)
  - calls private getRulingKingdom() method
- SPACE: O(v)
  - calls private getRulingKingdom() method

`private getRulingKingdom()`
- TIME: O(v + e)
  - implements Depth-First Search; v = number of vertices/kingdoms, e = number of edges
  - loops through # of vertices = O(v)
  - each loop pushes adjacent vertices not yet visited based on # of edges, but each edge is considered only once = O(e) 
- SPACE: O(v)
  - uses a stack which vertices are pushed into 

`protected territoryCount()`
- TIME: O(v + e)
    - calls private territoryCount() method
- SPACE: O(v)
    - calls private territoryCount() method

`private territoryCount()`
- TIME: O(v + e)
    - implements Depth-First Search; v = number of vertices/kingdoms, e = number of edges
    - loops through # of vertices = O(v)
    - each loop pushes adjacent vertices not yet visited based on # of edges, but each edge is considered only once = O(e)
- SPACE: O(v)
    - uses a stack which vertices are pushed into

`protected randomLoser()`
- TIME: O(1)
- SPACE: O(1)