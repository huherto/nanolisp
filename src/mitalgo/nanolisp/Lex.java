package mitalgo.nanolisp;

import java.util.ArrayList;
import java.util.List;

public class Lex {
	
	class Token {
		String value() {
			return null;
		}
		
		boolean isAtom() {
			return false;
		}
		
		boolean isOpenPar() {
			return false;
		}
		
		boolean isClosePar() {
			return false;			
		}
		
		boolean isSymbol() {
			return false;	
		}
		
		@Override
		public String toString() {
			return "Token";
		}
	}
	
	class Atom extends Token {
		String value;
		
		Atom(String value) {
			this.value = value;
		}
		
		@Override
		boolean isAtom() {
			return true;
		}		
		
		@Override
		String value() {
			return value;
		}

		@Override
		public String toString() {
			return "Atom["+value+"]";
		}
		
	};
	
	class Symbol extends Atom {

		Symbol(String value) {
			super(value);
		}

		@Override
		boolean isSymbol() {
			return true;
		}

		@Override
		public String toString() {
			return "Symbol["+value+"]";
		}
		
	};
	
	class OpenPar extends Token {

		@Override
		boolean isOpenPar() {
			return true;
		}

		@Override
		public String toString() {
			return "OpenPar";
		}
	};
	
	class ClosePar extends Token {
		@Override
		boolean isClosePar() {
			return true;
		}

		@Override
		public String toString() {
			return "ClosePar";
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
					if (idx == input.length() - 1) {
						res.add(new Symbol(str.toString()));
						return res;
					}
					idx++;
					ch = input.charAt(idx);
				} while (Character.isDigit(ch));
				res.add(new Symbol(str.toString()));
				idx--;
			}
			else if (ch == '"') {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					idx++;
					ch = input.charAt(idx);
				} while (ch != '"');
				str.append(ch);
				res.add(new Symbol(str.toString()));
			}
			else {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					if (idx == input.length() - 1) {
						res.add(new Atom(str.toString()));
						return res;
					}
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
