/**
 * 
 */
package mitalgo.nanolisp;

import java.math.BigDecimal;
import java.util.List;

public class SumFunction extends ArithmeticFunction {

	public SumFunction(Eval evaluator) {
		super(evaluator);
	}

	protected Node integerOp(List<Node> evaled) {
		long sum = 0;
		for(Node node: evaled) {
			if (node.isNumber()) {
				sum += node.number().longValue();
			}
		}
		return new Node(sum);		
	}
	
	protected Node floatOp(List<Node> evaled) {
		double sum = 0;
		for(Node node: evaled) {
			if (node.isNumber()) {
				sum += node.number().doubleValue();
			}
		}
		return new Node(sum);
	}
	
	protected Node decimalOp(List<Node> evaled) {
		BigDecimal res = BigDecimal.ZERO;
		for(Node node: evaled) {
			if (node.isNumber()) {
				res = res.add(node.toDecimal());
			}
		}
		return new Node(res);
	}
}