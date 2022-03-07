package york.eecs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import york.eecs.source.UndirectedGraph;
import york.eecs.source.UndirectedGraphAlgorithms;
import york.eecs.source.VertexExistsException;

//import graphs.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

public class ProfTest {

	
	@Before
	public void setUp() throws Exception {

	}


	@After
	public void tearDown() throws Exception {
	}

	@Test(timeout=100)
	@Description(description="Testing isEmpty() method on an empty graph")
	public void Grading_testEmptyGraphTrue() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();
		assertTrue(ug.isEmpty());
	}

	@Test(timeout=100)
	@Description(description="Testing isEmpty() method on a non-empty graph")
	public void Grading_testEmptyGraphFalse() {
		UndirectedGraph<Integer> ug = new UndirectedGraph<>();
		try {
			ug.addVertex(99);
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(ug.isEmpty());
	}
	
	@Test(timeout=100)
	@Description(description="Testing one-vertex bfs must return a list with one vertex")
	public void Grading_oneVertexBFS() {
		UndirectedGraph<Integer> ug = new UndirectedGraph<>();
		UndirectedGraphAlgorithms<Integer> ugi = new UndirectedGraphAlgorithms<>();
		try {
			ug.addVertex(5);
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> result = (ArrayList<Integer>) ugi.findBFSpath(ug, 5, 5);
		List<Integer> expected = new ArrayList<>();
		expected.add(5);
		assertEquals(expected, result);
	}
	
	@Test(timeout=100)
	@Description(description="Testing addEdge on a two vertex graph")
	public void Grading_testAddEdge() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addEdge("A", "B");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = ug.getAdjacent("A");
		List<String> expected = new ArrayList<String>();
		expected.add("B");
		assertEquals(expected, list);
	}
	
	@Test(timeout=100)
	@Description(description="Testing addVertex on an empty graph")
	public void Grading_testAddVertex() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = ug.getVertices();
		List<String> expected = new ArrayList<String>();
		expected.add("A");
		assertEquals(expected, result);
	}
	
	@Test(timeout=100)
	@Description(description="Testing addVertex method with a duplicate vertex")
	public void Grading_testExistVertex() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("C");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		int ugSize = ug.getSize();
		int expectedSize = 3;
		assertEquals(expectedSize, ugSize);
	}
	
	@Test(timeout=100)
	@Description(description="Testing getAdjacent a non-empty graph")
	public void Grading_testGetAdjacent() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("D");
			ug.addVertex("E");
			ug.addVertex("F");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
			ug.addEdge("C", "D");
			ug.addEdge("C", "E");
			ug.addEdge("C", "F");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> aAdjacent = (ArrayList<String>) ug.getAdjacent("C");
		aAdjacent.sort((s1, s2) -> s1.compareTo(s2));
		List<String> expectedAdjacent = new ArrayList<>();
		expectedAdjacent.add("A");
		expectedAdjacent.add("D");
		expectedAdjacent.add("E");
		expectedAdjacent.add("F");
		assertEquals(expectedAdjacent, aAdjacent);
	}
	
	@Test(timeout=100)
	@Description(description="Testing BFS on A-B, A-C, A-D, B-C, B-D, B-E, C-D, E-F")
	public void Grading_TestBFS() {
		UndirectedGraph<String> ug = new UndirectedGraph<>();
		UndirectedGraphAlgorithms<String> uga = new UndirectedGraphAlgorithms<>();
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("D");
			ug.addVertex("E");
			ug.addVertex("F");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
			ug.addEdge("A", "D");
	
			
			ug.addEdge("B", "C");
			ug.addEdge("B", "D");
			ug.addEdge("B", "E");
		
			ug.addEdge("C", "D");
			
			ug.addEdge("E", "F");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "A", "F");
		List<String> expected = new ArrayList<>();
		expected.add("A");
		expected.add("B");
		expected.add("E");
		expected.add("F");
		assertEquals(expected, result);
	}
}

