package mitalgo.nanolisp;

import java.util.ArrayList;
import java.util.List;

public class Lex {

	interface Token {
		
	}
	
	enum TokenTypes implements Token {
		OpenPar, ClosePar, Atom
	}
	
	public List<Token> read(String input) {
		List<Token> res =  new ArrayList<Token>();
		
		int idx = 0;
		while(idx < input.length()) {
			int ch = input.charAt(idx);
			if (ch == '(') {
				res.add(TokenTypes.OpenPar);
			}
			else if (ch == ')') {
				res.add(TokenTypes.ClosePar);
			}
			else if (ch == ' ') {
				
			}
			else {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					idx++;
					ch = input.charAt(idx);
				} while (ch != ' ' && ch != '(' && ch !=  ')');
				res.add(TokenTypes.Atom);
				idx--;
			}
			idx++;
		}
		return res;
	}	

}
