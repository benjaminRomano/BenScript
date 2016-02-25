package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;

public class IndexExpression implements Expression {
    public Expression expression;
    public Expression indexExpression;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        Primary lhs = expression.evaluate(context);

        return lhs.index(indexExpression.evaluate(context));
    }
}
