package mitalgo.nanolisp;

import java.util.List;

public class SubstractFunction extends Function {

	public SubstractFunction(Eval evaluator) {
		super(evaluator);
	}

	@Override
	public Node eval(List<Node> params) {
		int sum = 0;
		boolean first = true;
		for(Node node: evalEach(params)) {
			if (node.isNumber()) {
				int num = node.number().intValue();
				if (first) {
					sum = num;
					first = false;
				}
				else {
					sum -= num;
				}
			}
		}
		return new Node(sum);
	}
	

}
