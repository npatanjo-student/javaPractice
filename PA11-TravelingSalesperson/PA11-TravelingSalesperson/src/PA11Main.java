import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * AUTHOR: Nate Patanjo
 * FILE: PA11Main.java
 * ASSIGNMENT: Traveling Salesperson PA 10
 * COURSE: CSC 210
 * PURPOSE: The purpose of this assignment is to traverse
 * though a graph using recursive backing tracking and heuristic style
 * traversal.
 * 
 * USAGE:
 * args[0] is the file path
 * 
 * args[1] = HEURISTIC:
 * this will traverse through the graph using the heuristic 
 * traversal.
 * 
 * args[1] = BACKTRACK:
 * this will traverse through the graph using recursive
 * backtracking
 * 
 * args[1] = MINE:
 * this will traverse through the graph using a "modified"
 * version of recursive backtracking
 * 
 * args[1] = TIME:
 * this will give the run time of all 3 traversals
 */
public class PA11Main {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	DGraph graph = extraction(args[0]);
    	if (args[1].equals("HEURISTIC")) {
    		System.out.println(heuristic(graph).toString(graph));
    	} else if (args[1].equals("BACKTRACK")){
    		System.out.println(backtracking(graph).toString(graph));
    	} else if (args[1].equals("MINE")) {
    		System.out.println(mine(graph).toString(graph));
    	} else if (args[1].equals("TIME")) {
    		time(graph);
    	}
    }
    
    /**
     * Extraction.
     *
     * @param file the file
     * @return the d graph
     */
    public static DGraph extraction(String file) {
    	Scanner scanner = null;
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext() && scanner.next().charAt(0) == '%') {
        	scanner.nextLine();
        }
        String lineStr = scanner.nextLine();
        String[] lineArr = lineStr.trim().split("( )+");
        DGraph graph = new DGraph(Integer.valueOf(lineArr[0]));
        while (scanner.hasNext()) {
        	String[] line = scanner.nextLine().trim().split("( )+");
        	graph.addEdge(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Double.valueOf(line[2]));
        }
        scanner.close();
        return graph;
    }
    
    /**
     * Heuristic.
     *
     * @param graph the graph
     * @return the trip
     */
    public static Trip heuristic(DGraph graph) {
    	Trip myTrip = new Trip(graph.getNumNodes());
    	Integer current = 1;
    	myTrip.chooseNextCity(current);
    	for (int k = 2; k <= graph.getNumNodes(); k++) {
    		Integer target = 0;
    		for (Integer n : graph.getLowestN(current)) {
    			if (myTrip.isCityAvailable(n)) {
    				target = n;
    				break;
    			}
    		}
    		myTrip.chooseNextCity(target);
    		current = target;
    	}
    	return myTrip;
    	
    }
    
    /**
     * Backtracking.
     *
     * @param graph the graph
     * @return the trip
     */
    public static Trip backtracking(DGraph graph) {
    	Trip myTrip = new Trip(graph.getNumNodes());
    	Trip minTrip = new Trip(0);
    	myTrip.chooseNextCity(1);
    	minTrip = backtrackingFunction(graph, myTrip, minTrip);
    	
    	return minTrip;
    }
    
    /**
     * Backtracking helper.
     *
     * @param graph the graph
     * @param myTrip the my trip
     * @param minTrip the min trip
     * @return the trip
     */
    public static Trip backtrackingFunction(DGraph graph, Trip myTrip, Trip minTrip) {
    	if (myTrip.citiesLeft().isEmpty()) {
    		if(myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
    			minTrip.copyOtherIntoSelf(myTrip);
    		}
    		return minTrip;
    	}
    	if(myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
    		for (Integer x : myTrip.citiesLeft()) {
    			myTrip.chooseNextCity(x);
    			backtrackingFunction(graph, myTrip, minTrip);
    			myTrip.unchooseLastCity();
    		}
    	}
    	return minTrip;
    }
    
    /**
     * Mine. My backtracking function.
     *
     * @param graph the graph
     * @return the trip
     */
    public static Trip mine(DGraph graph) {
    	Trip myTrip = new Trip(graph.getNumNodes());
    	Trip minTrip = new Trip(0);
    	myTrip.chooseNextCity(1);
    	minTrip = mineHelper(graph, myTrip, minTrip);
    	return minTrip;
    	
    }
    
    /**
     * Mine helper.
     *
     * @param graph the graph
     * @param myTrip the my trip
     * @param minTrip the min trip
     * @return the trip
     */
    public static Trip mineHelper(DGraph graph, Trip myTrip, Trip minTrip) {
    	if (myTrip.citiesLeft().isEmpty()) {
    		if(myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
    			minTrip.copyOtherIntoSelf(myTrip);
    		}
    		return minTrip;
    	}
    	if(myTrip.tripCost(graph) < minTrip.tripCost(graph)) {
    		for (Integer x : myTrip.citiesLeft()) {
				myTrip.chooseNextCity(x);
				mineHelper(graph, myTrip, minTrip);
				myTrip.unchooseLastCity();
    		}
    		
    	}
    	return minTrip;
    }

    /**
     * Time.
     *
     * @param graph the graph
     */
    public static void time(DGraph graph) {
    	long startTime = System.nanoTime();
    	Trip trip = heuristic(graph);
    	long endTime = System.nanoTime();
    	long duration = (endTime - startTime) / 100000;
    	System.out.println("heuristic: cost = " + trip.tripCost(graph) + ", " + duration +
    			" milliseconds");
    	
    	long startTime2 = System.nanoTime();
    	Trip trip2 = backtracking(graph);
    	long endTime2 = System.nanoTime();
    	long duration2 = (endTime2 - startTime2) / 100000;
    	System.out.println("backtracking: cost = " + trip2.tripCost(graph) + ", " + duration2 +
    			" milliseconds");
    	
    	long startTime3 = System.nanoTime();
    	Trip trip3 = mine(graph);
    	long endTime3 = System.nanoTime();
    	long duration3 = (endTime3 - startTime3) / 100000;
    	System.out.println("mine: cost = " + trip3.tripCost(graph) + ", " + duration3 +
    			" milliseconds");

    }
}







