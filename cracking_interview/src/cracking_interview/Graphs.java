package cracking_interview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * This tree is a connected graph without cycles, hence a tree is a type of graph, 
 * hence a tree is a type of graph, but not all graphs are trees.
 * Representation - adjacent List
 * @author muhammedadeyemi
 *
 */
public class Graphs {
	//
	private HashMap<Integer, Node> nodeLookup = new HashMap<Integer, Node>();
	
	public static class Node{
		private int id; //represents node id
		LinkedList<Node> adjacent = new LinkedList<>(); //adjacent nodes
	
		private Node(int id) { //constructor
			this.id = id;
		}
	}
	
	//gets the node with the specified ID
	private Node getNode(int id) {
		Node out = nodeLookup.get(id);
		if(out == null) {
			throw new NullPointerException("Node doesn't exist");
		}
		return  nodeLookup.get(id);
	}
	
	public void addEdge(int src, int dest) {
		Node s = getNode(src);
		Node d = getNode(dest);
		s.adjacent.add(d); //add dest as a child of src
		d.adjacent.add(s);
	}
	
	public void addVertex(int v) {
		nodeLookup.put(v, new Node(v));
	}
	
	/**
	 * DEPTH FIRST SEARCH --> “better” at answering connectivity queries (determining if every pair of vertices can be connected by two disjoint paths).
	 * cons: uses a lot of memory for sparse graphs 
	 * 
	 * Applications;
	 * finding connectivity, 
	 * find bridges/articulation points, 
	 * count connected components
	 */
	public boolean hasPathDFS(int src, int dest) {

		//set to store already visited nodes id or
		//we could create a flag in the node class boolean alreadyVisited, 
		//but this will require us clearing it after iteration
		HashSet<Integer> visited = new HashSet<Integer>();
		return hasPathDFS(getNode(src), getNode(dest), visited); //call recursive method
	}
	

	private boolean hasPathDFS(Node src, Node dest, HashSet<Integer> visited) {//complexity is O(V+E)
		
		if(visited.contains(src.id)) //if i have already visited this node return false
			return false;
		
		visited.add(src.id); 		//otherwise update/mark node as visited
		
		
		if(src == dest) //if src is destination, return true. path found
			return true;
		
		//otherwise check all nodes in children to find path
		//do this by recursively calling the same function and setting src as child
		for(Node child : src.adjacent) {
			if(hasPathDFS(child, dest, visited)) //if path is found return true
				return true;
		}
		
		return false; //there is no path
	}
	
	
	//Iterative DFS
	public boolean dfsSearch(int s, int d) {
		Node src, dest;
		src = getNode(s);
		dest = getNode(d);
		
		Set<Node> visited = new HashSet<Node>();
		
		Stack<Node> s1 = new Stack<Node>(); //needed for dfs
		
		visited.add(src);
		s1.push(src);
		
		while(!s1.isEmpty()) {
			Node v = s1.pop();
			
			if(v == dest)
				return true;
			
			//gell all adjacent vertices of popped vertex v
			for(Node adj : v.adjacent) {
				if(!visited.contains(adj)) {
					visited.add(adj);
					s1.push(adj);
				}
			}
		}
		
		return false;
	}
	
	
	
	/*
	 * BREADTH FIRST SEARCH --> “better” at finding the shortest path in a graph
	 * cons: uses a lot of memory in traversing dense graphs
	 *
	 * Applications;
	 * 	Check if a graph is connected	
	 *	Generating spanning tree
	 *	Finding the shortest path in a graph
	 */
	//takes in src and dest ids
	public boolean hasPathBFS(int src, int dest) {
		return hasPathBFS(getNode(src), getNode(dest));
	}
	
	
	private boolean hasPathBFS(Node src, Node dest) {//non recursive
		
		//representing queue
		Queue<Node> nextToVisit = new LinkedList<Node>(); //nodes thats yet to be visited
		HashSet<Integer> visited = new HashSet<Integer>(); //stores visited nodes id
		
		nextToVisit.add(src); //visit src first 
		
		while(!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();  //remove from end of queue
			
			if(node == dest) //dest found so path found
				return true;
			
//			if(visited.contains(node.id))//if visited skip
//				continue;
			
			visited.add(node.id); //mark node as visited
			
			for(Node child : node.adjacent) {//add adjacent node to nextToVisit queue
				if(!visited.contains(child.id))
					nextToVisit.add(child);	
			}
		}
		return false;
	}
	
	
	/* Bi-directional search*/
	//search if path exists between two points
	public boolean biDirectionalSearch(int a, int b) {
		Node nodeA = getNode(a);
		Node nodeB = getNode(b);
		
		//need two queues to hold nodes to iterate
		Queue<Node> qA = new LinkedList<>(); //for node1
		Queue<Node> qB = new LinkedList<>(); //for node2
		
		qA.add(nodeA);
		qB.add(nodeB);
		
		//need two sets to hold visited nodes
		Set<Integer> visitedA = new HashSet<>();
		Set<Integer> visitedB = new HashSet<>();

		
		//iterate through both nodes
		while(!qA.isEmpty() && !qB.isEmpty()) {
			if(pathExists(qA, visitedA , visitedB)) //checks for the adj nodes of the first node
				return true;
			if(pathExists(qB, visitedB, visitedA)) //checks for the adj nodes of the second node
				return true;
//			System.out.println("list1: " + visitedA.toString());
//			System.out.println("list2: " + visitedB.toString());
		}
		return false;
	}

	
	/**
	 * 
	 * @param q
	 * @param visited
	 * @param otherVisited
	 * @param dest other path
	 * @return true if path is found otherwise false
	 */
	private boolean pathExists(Queue<Node> nextToVisit, Set<Integer> visited, Set<Integer> otherVisited) {
		//only checks if the front of the queue is the destination
		if(!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove(); 
			
			//add to visited nodes
			visited.add(node.id);
			for(Node adj : node.adjacent) { //add adjacent node to queue
				if(otherVisited.contains(adj.id)) {
					return true;
				}
				else if(!visited.contains(adj.id))
					nextToVisit.add(adj);
			}
		}
		return false;
	}
	
	
	

	
	
	/**
 * 	Given an undirected graph represented as an adjacency matrix and an integer k,
	write a function to determine whether each vertex in the graph can be colored such that no two adjacent vertices share the same color using at most k colors.
	
	solution build up
	adjacency matrix is an N by N boolean matrix (where N is the number of nodes)
	a true value at matrix[i][j] indicates an edge from node i to node j.
	i can represent this by building a list of hash-sets(representing connected edges)
	k is the maximum number of colors
	
	
	EXAMPLE
	
	Input:  
	graph = {0, 1, 1, 1},
	        {1, 0, 1, 0},
	        {1, 1, 0, 1},
	        {1, 0, 1, 0}
	Output: 
	Solution Exists: 
	Following are the assigned colors
	 1  2  3  2
	Explanation: By coloring the vertices 
	with following colors, adjacent 
	vertices does not have same colors
	
	Input: 
	graph = {1, 1, 1, 1},
	        {1, 1, 1, 1},
	        {1, 1, 1, 1},
	        {1, 1, 1, 1}
	Output: Solution does not exist.
	Explanation: No solution exits.
	 * 
	 */
	
	public static boolean colorVertices(int[][] edges, int k) {//solution: https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/
		Queue<Integer> q = new LinkedList<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		
		HashMap<Integer, Integer> colors = new HashMap<>(); //to store colors, key represents the vertex and value is color

				
		visited.add(0); //starting with source 0, set as visited
		q.add(0);
		
//		colors.put(0, k);//store color of first vertex
		int v, c = k;
	
		while(!q.isEmpty()) {
			
			v = q.poll();
			System.out.println(v);
			HashSet<Integer> adjColors = new HashSet<Integer>();
			
			
			for(int i = 0; i < edges[v].length; i++) {
				
				
				
				if(edges[v][i] == 1) { //only for connected edges
					
					int adj = i;
					
//					System.out.println("EDGE EXISTS BETWEEM: " + v + " and " + adj);
					
					if(colors.containsKey(adj)) //add all adjacent colors to set
						adjColors.add(colors.get(adj));
					
					
					if(!visited.contains(adj))
						q.add(adj);
				}

			}
			
			//check if all numbers in range colors from 1 to and including k exists in adjColors
			boolean check = true;
			for(int i = 1; i <= k; i++) {
				if(!adjColors.contains(i)) {
					check = false;
					break;
				}
			}
			
			if(check) {
				System.out.println("reachable");
				return false;
			}
				
			//add color
			if(colors.size() == 0) //first iteration
				c = k;
			
			while(adjColors.contains(c)) 
				--c;
			
			colors.put(v,  c);
				
		}
		
		System.out.println("here: " + colors.toString());
		
		return true;
	}


	 
	public static boolean directedGraphPath(int[][] edges, int s, int e) {
		
		
		//base case
		if(s == e)
			return true;
		
		int size = edges.length;
		
		//convert graph to adjacency list
		ArrayList<HashSet<Integer>> graph = convertHash(edges);
		
		
		//perform BFS, remember only checking for path from s to e
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet();
		
		queue.offer(s);
		visited.add(s);
	
		
		while(!queue.isEmpty()) {
			
			for(int i = 0; i < queue.size(); i++) {
				int currV = queue.poll();
				
				
				if(currV == e)//path found
					return true;
				
				//check adjacent nodes
				for(int adj : graph.get(currV)) {
					if(!visited.contains(adj)) {
						visited.add(adj);
						queue.offer(adj);
					}				
				}
			}
		}
		
		return false;
	}
		
	
	private static void swap(int[] arr, int indx1, int indx2) {
		int temp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = temp;
	}
	
	
	//method to convert adjacency matrix to list
	private static ArrayList<HashSet<Integer>> convertHash(int[][] edges) {//O(n^2) -- worst case a vertex is connected to every vertices so we have to travel through all vertices 
		
		int l = edges[0].length;
		
		ArrayList<HashSet<Integer>> adjListSet = new ArrayList<HashSet<Integer>>(l);
		
		//create a new hashset for each vertex
		//such that adjacent nodes can be stored
		for(int i = 0; i < l; i++) 
			adjListSet.add(new HashSet<Integer>());
		
		for(int i = 0; i < edges.length; i++) {//using iterator to loop through 2d array
			for(int j = 0; j < edges[i].length; j++) {
				int curr = edges[i][j];
				if(curr > 0)
					adjListSet.get(i).add(j);
			}
		}
		
//		for(int i = 0; i < edges.length; i++) {
//			System.out.println(i + " position " + adjListSet.get(i).toString());
//		}
		
		return adjListSet;
	}
	
	
    // Function to convert adjacency
    // list to adjacency matrix
    private static ArrayList<ArrayList<Integer>> convertList(int[][] a) {//takes O(n^2) polynomial time
        // no of vertices
        int l = a[0].length;
   
        ArrayList<ArrayList<Integer>> adjListArray = new ArrayList<ArrayList<Integer>>(l);
        
 
        // Create a new list for each
        // vertex such that adjacent
        // nodes can be stored
        for (int i = 0; i < l; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }
         
        int i, j;
        for (i = 0; i < a[0].length; i++) {
            for (j = 0; j < a.length; j++) {
                if (a[i][j] == 1) {
                    adjListArray.get(i).add(j);
                }
            }
        }
         
//        for(int c = 0; c < adjListArray.size(); c++) {
//        	System.out.println(c + " position " + adjListArray.get(c).toString());
//        }
        
        return adjListArray;
    }
	
    
    /**
     * This algorithm helps find the shortest distance to a vertex
     * Dijkstra does this by trying to minimize the path from the start, which is done at every vertex
     * 
     * DIJSKTRAS ALGORITHM TAKES A LIST OF ALL VERTICES AND THEIR RESPECTIVE EDGES AND SORTS THEM IN ASCENDING ORDER
     * - cons: it cannot handle negative edges
     * 
     * - you can use a minimum dequeue for this and keep removing from the front for exploration
     * - explore adjacent vertices, except the vertex that exists as a parent
     * - hence we have to keep three data structures 
     * - first to store our list of vertices - dequeue
     * - second to store parents of each vertex - HashMap - for constant lookup (key being the vertex and value being the parent vertex)
     * - Although we also need to keep track of the adjacent vertices of a particular vertex, hmm i guess we can use a queue for that.
     * Also when checking for the possible minimum path for each vertex, if the vertex holds the largest value in the graph(min-queue), there's no need checking
     * - space complexity will be O(n)
     */
    
    /**
     * A spanning tree is a subset of a graph such that contains all the vertices of the tree, 
     * it has all vertices connected with minimum possible number of edges
     * PRIM'S algorithm has the same goal as Dijkstra,
     * Prim creates a spanning tree and tries to minimize the edge that is being added to the growing spanning tree.
     * 
     * - Get full scope of all edges connected to start and end end vertices (can be stored in a list) -> we need to remove and replace it with the vertex that has the smallest edge
     * - Choose the vertex with the minimum edge (cannot choose an edge connecting to a vertex already visited)
     * - we need a HashSet to store visited vertices
     * - space complexity is O(n) 
     */
    
    
    
    // DATE: FRIDAY, JANUARY 2022 -> 2:26 AM
	
	// using BFS find the shortest path between two vertices
	public static List<Integer> findPath(int[][] g, int src, int dest) {
		
		
		List<Integer> output = new ArrayList<>();
		
		// for the iteration amongst vertices
		Queue<List<Integer>> q = new ArrayDeque<>();
		
		// populate queue with a src
		q.add(Arrays.asList(src));
		
		while(!q.isEmpty()) {
			List<Integer> path = q.remove(); // gets edge between two vertices
			
			// check if last vertex in path is destination
			if(path.get(path.size()-1).compareTo(dest) == 0)
				return path;
			
			// creates path between last added vertex in path and each of its adjacent vertices
			for(int adj : g[path.get(path.size() - 1)]) {
				List<Integer> next = new ArrayList(path);
				next.add(adj);
				q.add(next);
			}
		}
		
		
		return output;
	}
	
	
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        
        List<List<Integer>> res = new ArrayList<>();    // this stores possible paths for all vertices
        Queue<List<Integer>> q = new ArrayDeque<>();    // for BFS Iteration, this holds lists
        
        q.add(Arrays.asList(0)); // populating queue with a list containing only zero
        
        while(!q.isEmpty()) { 
            List<Integer> edge = q.remove(); // removes the head of the queue which is an edge between two vertices represented as a list
            
            // checks if last element of edge is equal to last index of graph, graph.length - 1 is represented as terminal node
            if(edge.get(edge.size() - 1) == graph.length - 1) { 
                res.add(edge);
                continue;
            }
            
            // creates a path between a vertex and each of its adjacent vertices
            for(int adj : graph[edge.get(edge.size() - 1)]) {
                List<Integer> next = new ArrayList(edge); 
                next.add(adj); 
                q.add(next);
            }
        }
        
        return res;
    }

	public static void main(String[] args) {
		
//		colorVertices
		int[][] graph = {{0, 1, 1, 1},
		        {1, 0, 1, 0},
		        {1, 1, 0, 1},
		        {1, 0, 1, 0}};
        
        System.out.println(colorVertices(graph, 3));
	}

}
