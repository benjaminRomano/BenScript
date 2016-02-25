package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.BreakPrimary;
import org.bromano.benscript.evaluator.primaries.special.ContinuePrimary;
import org.bromano.benscript.evaluator.primaries.special.ReturnPrimary;

import java.util.List;

public class CompoundStatement implements Statement {
    public List<Statement> statements;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        Environment newContext = new Environment(context);

        Primary result = new NullPrimary();

        for (Statement s : this.statements)  {
            result = s.evaluate(newContext);

            if (result instanceof ReturnPrimary
                    || result instanceof BreakPrimary
                    || result instanceof ContinuePrimary) {
                return result;
            }
        }

        return new NullPrimary();
    }
}
