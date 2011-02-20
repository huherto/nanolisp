package mitalgo.nanolisp;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import static java.text.CharacterIterator.DONE;

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
		
		CharacterIterator iter = new StringCharacterIterator(input);
		for(char ch = iter.first(); ch != DONE; ch = iter.next()) {
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
					ch = iter.next();
				} while (ch != DONE && Character.isDigit(ch));
				res.add(new Symbol(str.toString()));
				ch = iter.previous();
			}
			else if (ch == '"') {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					ch = iter.next();
				} while (ch != '"');
				str.append(ch);
				res.add(new Symbol(str.toString()));
			}
			else {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					ch = iter.next();
				} while (ch != DONE && ch != ' ' && ch != '(' && ch !=  ')');
				res.add(new Atom(str.toString()));
				ch = iter.previous();
			}
		}
		return res;
	}	

}
