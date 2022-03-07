package york.eecs.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import york.eecs.source.UndirectedGraph;
import york.eecs.source.UndirectedGraphAlgorithms;
import york.eecs.source.VertexExistsException;

public class StudentTest {

	 /**
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraph.
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraphAlgorithms.
	  */
	
	// Test cases for the undirected graph
	
	UndirectedGraph<String> ugStr = new UndirectedGraph<>();
	UndirectedGraph<Integer> ugInt = new UndirectedGraph<>();
	UndirectedGraph<Double> ugDouble = new UndirectedGraph<>();
	
	
	public void populateGraph() {
		try {
			// for graph containing strings
			ugStr.addVertex("A");
			ugStr.addVertex("C");
			ugStr.addVertex("B");
			ugStr.addVertex("Z");
			ugStr.addVertex("Q");
//			ugStr.addVertex("C");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			// for graph containing integers
			ugInt.addVertex(4);
			ugInt.addVertex(10);
			ugInt.addVertex(8);
			ugInt.addVertex(1);
			ugInt.addVertex(3);
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// for graph containing double
			ugDouble.addVertex(4.3);
			ugDouble.addVertex(10.95);
			ugDouble.addVertex(8.0);
			ugDouble.addVertex(1.125);
			ugDouble.addVertex(3.98);
			
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testisEmpty() {
		assertTrue(ugStr.isEmpty());
	}
	
	
	@Test
	public void testGetSizeUgStr() {
		populateGraph();
		int ugSize = ugStr.getSize();
		int expectedSize = 5;
		assertEquals(ugSize, expectedSize);
	}
	
	@Test
	public void testGetSizeUgInt() {
		populateGraph();
		int ugSize = ugInt.getSize();
		int expectedSize = 3;
		assertFalse(ugSize == expectedSize);
	}
	
	@Test
	public void testGetSizeUgDouble() {
		populateGraph();
		int ugSize = ugDouble.getSize();
		int expectedSize = 5;
		assertEquals(ugSize, expectedSize);
	}
	
	
	

	@Test
	public void testGetVerticesStr() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("D");
			ug.addVertex("C");
			ug.addVertex("B");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> ugVertices = (ArrayList<String>) ug.getVertices();
		ugVertices.sort((s1, s2) -> s1.compareTo(s2)); // this sorts the vertices
		List<String> expectedVertices = new ArrayList<>();
		expectedVertices.add("A");
		expectedVertices.add("B");
		expectedVertices.add("C");
		expectedVertices.add("D");
		assertEquals(ugVertices, expectedVertices);
	}
	
	@Test
	public void testGetVerticesInt() {
		UndirectedGraph<Integer> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex(90);
			ug.addVertex(1);
			ug.addVertex(-2);
			ug.addVertex(6);
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> ugVertices = (ArrayList<Integer>) ug.getVertices();
		ugVertices.sort((s1, s2) -> s1.compareTo(s2)); // this sorts the vertices
		List<Integer> expectedVertices = new ArrayList<>();
		expectedVertices.add(-2);
		expectedVertices.add(1);
		expectedVertices.add(6);
		expectedVertices.add(90);
		assertEquals(ugVertices, expectedVertices);
	}

	@Test
	public void testGetAdjacent() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("G");
			ug.addVertex("Z");
			ug.addVertex("B");
			ug.addEdge("Z", "G");
			ug.addEdge("G", "A");
			ug.addEdge("A", "B");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> aAdjacent = (ArrayList<String>) ug.getAdjacent("A");
		aAdjacent.sort((s1, s2) -> s1.compareTo(s2));
		List<String> expectedAdjacent = new ArrayList<>();
		expectedAdjacent.add("B");
		expectedAdjacent.add("G");
		assertEquals(aAdjacent, expectedAdjacent);
	}
	
	
	@Test
	public void testToString() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String toSt = ug.toString();
		String expected = "Graph:\nVertex: A & Adjacent Vertices: [B, C]\nVertex: B & Adjacent Vertices: [A]\nVertex: C & Adjacent Vertices: [A]\n";
	    assertEquals(toSt, expected);
	}
	
	
	// Test cases for BFS
	
	UndirectedGraphAlgorithms<Integer> ugaInt;
	UndirectedGraphAlgorithms<String> ugaStr;
	@Before
	public void setUp() throws Exception {
		ugaInt = new UndirectedGraphAlgorithms<>();
		ugaStr = new UndirectedGraphAlgorithms<>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testBFSInt() {
		UndirectedGraph<Integer> ug = new UndirectedGraph<>();
		int[] vertices = new int[] {1, 2, 3, 4, 5, 19, 23, 25, 24, 40};
		try {
			for(int i : vertices)
				ug.addVertex(i);
			ug.addEdge(3, 1);
			ug.addEdge(1, 5);
			ug.addEdge(1, 25);
			ug.addEdge(1, 19);
			ug.addEdge(19, 2);
			ug.addEdge(2, 23);
			ug.addEdge(3, 4);
			ug.addEdge(4, 40);
			ug.addEdge(25, 40);
		} catch (VertexExistsException e) {
			e.printStackTrace();
		}
		
		List<Integer> result = (ArrayList<Integer>) ugaInt.findBFSpath(ug, 1, 23);
		List<Integer> expected = new ArrayList<Integer>() {{			
			add(1);
			add(19);
			add(2);
			add(23);
			}};
		assertEquals(expected, result);
	}
	
	@Test
	public void testBFS1() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();
		String[] vertices = new String[] {"A", "B", "C", "G", "D"};
		try {
			for(String i : vertices)
				ug.addVertex(i);
			ug.addEdge("A", "D");
			ug.addEdge("B", "C");
			ug.addEdge("B", "G");
			ug.addEdge("D", "G");
			ug.addEdge("G", "C");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) ugaStr.findBFSpath(ug, "A", "C");
		List<String> expected = new ArrayList<>();
		expected.add("A");
		expected.add("D");
		expected.add("G");
		expected.add("C");
		assertEquals(expected, result);
	}
	
	@Test
	public void testBFS2() {
		UndirectedGraph<Integer> ug = new UndirectedGraph<>();
		int[] vertices = new int[] {0, 1, 2, 3};
		try {
			for(int i : vertices)
				ug.addVertex(i);
			ug.addEdge(0, 1);
			ug.addEdge(0, 2);
			ug.addEdge(1, 3);
			ug.addEdge(2, 3);
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> result = (ArrayList<Integer>) ugaInt.findBFSpath(ug, 0, 3);
		List<Integer> expected = new ArrayList<>();
		expected.add(0);
		expected.add(1);
		expected.add(3);
		assertEquals(expected, result);
	}
	
	@Test
	public void testBFS3() {
		UndirectedGraph<Integer> ug = new UndirectedGraph<>();
		int[] vertices = new int[] {0, 1, 2, 3, 4};
		try {
			for(int i : vertices)
				ug.addVertex(i);
			ug.addEdge(0, 4);
			ug.addEdge(0, 3);
			ug.addEdge(0, 1);
			ug.addEdge(1, 3);
			ug.addEdge(1, 2);
			ug.addEdge(1, 4);
			ug.addEdge(2, 3);
			ug.addEdge(3, 4);
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> result = (ArrayList<Integer>) ugaInt.findBFSpath(ug, 1, 0);
		List<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(0);
		assertEquals(expected, result);
	}
	
	@Test
	public void testCyclicPath() {
		UndirectedGraph<Integer> ug = new UndirectedGraph<>();
		int[] vertices = new int[] {0, 1, 2, 3, 4};
		try {
			for(int i : vertices)
				ug.addVertex(i);
			ug.addEdge(0, 1);
			ug.addEdge(0, 2);
			ug.addEdge(0, 3);
			ug.addEdge(1, 2);
			ug.addEdge(3, 4);
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> result = (ArrayList<Integer>) ugaInt.findBFSpath(ug, 2, 4);
		List<Integer> expected = new ArrayList<>();
		expected.add(2);
		expected.add(0);
		expected.add(3);
		expected.add(4);
		assertEquals(expected, result);
	}
}
