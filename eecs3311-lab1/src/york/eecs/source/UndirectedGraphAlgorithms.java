package york.eecs.source;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class UndirectedGraphAlgorithms<T extends Comparable<T>> 
								implements GraphAlgorithms<T> {

	/**
	 * Please implement BFS algorithm as described on the handout
	 * @param g: a graph
	 * @param initial: the starting vertex of the path
	 * @param destination: the destination vertex of the path
	 * @return the path from initial to destination in the form of
	 *         an ArrayList of vertices, with initial as the first
	 *         element, and destination as the last element of the 
	 *         ArrayList
	 */
	public List<T> findBFSpath(Graph<T> g, T initial, T destination) {
		
		//base case the initial is the destination
		if(initial.compareTo(destination) == 0)
			return new ArrayList<T>(){{add(initial);}};
		
		List<T> output = new ArrayList<>();
		
		// for iteration amongst vertices
		Queue<List<T>> q = new ArrayDeque<>();
		
		// populate queue with initial
		q.add(Arrays.asList(initial));
		
		while(!q.isEmpty()) {
			List<T> path = q.remove(); // gets existing edge between vertices
			
			// check if last added vertex in path is destination
			if(path.get(path.size()-1).compareTo(destination) == 0)
				return path;
			
			// adds edges to create path to the last added vertex in path list
			for(T adj : g.getAdjacent(path.get(path.size() - 1))) {
				List<T> next = new ArrayList<T>(path);
				next.add(adj);
				q.add(next);
			}
		}
		
			
		return output;
	}
	

}
