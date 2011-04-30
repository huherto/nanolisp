package mitalgo.nanolisp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Eval {
	
	class Frame {
		Map<String, Node> variables = new HashMap<String, Node>();
		
		public void put(String name, Node value) {
			variables.put(name, value);
		}
		
		public Node get(String name) {
			return variables.get(name);
		}
	};
	
	private Map<String, Function> functions;
	
	private Stack<Frame> frames = new Stack<Frame>();
	
	private Frame global = new Frame();
	
	public Eval() {
		functions = new HashMap<String, Function>();
		functions.put("+", new SumFunction(this));
		functions.put("-", new SubtractFunction(this));
		functions.put("*", new MultiplyFunction(this));
		functions.put("/", new DivisionFunction(this));
		functions.put("expt", new ExptFunction(this));
		functions.put("length", new LengthFunction(this));
		functions.put("defun", new DefunFunction(this));
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

	public void pushFrame(List<Node> args, List<Node> params) {	
		Frame frame = new Frame();
		int numArgs = args.size();
		for(int i = 0; i < numArgs; i++) {
			Node arg   = args.get(i);
			Node value;
			if (i < numArgs - 1 || params.size() == args.size()) {
				value = params.get(i);
			}
			else {
				value = new Node();
				while(i < params.size()) {
					value.add(params.get(i));
					i++;
				}
			}
			frame.put(arg.strValue(), value);
		}
		frames.push(frame);
	}
	
	public Frame popFrame() {
		return frames.pop();
	}
	
	public void addFunction(String name, Function func) {
		functions.put(name, func);
	}
}
