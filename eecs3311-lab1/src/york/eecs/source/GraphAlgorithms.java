package york.eecs.source;

import java.util.List;

interface GraphAlgorithms<T extends Comparable<T>> {

	/**
	 * Computes a path from an initial vertex, on a graph g,
	 * to a destination vertex, using BFS search technique.
	 * The path is returned as a Collection of vertices,
	 * starting at initial, terminating at destination
	 * 
	 * @param g, Graph with vertices of type T
	 * @param initial, a vertex in g, the initial vertex of the path
	 * @param destination, a vertex on g, the destination vertex
	 * @return a path from initial to destination
	 */
	public List<T> findBFSpath(Graph<T> g, T initial, T destination);

}
