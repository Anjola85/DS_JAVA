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

public class UndirectedGraphAlgorithmsTest {

	UndirectedGraphAlgorithms<String> uga;
	@Before
	public void setUp() throws Exception {
		uga = new UndirectedGraphAlgorithms<>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBFS() {
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
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "B", "C");
		List<String> expected = new ArrayList<>();
		expected.add("B");
		expected.add("A");
		expected.add("C");
		assertEquals(expected, result);
	}

}