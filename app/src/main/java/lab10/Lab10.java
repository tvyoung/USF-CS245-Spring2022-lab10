package lab10;

import java.util.*;

/**
 * This Java source file was (initially) generated by the Gradle 'init' task.
 *
 * @author Vicki Young
 * @version 2022.04.23
 * CS245 Lab10 Survived Kingdoms
 */
public class Lab10 {

    /**
     * kingdoms = number of kingdoms
     * survivingKingdoms = boolean list of surviving kingdoms; kingdom i is surviving if survivingKingdoms[i] = true
     * connectedTerritories = array of LinkedLists with adjacent kingdoms (acting adjacencyList);
     * gives list of adjacent kingdoms to kingdom i at connectedTerritories[i]
     */
    protected static int kingdoms;
    protected static boolean[] survivingKingdoms;
    protected static LinkedList<Integer>[] connectedTerritories;

    /**
     * survivedKingdoms() method takes a number of kingdoms and a list of conflicts between them.
     * Determines and returns how many surviving kingdoms remain.
     * @param n = number of kingdoms
     * @param listOfConflicts = list of conflicts, each one between 2 kingdoms
     * @return int number of surviving kingdoms
     * @throws IllegalArgumentException if !(1 <= number of kingdoms <= 1000)
     * or if the number of kingdoms in a conflict is not 2
     */
    public static int survivedKingdoms(int n, int[][] listOfConflicts) throws IllegalArgumentException {
        //throw exception if !(1 <= n <= 1000)
        if (n < 1 || n > 1000) {
            throw new IllegalArgumentException("Invalid number of kingdoms.");
        }
        //initialize global variables # of kingdoms, list of surviving kingdoms, and adjacencyList of adjacent kingdoms
        kingdoms = n;
        survivingKingdoms();
        connectedTerritories();

        //loop through list of conflicts to determine the winner
        for (int[] conflict : listOfConflicts) {
            //throw exception if the number of kingdoms in a conflict is not 2
            if (conflict.length != 2) {
                throw new IllegalArgumentException("Only 2 kingdoms can be in a conflict.");
            }

            //get each kingdom in given conflict
            int a = conflict[0];
            int b = conflict[1];

            //get territory count of each kingdom (total # of connected territories to it)
            int aTerritories = territoryCount(a);
            int bTerritories = territoryCount(b);

            //if # of territories for kingdoms A and B are equal, choose winner randomly; the ruling kingdom of the loser does not survive
            if (aTerritories == bTerritories) {
                int loser = randomLoser(conflict);
                survivingKingdoms[getRulingKingdom(loser)] = false;
            //else if kingdom A # of territories > kingdom B # of territories, the ruling kingdom of B does not survive
            } else if (aTerritories > bTerritories) {
                survivingKingdoms[getRulingKingdom(b)] = false;
            //else kingdom B # of territories > kingdom A # of territories, the ruling kingdom of A does not survive
            } else {
                survivingKingdoms[getRulingKingdom(a)] = false;
            }
            //regardless of the winner, connect the two kingdoms together (they become adjacent kingdoms)
            connectedTerritories[a].add(b);
            connectedTerritories[b].add(a);
        }
        //return # of surviving kingdoms
        return survivedKingdomsCount();
    }

    /**
     * survivingKingdomsCount() method returns number of surviving kingdoms.
     * Counts number of kingdoms in boolean list survivingKingdoms = true
     * @return count of surviving kingdoms
     */
    protected static int survivedKingdomsCount() {
        int count = 0;
        for (boolean kingdom : survivingKingdoms) {
            if (kingdom) {
                count++;
            }
        }
        return count;
    }

    /**
     * survivingKingdoms() method initializes global variable boolean[] survivingKingdoms.
     * Uses global variable kingdoms for boolean array size.
     * Sets each kingdom survival status to true.
     */
    protected static void survivingKingdoms() {
        survivingKingdoms = new boolean[kingdoms];
        Arrays.fill(survivingKingdoms, true);
    }

    /**
     * connectedTerritories() method initializes global variable LinkedList<Integer>[] connectedTerritories.
     * Uses global variable kingdoms for array size.
     * Note: # of adjacent kingdoms to a given kingdom i = connectedTerritories[i].size()
     */
    protected static void connectedTerritories() {
        connectedTerritories = new LinkedList[kingdoms];
        for (int i = 0; i < kingdoms; i++) {
            connectedTerritories[i] = new LinkedList<>();
        }
    }

    /**
     * getRulingKingdom() method takes given kingdom and searches through its
     * connected territories to return its ruling kingdom (the surviving kingdom in
     * the connected territories).
     * If the given kingdom itself is surviving, then it is still its own ruling kingdom.
     * Employs Depth-First Search implementation.
     * @param kingdom = the given kingdom for which to find the ruling kingdom
     * @return int of the ruling kingdom
     */
    protected static int getRulingKingdom(int kingdom) {
        if (survivingKingdoms[kingdom]) {
            return kingdom;
        }
        boolean[] visited = new boolean[kingdoms];
        return getRulingKingdom(kingdom, visited);
    }

    /**
     * getRulingKingdom() private method, which takes given kingdom and searches through
     * its connected territories to return its ruling kingdom (the surviving kingdom in
     * the connected territories).
     * Employs Depth-First Search implementation.
     * @param k = the given kingdom for which to find the ruling kingdom
     * @param visited = list of visited kingdoms; to prevent from visiting a kingdom more than once
     * @return int of the ruling kingdom; returns -1 if there is no surviving kingdom (which indicates an error)
     */
    private static int getRulingKingdom(int k, boolean[] visited) {
        Stack<Integer> s = new Stack<>();
        //add given kingdom to stack and mark as visited
        visited[k] = true;
        s.push(k);
        //while stack is not empty, repeatedly check given kingdom and its adjacent ones for surviving ruler
        while (!s.empty()) {
            k = s.pop();
            //if the kingdom is a surviving kingdom, return it because it is the ruler
            if (survivingKingdoms[k]) {
                return k;
            }
            //otherwise, for all adjacent kingdoms not yet visited, push to stack and mark as visited
            for (int territory : connectedTerritories[k]) {
                if (!visited[territory]) {
                    s.push(territory);
                    visited[territory] = true;
                }
            }
        }
        //ERROR: no surviving ruler found
        return -1;
    }

    /**
     * territoryCount() method takes given kingdom and returns the
     * territory count. Territory count is the number of kingdoms the given kingdom
     * is connected to (the kingdom itself counts as 1). This means counting not just
     * the kingdoms adjacent to given kingdom, but all the kingdoms connected together
     * through it.
     * Employs Depth-First Search implementation.
     * @param kingdom = the given kingdom for which to find # of connected territories
     * @return number of connected kingdoms, including the given kingdom itself
     */
    protected static int territoryCount(int kingdom) {
        boolean[] visited = new boolean[kingdoms];
        return territoryCount(kingdom, visited);
    }

    /**
     * territoryCount() private method, which takes given kingdom and returns the
     * territory count. Territory count is the number of kingdoms the given kingdom
     * is connected to (the kingdom itself counts as 1). This means counting not just
     * the kingdoms adjacent to given kingdom, but all the kingdoms connected together
     * through it.
     * Employs Depth-First Search implementation.
     * @param k = the given kingdom for which to find # of connected territories
     * @param visited = list of visited kingdoms; to prevent from visiting and counting a kingdom more than once
     * @return number of connected kingdoms, including the given kingdom itself
     */
    private static int territoryCount(int k, boolean[] visited) {
        int count = 0;
        Stack<Integer> s = new Stack<>();
        //add given kingdom to stack and mark as visited
        visited[k] = true;
        s.push(k);
        //while stack is not empty, repeatedly pop topmost kingdom and increment count
        while (!s.empty()) {
            k = s.pop();
            count++;
            //for all adjacent kingdoms not yet visited, push to stack and mark as visited
            for (int territory : connectedTerritories[k]) {
                if (!visited[territory]) {
                    s.push(territory);
                    visited[territory] = true;
                }
            }
        }
        //return count of connected kingdoms
        return count;
    }

    /**
     * randomLoser() method takes an array of kingdoms (should be 2) in conflict
     * and randomly returns a loser. Used when the two kingdoms in
     * conflict have an equal number of territories.
     * @param conflict = array of two kingdoms in conflict
     * @return int of the randomly chosen losing kingdom in conflict
     */
    protected static int randomLoser(int[] conflict) {
        int i = new Random().nextInt(conflict.length);
        return conflict[i];
    }

    /**
     * main() method for testing.
     */
    public static void main(String[] args) {
        int n = 10;
        int[][] arr = {{4,5},{5,2},{2,7},{3,6},{7,6}};
        System.out.println("number of kingdoms: " + n);
        System.out.print("conflicts: ");
        for (int[] a : arr) {
            System.out.print("[" + a[0] + ", " + a[1] + "] ");
        }

        System.out.println("\nSURVIVING KINGDOMS: " + survivedKingdoms(n, arr));
        for (int i = 0; i < n; i++) {
            System.out.print("kingdom " + i + " survived: " + survivingKingdoms[i] + " | ");
            System.out.print("ruler: " + getRulingKingdom(i) + " | ");
            System.out.print("adjacent territories: " + connectedTerritories[i] + " | ");
            System.out.print("territory count: " + territoryCount(i));
            System.out.println();
        }
    }
}