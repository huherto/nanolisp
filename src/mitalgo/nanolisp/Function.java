package mitalgo.nanolisp;

import java.util.ArrayList;
import java.util.List;

public abstract class Function {

	protected Eval evaluator;
	
	public Function(Eval evaluator) {
		this.evaluator = evaluator;
	}

	public abstract Node eval(List<Node> params);

	protected List<Node> evalEach(List<Node> params) {
		List<Node> res = new ArrayList<Node>();
		for(Node node: params) {
			res.add(evaluator.eval(node));
		}
		return res;
	}

	protected Node eval(Node node) {
		return evaluator.eval(node);
	}

	public static Node first(List<Node> list) {
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	public static List<Node> rest(List<Node> list) {
		if (list.isEmpty()) {
			return null;
		}
		return list.subList(1, list.size());
	}

}