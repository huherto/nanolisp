package mitalgo.nanolisp;

import java.util.List;
import java.util.ListIterator;

import mitalgo.nanolisp.Lex.Token;


public class Parser {
	
	class SyntaxError extends Error {
		private static final long serialVersionUID = 1L;		
	}

	public Node read(String input) {
		Lex lex = new Lex();
		List<Lex.Token> tokens = lex.read(input);
		return read(tokens);
	}
	
	public Node read(List<Token> tokens) {
		return parseListOrAtom(tokens.listIterator());
	}
	
	public Node parseListOrAtom(ListIterator<Token> tokens) {
		Token token = tokens.next();
		if (token.isOpenPar()) {
			Node node = new Node();
			while(true) {
				token = tokens.next();
				if (token.isClosePar()) {
					return node;
				}
				else if (token.isOpenPar()) {
					tokens.previous();
					node.add(parseListOrAtom(tokens));
				}
				else {
					tokens.previous();
					node.add(parseListOrAtom(tokens));
				}
			}
		}
		else if (token.isAtom()) {
			return new Node(token.value());
		}
		else {
			throw new SyntaxError();
		}
	}
}