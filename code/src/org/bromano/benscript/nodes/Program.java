package org.bromano.benscript.nodes;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.BreakPrimary;
import org.bromano.benscript.evaluator.primaries.special.ContinuePrimary;
import org.bromano.benscript.evaluator.primaries.special.ReturnPrimary;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;

public class Program implements ParserNode {
    public List<Statement> statements;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        Primary result = new NullPrimary();

        for (Statement s : this.statements) {
            result = s.evaluate(context);

            if (result instanceof BreakPrimary) {
                throw new EvaluatorException("Cannot use break statement outside loop");
            } else if (result instanceof ReturnPrimary) {
                throw new EvaluatorException("Cannot use return statement outside function");
            } else if (result instanceof ContinuePrimary) {
                throw new EvaluatorException("Cannot use continue statement outside loop");
            }
        }

        return result;
    }
}
