package mitalgo.nanolisp;

import java.util.List;

import junit.framework.TestCase;

public class TestParser extends TestCase {
	
	public void testBasic() {
		Parser parser = new Parser();
		Node node = parser.read("12");
		assertNotNull(node);
		assertEquals(new Integer(12), node.number());
		assertEquals("12", node.toString());
		
		node = parser.read("( 12 )");
		assertNotNull(node);
		assertTrue(node.isList());
		assertEquals(1, node.getList().size());
		assertEquals("12", node.getList().get(0).number().toString());
		assertEquals("(12)", node.toString());
		
		node = parser.read("( + 10 15 )");
		assertNotNull(node);
		assertTrue(node.isList());
		assertEquals(3, node.getList().size());
		assertEquals("+", node.getList().get(0).strValue());
		assertEquals("10", node.getList().get(1).number().toString());
		assertEquals("15", node.getList().get(2).number().toString());
		assertEquals("(+ 10 15)", node.toString());
		
		node = parser.read("( list ( 5 3 2) )");
		assertNotNull(node);
		assertTrue(node.isList());
		assertEquals(2, node.getList().size());
		List<Node> list1 = node.getList();
		assertEquals("list", list1.get(0).strValue());
		List<Node> list2 = list1.get(1).getList();
		assertEquals(3, list2.size());
		assertEquals("(list (5 3 2))", node.toString());
	}

}
