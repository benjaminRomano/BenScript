package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

import java.util.ArrayList;
import java.util.List;

public class CallExpression implements Expression {
    public Expression  function;
    public List<Expression> arguments;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        Primary lhs = function.evaluate(context);

        List<Primary> primaryArgs = new ArrayList<>();

        for (Expression e : this.arguments) {
            primaryArgs.add(e.evaluate(context));
        }

        return lhs.call(primaryArgs);
    }
}
