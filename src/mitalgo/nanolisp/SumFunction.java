/**
 * 
 */
package mitalgo.nanolisp;

import java.util.List;

public class SumFunction extends Function {

	public SumFunction(Eval evaluator) {
		super(evaluator);
	}

	@Override
	public Node eval(List<Node> params) {
		int sum = 0;
		for(Node node: evalEach(params)) {
			if (node.isNumber()) {
				sum += node.number().intValue();
			}
		}
		return new Node(sum);
	}
	
}