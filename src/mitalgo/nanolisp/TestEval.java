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
	}

}
