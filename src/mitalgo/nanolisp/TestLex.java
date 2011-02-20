package mitalgo.nanolisp;

import java.util.List;

import junit.framework.TestCase;

public class TestLex extends TestCase {

	public void testBasic() {
		
		
		Lex lex = new Lex();
		List<Lex.Token> tokens = lex.read("( + 1 12 \"hola mundo\")");
		
		assertEquals(6, tokens.size());
		assertTrue(tokens.get(0).isOpenPar());
		assertTrue(tokens.get(1).isAtom());
		assertEquals("+", tokens.get(1).value());
		assertTrue(tokens.get(2).isSymbol());
		assertEquals("1", tokens.get(2).value());
		assertTrue(tokens.get(3).isSymbol());
		assertEquals("12", tokens.get(3).value());
		assertTrue(tokens.get(4).isSymbol());
		assertEquals("\"hola mundo\"", tokens.get(4).value());
		assertTrue(tokens.get(5).isClosePar());
	}
}
