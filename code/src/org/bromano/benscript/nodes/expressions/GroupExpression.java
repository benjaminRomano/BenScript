package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

public class GroupExpression implements Expression {
    public Expression expression;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        return expression.evaluate(context);
    }
}
