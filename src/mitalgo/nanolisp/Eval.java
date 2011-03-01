package mitalgo.nanolisp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eval {
	
	private Map<String, Function> functions;
	
	public Eval() {
		functions = new HashMap<String, Function>();
		functions.put("+", new SumFunction(this));
		functions.put("-", new SubtractFunction(this));
		functions.put("*", new MultiplyFunction(this));
		functions.put("/", new DivisionFunction(this));
		functions.put("expt", new ExptFunction(this));
		functions.put("length", new LengthFunction(this));
	}

	public Node eval(String expr) {
		Parser parser = new Parser();
		Node tree = parser.read(expr);
		return eval(tree);
	}

	public Node eval(Node tree) {
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
				if (head.isString()) {
					Function funct = functions.get(head.strValue());
					if (funct != null) {
						return funct.eval(rest);
					}
					
				}
			}
		}
		return null;
	}

}
