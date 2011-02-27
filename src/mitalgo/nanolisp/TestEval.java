package mitalgo.nanolisp;

import junit.framework.TestCase;

public class TestEval extends TestCase {
	
	public void testEvalBasic() {
		Eval eval = new Eval();
		
		Node res = eval.eval("1000");
		assertEquals(1000, res.number().intValue());
		res = eval.eval("casa");
		assertEquals("casa", res.strValue());
		res = eval.eval("(+ 2 2 4 20)");
		assertEquals(28, res.number().intValue());
		res = eval.eval("(+ (+ 2 2) (+ 3 2))");
		assertEquals(9, res.number().intValue());
		res = eval.eval("(- (+ 9000 900 90 9 ) (+ 5000 500 50 5 ))");
		assertEquals(4444, res.number().intValue());
		res = eval.eval("'(+ 2 2)");
		assertEquals("(+ 2 2)", res.strValue());
		res = eval.eval("(* 2 2 5)");
		assertEquals(20, res.number().intValue());
		res = eval.eval("(+ 2.2 2)");
		assertTrue( Math.abs(res.number().doubleValue() - 4.2) < 0.01);
		res = eval.eval("(* 10 1.1 3)");
		assertTrue( Math.abs(res.number().doubleValue() - 33) < 0.01);
		res = eval.eval("(- 12.12 10)");
		assertTrue( Math.abs(res.number().doubleValue() - 2.12) < 0.01);
	}

}
