package mitalgo.nanolisp;

import java.util.ArrayList;
import java.util.List;

public class Lex {
	
	public static final String OPEN_PAR = "OPEN_PAR";
	public static final String CLOSE_PAR = "CLOSE_PAR"; 
	public static final String ATOM = "ATOM"; 
	public static final String SYMBOL = "SYMBOL"; 
	
	interface Token {
		String value();
		String type();
	}
	
	class Atom implements Token {
		String value;
		
		Atom(String value) {
			this.value = value;
		}
		
		@Override
		public String value() {
			return value;
		}

		@Override
		public String type() {
			return ATOM;
		}
		
	};
	
	class Symbol extends Atom {

		Symbol(String value) {
			super(value);
		}
		
		@Override
		public String type() {
			return SYMBOL;
		}
	};
	
	class OpenPar implements Token {

		@Override
		public String value() {
			return "(";
		}

		@Override
		public String type() {
			return OPEN_PAR;
		}
		
	};
	
	class ClosePar implements Token {

		@Override
		public String value() {
			return ")";
		}

		@Override
		public String type() {
			return CLOSE_PAR;
		}
		
	};
	
	public List<Token> read(String input) {
		List<Token> res =  new ArrayList<Token>();
		
		int idx = 0;
		while(idx < input.length()) {
			char ch = input.charAt(idx);
			if (ch == '(') {
				res.add(new OpenPar());
			}
			else if (ch == ')') {
				res.add(new ClosePar());
			}
			else if (ch == ' ') {
				
			}
			else if (Character.isDigit(ch)) {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					idx++;
					ch = input.charAt(idx);
				} while (Character.isDigit(ch));
				res.add(new Symbol(str.toString()));
				idx--;
			}
			else {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					idx++;
					ch = input.charAt(idx);
				} while (ch != ' ' && ch != '(' && ch !=  ')');
				res.add(new Atom(str.toString()));
				idx--;
			}
			idx++;
		}
		return res;
	}	

}
