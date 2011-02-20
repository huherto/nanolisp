/**
 * 
 */
package mitalgo.nanolisp;

import java.util.ArrayList;
import java.util.List;

public abstract class Function {

	private Eval evaluator;

	public Function(Eval evaluator) {
		this.evaluator = evaluator;
	}
	
	abstract public Node eval(List<Node> params);
	
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
}