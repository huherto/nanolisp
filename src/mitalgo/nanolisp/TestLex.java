package mitalgo.nanolisp;

import java.util.List;

import junit.framework.TestCase;

public class TestLex extends TestCase {

	public void testBasic() {
		
		
		Lex lex = new Lex();
		List<Lex.Token> tokens = lex.read("( 1 )");
		
		assertEquals(3, tokens.size());
		assertEquals(Lex.TokenTypes.OpenPar, tokens.get(0));
		assertEquals(Lex.TokenTypes.Atom, tokens.get(1));
		assertEquals(Lex.TokenTypes.ClosePar, tokens.get(2));
	}
}
