package mitalgo.nanolisp;

import java.util.List;

import junit.framework.TestCase;

public class TestLex extends TestCase {

	public void testBasic() {
		
		
		Lex lex = new Lex();
		List<Lex.Token> tokens = lex.read("( + 1 12 \"hola mundo\")");
		
		assertEquals(5, tokens.size());
		assertEquals(Lex.OPEN_PAR, tokens.get(0).type());
		assertEquals(Lex.ATOM, tokens.get(1).type());
		assertEquals("+", tokens.get(1).value());
		assertEquals(Lex.SYMBOL, tokens.get(2).type());
		assertEquals("1", tokens.get(2).value());
		assertEquals(Lex.SYMBOL, tokens.get(3).type());
		assertEquals("12", tokens.get(3).value());
		assertEquals(Lex.SYMBOL, tokens.get(4).type());
		assertEquals("\"hola mundo\"", tokens.get(4).value());
		assertEquals(Lex.CLOSE_PAR, tokens.get(5).type());
	}
}
