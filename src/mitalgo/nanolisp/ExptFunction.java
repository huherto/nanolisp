package mitalgo.nanolisp;

import java.math.BigDecimal;
import java.util.List;

public class ExptFunction extends ArithmeticFunction {

	public ExptFunction(Eval evaluator) {
		super(evaluator);
	}

	@Override
	protected Node decimalOp(List<Node> evaled) {
		assertTwoNumbers(evaled);
		
		BigDecimal base = evaled.get(0).toDecimal();
		int exp  = evaled.get(1).number().intValue();
		
		return new Node(base.pow(exp));
	}

	@Override
	protected Node floatOp(List<Node> evaled) {
		assertTwoNumbers(evaled);
		
		double base = evaled.get(0).number().doubleValue();
		double exp  = evaled.get(1).number().doubleValue();
		
		return new Node(Math.pow(base, exp));
	}

	@Override
	protected Node integerOp(List<Node> evaled) {
		return floatOp(evaled);
	}

}
