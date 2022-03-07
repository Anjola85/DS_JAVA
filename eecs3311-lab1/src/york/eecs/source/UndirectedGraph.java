package york.eecs.source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class UndirectedGraph<T extends Comparable<T>> extends Graph<T> {

	private Map<T, Set<T>> graph;
	
	/**
	 *  This is the constructor.
	 *  Please do not change it.
	 */
	public UndirectedGraph() {
		this.graph = new HashMap<>();
	}
	
	/**
	 * @return true if graph is nonempty, false otherwise.
	 */
	public boolean isEmpty() {
		// TODO: Complete this method
		// Hint: An empty graph contains zero vertices
		return getSize() == 0; // this line needs to be rewritten 
	}
    
	/**
	 * @return the size (i.e. number of vertices) of this graph
	 */
	public int getSize() {
		return graph.size(); 
	}
	
	/**
	 * Add a new vertex to the graph. A new vertex points
	 * to an empty set of adjacent vertices.
	 * 
	 * @param vertex: an object that is a new vertex in the graph
	 */
	public void addVertex(T vertex) throws VertexExistsException {
		// TODO: Complete this method  
		// Hints: If the vertex already exists, throw and exception
		//        Else, add a new pair to the graph hashmap:
		//        the vertex is the key, the value is an empty
		//        set of vertices.
		if(graph.containsKey(vertex))
			throw new VertexExistsException("Vertex already exists");
		
		graph.put(vertex, new HashSet<T>());
	}
	
	public List<T> getAdjacent(T vertex) {
		return new ArrayList<>(graph.get(vertex));
	}
	
	
	@Override
	public List<T> getVertices() {
		return graph.keySet().stream().collect(Collectors.toList());
	}

	/**
	 * @param fromVertex one of vertices of this edge
	 * @param toVertex the other vertex of this egde
	 */
	public void addEdge(T fromVertex, T toVertex) {
		// Hint: Recall, both vertices already exist. Also,
		//       our graphs are not oriented, hence both edges
		//       need to be added.
		// no need to check if one of the vertices don't exist since we are guaranteed both exists
		graph.get(fromVertex).add(toVertex);
		graph.get(toVertex).add(fromVertex);
	}

	/*
	 * Return a sorted list of the vertices
	 */
	@Override
	public String toString() {
		
		String out = "Graph:\n";
		
		List<T> vertices = new ArrayList<>();
		
		
		// get and sort the vertices
		for(Entry<T, Set<T>> entry : graph.entrySet()) 
			vertices.add(entry.getKey());
		
		Collections.sort(vertices);
		
		// get all adj vertices of each vertex and sort them
		for(T v : vertices) {
			List<T> adjVertices = new ArrayList<>(graph.get(v));
			Collections.sort(adjVertices);
			out += "Vertex: " + v + " & Adjacent Vertices: " + adjVertices + "\n";
		}
        return out; 
	}
	

}
