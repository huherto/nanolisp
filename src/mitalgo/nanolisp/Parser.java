package mitalgo.nanolisp;

import java.util.List;
import java.util.ListIterator;

import mitalgo.nanolisp.Lex.Token;


public class Parser {
	
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
				tokens.previous();
				node.add(parseListOrAtom(tokens));
			}
		}
		else if (token.isAtom()) {
			if (token.isNumberSymbol()) {
				String value = token.value();
				if (value.indexOf('.') >= 0) {
					Float num = new Float(value);
					return new Node(num);
				}
				else {
					Integer num = new Integer(value);
					return new Node(num);					
				}
			}
			else if (token.isSymbol()) {
				return new Node(token.value(), true);
			}
			else {
				String value = token.value();
				if (value.indexOf('\"') == 0) {
					value = value.substring(1, value.length() - 1);					
				}
				return new Node(value);
			}
		}
		else {
			throw new SyntaxError("Unexpected "+token.toString());
		}
	}
}
