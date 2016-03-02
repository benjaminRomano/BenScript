package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.ReturnPrimary;
import org.bromano.benscript.nodes.expressions.Expression;

public class ReturnStatement implements Statement {
    public Expression expression;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        if (this.expression != null) {
            return new ReturnPrimary(this.expression.evaluate(context));
        }

        return new ReturnPrimary(new NullPrimary());
    }
}
