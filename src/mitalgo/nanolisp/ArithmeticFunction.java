/**
 * 
 */
package mitalgo.nanolisp;

import java.util.List;

public abstract class ArithmeticFunction extends Function {

	public ArithmeticFunction(Eval evaluator) {
		super(evaluator);
	}
	
	public static enum Arithmetic { IntegerType, FloatType, DecimalType }
	
	public static Arithmetic arthmeticType(List<Node> params) {
		boolean floatType = false;
		for(Node node:params) {
			if (node.isNumber()) {
				if (node.isDecimal())
					return Arithmetic.DecimalType;
				if (node.isFloat())
					floatType = true;
			}
		}
		if (floatType)
			return Arithmetic.FloatType;
		return Arithmetic.IntegerType;
	}
	
	@Override
	public Node eval(List<Node> params) {
		List<Node> evaled = evalEach(params);
		Arithmetic a = arthmeticType(evaled);
		if (a == Arithmetic.IntegerType) {
			return integerOp(evaled);
		}
		if (a == Arithmetic.FloatType) {
			return floatOp(evaled);
		}
		else if (a == Arithmetic.DecimalType){
			return decimalOp(evaled);
		}
		throw new Error("Unknown arithmetic type");
	}

	protected void assertTwoNumbers(List<Node> params) {
		Assert.isTrue(params.size() == 2);
		Assert.notNull(params.get(0));
		Assert.notNull(params.get(1));
		Assert.isTrue(params.get(0).isNumber());
		Assert.isTrue(params.get(1).isNumber());
	}
	
	protected abstract Node integerOp(List<Node> evaled);
	protected abstract Node floatOp(List<Node> evaled);
	protected abstract Node decimalOp(List<Node> evaled);
	
}