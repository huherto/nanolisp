package mitalgo.nanolisp;

import java.math.BigDecimal;
import java.util.List;

public class DivisionFunction extends ArithmeticFunction {

	public DivisionFunction(Eval evaluator) {
		super(evaluator);
	}

	@Override
	protected Node decimalOp(List<Node> evaled) {
		BigDecimal res = null;
		for(Node node: evaled) {
			if (node.isNumber()) {
				if (res == null)
					res = node.toDecimal();
				else
					res = res.divide(node.toDecimal()); 
			}
		}
		return new Node(res);
	}

	@Override
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
					res /= value;
			}
		}
		return new Node(res);		
	}

	@Override
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
					res /= value;
			}
		}
		return new Node(res);		
	}

}
