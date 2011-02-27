package mitalgo.nanolisp;

import java.math.BigDecimal;
import java.util.List;

public class SubtractFunction extends ArithmeticFunction {

	public SubtractFunction(Eval evaluator) {
		super(evaluator);
	}
	
	protected Node integerOp(List<Node> evaled) {
		long res = 0;
		boolean first = true;
		for(Node node: evaled) {
			if (node.isNumber()) {
				long value = node.number().longValue();
				if (first) {
					res = value;
					first = false;
				}
				else
					res -= value;
			}
		}
		return new Node(res);		
	}
	
	protected Node floatOp(List<Node> evaled) {
		double res = 0;
		boolean first = true;
		for(Node node: evaled) {
			if (node.isNumber()) {
				double value = node.number().doubleValue();
				if (first) {
					res = value;
					first = false;
				}
				else
					res -= value;
			}
		}
		return new Node(res);		
	}
	
	protected Node decimalOp(List<Node> evaled) {
		BigDecimal res = null;
		for(Node node: evaled) {
			if (node.isNumber()) {
				if (res == null)
					res = node.toDecimal();
				else
					res = res.subtract(node.toDecimal()); 
			}
		}
		return new Node(res);
	}
}
