package mitalgo.nanolisp;

import java.math.BigDecimal;
import java.util.List;

public class MultiplyFunction extends ArithmeticFunction {

	public MultiplyFunction(Eval evaluator) {
		super(evaluator);
	}

	protected Node integerOp(List<Node> evaled) {
		long sum = 1;
		for(Node node: evaled) {
			if (node.isNumber()) {
				sum *= node.number().longValue();
			}
		}
		return new Node(sum);		
	}
	
	protected Node floatOp(List<Node> evaled) {
		double sum = 1.0;
		for(Node node: evaled) {
			if (node.isNumber()) {
				sum *= node.number().doubleValue();
			}
		}
		return new Node(sum);
	}
	
	protected Node decimalOp(List<Node> evaled) {
		BigDecimal res = BigDecimal.ONE;
		for(Node node: evaled) {
			if (node.isNumber()) {
				res = res.multiply(node.toDecimal());
			}
		}
		return new Node(res);
	}
}
