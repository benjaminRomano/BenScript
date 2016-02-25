package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.BreakPrimary;
import org.bromano.benscript.evaluator.primaries.special.ContinuePrimary;
import org.bromano.benscript.evaluator.primaries.special.ReturnPrimary;
import org.bromano.benscript.nodes.expressions.Expression;

public class WhileStatement implements Statement {
    public Expression conditional;
    public Statement statement;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        while (conditional.evaluate(context).castToBoolean().getValue()) {

            Environment newContext = new Environment(context);

            Primary result = statement.evaluate(newContext);

            if (result instanceof BreakPrimary) {
                return new NullPrimary();
            } else if (result instanceof ReturnPrimary) {
                return result;
            }
        }

        return new NullPrimary();
    }
}
