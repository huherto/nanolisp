package mitalgo.nanolisp;

import java.util.List;

public class DefunFunction extends Function {

	public DefunFunction(Eval evaluator) {
		super(evaluator);
	}

	@Override
	public Node eval(List<Node> params) {
		Assert.isTrue(params.size() == 3);
		Assert.isTrue(params.get(0).isString());
		Assert.isTrue(params.get(1).isList());
		
		String funcName = params.get(0).strValue();
		final List<Node> args = params.get(1).getList();
		final Node expr = params.get(2);
		
		Function func = new Function(evaluator) {
			
			@Override
			public Node eval(List<Node> params) {
				
				evaluator.pushFrame(args, params);
				Node res = evaluator.eval(expr);
				evaluator.popFrame();
				
				return res;
			}
			
		};
		
		evaluator.addFunction(funcName, func);
		
		return new Node(funcName);
	}

}
