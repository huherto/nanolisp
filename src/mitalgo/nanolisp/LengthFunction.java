package mitalgo.nanolisp;

import java.util.List;

public class LengthFunction extends Function {

	public LengthFunction(Eval evaluator) {
		super(evaluator);
	}

	@Override
	public Node eval(List<Node> params) {
		if (!params.isEmpty()) {
			Node first = first(params);
			if (first.isString()) {
				return new Node(first.strValue().length());
			}
		}
		return null;
	}

}
