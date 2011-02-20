package mitalgo.nanolisp;

import java.util.List;

public class Eval {
	
	public Node eval(String expr) {
		Parser parser = new Parser();
		Node tree = parser.read(expr);
		return eval(tree);
	}

	private Node eval(Node tree) {
		if (tree.isNumber()) {
			return new Node(tree.number());
		}
		if (tree.isString()) {
			return new Node(tree.strValue());
		}
		if (tree.isList()) {
			List<Node> list = tree.getList();
			if (list.size() > 0) {
				Node head = list.get(0);
				List<Node> rest = list.subList(1, list.size());
				if (head.isString() && head.strValue().equals("+")) {
					int sum = 0;
					for(Node node:rest) {
						Node subEval = eval(node);
						if (subEval.isNumber()) {
							sum += subEval.number().intValue();
						}
					}
					return new Node(sum);
				}
			}
		}
		return null;
	}

}
