package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.BreakPrimary;
import org.bromano.benscript.evaluator.primaries.special.ContinuePrimary;
import org.bromano.benscript.evaluator.primaries.special.ReturnPrimary;
import org.bromano.benscript.nodes.expressions.Expression;

public class IfStatement implements Statement {
    public Expression conditional;
    public Statement statement;
    public Statement elseStatement;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        Environment newContext = new Environment(context);


        Primary result = new NullPrimary();

        if (this.conditional.evaluate(context).castToBoolean().getValue()) {
            result = statement.evaluate(newContext);
        } else if (elseStatement != null){
            result = elseStatement.evaluate(newContext);
        }

        if (result instanceof ReturnPrimary
                || result instanceof BreakPrimary
                || result instanceof ContinuePrimary) {
            return result;
        }

        return new NullPrimary();
    }
}
