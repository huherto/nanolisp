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
		assertTrue(tokens.get(3).isNumberSymbol());
		assertEquals("12", tokens.get(3).value());
		assertTrue(tokens.get(4).isSymbol());
		assertEquals("\"hola mundo\"", tokens.get(4).value());
		assertTrue(tokens.get(5).isClosePar());
		
		tokens = lex.read("+");
		assertEquals(1, tokens.size());
		assertTrue(tokens.get(0).isAtom());
		
		tokens = lex.read("12");
		assertEquals(1, tokens.size());
		assertTrue(tokens.get(0).isSymbol());

		tokens = lex.read("\"hola mundo\"");
		assertEquals(1, tokens.size());
		assertTrue(tokens.get(0).isSymbol());
		assertEquals("\"hola mundo\"", tokens.get(0).value());
		
		tokens = lex.read("( + 2 2) ; Este es un comentario\n");
		assertEquals(5, tokens.size());
		
		tokens = lex.read("(list '(John Q Public) )");
		assertEquals(4, tokens.size());
		assertEquals("(John Q Public)", tokens.get(2).value());

	}
}
