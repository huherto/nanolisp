package mitalgo.nanolisp;

import junit.framework.TestCase;

public class TestEval extends TestCase {
	
	public void assertDouble(Eval eval, double expected, String expr) {
		Node res = eval.eval(expr);
		assertTrue( Math.abs(res.number().doubleValue() - expected) < 0.01);	
	}
	
	public void assertInt(Eval eval, int expected, String expr) {
		Node res = eval.eval(expr);
		assertEquals(expected, res.number().intValue());	
	}
	
	public void assertStr(Eval eval, String expected, String expr) {
		Node res = eval.eval(expr);
		assertEquals(expected, res.strValue());	
	}
	
	public void testEvalBasic() {
		Eval eval = new Eval();
		
		assertInt(eval, 1000, "1000");
		assertStr(eval, "casa", "casa");
		assertStr(eval, "(+ 2 2)", "'(+ 2 2)");
		
		assertInt(eval, 28, "(+ 2 2 4 20)");
		assertInt(eval, 9, "(+ (+ 2 2) (+ 3 2))");
		assertInt(eval, 4444, "(- (+ 9000 900 90 9 ) (+ 5000 500 50 5 ))");
		assertInt(eval, 20, "(* 2 2 5)");
		assertInt(eval, 3, "(/ 10 3)");
		assertInt(eval, 1000, "(expt 10 3)");
		
		assertDouble(eval, 4.2, "(+ 2.2 2)");
		assertDouble(eval,  33, "(* 10 1.1 3)");
		assertDouble(eval, 2.12, "(- 12.12 10)");
		assertDouble(eval, 4.2, "(+ 2.2 2)");		
		assertDouble(eval, 2.0, "(/ 10.0 5)");
		assertDouble(eval, 4.0, "(expt 16 0.5 )");
		
		assertInt(eval, 4, "(length \"hola\")");
		
		eval.eval("(defun foo ( a ) ( + 5 3 ))");
		assertInt(eval, 8, "(foo)");
	}

}
