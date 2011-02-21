package mitalgo.nanolisp;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import static java.text.CharacterIterator.DONE;
import static java.lang.Character.isDigit;
import static java.lang.Character.isSpaceChar;

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
		
		public boolean isNumberSymbol() {
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

		public Symbol(String value) {
			super(value);
		}

		@Override
		public boolean isSymbol() {
			return true;
		}

		@Override
		public String toString() {
			return "Symbol["+value+"]";
		}
		
	};
	
	class NumberSymbol extends Symbol {
		
		public NumberSymbol(String value) {
			super(value);
		}
		
		@Override
		public boolean isNumberSymbol() {
			return true;
		}
	}
	
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
	
	/**
	 * Read a number. [0-9]+
	 */
	private String readNumber(CharacterIterator iter) {
		char ch = iter.current();
		StringBuilder str = new StringBuilder();
		do {
			str.append(ch);
			ch = iter.next();
		} while (ch != DONE && isDigit(ch));
		iter.previous();
		return str.toString();
	}
	
	/**
	 * Read a string in quotes. ".*"
	 */
	private String readString(CharacterIterator iter) {
		char ch = iter.current();
		StringBuilder str = new StringBuilder();
		do {
			str.append(ch);
			ch = iter.next();
			if (ch == DONE) {
				throw new SyntaxError("EOF unexpected. Incomplete string");
			}
		} while (ch != '"');
		str.append(ch);		
		return str.toString();
	}
	
	/**
	 * Read and ignore a comment. ;.*\n
	 * @param iter
	 */
	private void ignoreComment(CharacterIterator iter) {
		char ch = iter.current();
		do {
			ch = iter.next();
		}while(ch != DONE && ch != '\n');
	}	
	
	/**
	 * read a literal atom.  '[^()\s]*
	 */
	private String readLiteralAtom(CharacterIterator iter) {
		if (iter.current() != '\'') {
			throw new IllegalStateException();
		}
		char ch = iter.next();
		StringBuilder str = new StringBuilder();
		do {
			str.append(ch);
			ch = iter.next();
		} while (ch != DONE && !isSpaceChar(ch) && ch != '(' && ch !=  ')');
		ch = iter.previous();		
		
		return str.toString();
	}
	
	/**
	 * read a literal list. '(.*)
	 */
	private String readLiteralList(CharacterIterator iter) {
		if (iter.current() != '\'') {
			throw new IllegalStateException();
		}
		char ch = iter.next();
		if (ch != '(') {
			throw new IllegalStateException();
		}
		StringBuilder str = new StringBuilder();
		do {
			str.append(ch);
			ch = iter.next();
		} while (ch != DONE && ch !=  ')');
		if (ch != ')') {
			throw new SyntaxError("Character ')' expected");
		}
		str.append(ch);
		return str.toString();
	}

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
			else if (isSpaceChar(ch)) {
			}
			else if (isDigit(ch)) {
				String number = readNumber(iter);
				res.add(new NumberSymbol(number));
			}
			else if (ch == '"') {
				String str = readString(iter);
				res.add(new Symbol(str));
			}
			else if (ch == ';') {
				ignoreComment(iter);
			}	
			else if (ch == '\'') {
				ch = iter.next();
				if (ch == '(') {
					iter.previous();
					String str = readLiteralList(iter);
					res.add(new Symbol(str));
				}
				else {
					iter.previous();
					String str = readLiteralAtom(iter);
					res.add(new Symbol(str));
				}
			}
			else {
				StringBuilder str = new StringBuilder();
				do {
					str.append(ch);
					ch = iter.next();
				} while (ch != DONE && !isSpaceChar(ch) && ch != '(' && ch !=  ')');
				res.add(new Atom(str.toString()));
				ch = iter.previous();
			}
		}
		return res;
	}
}
