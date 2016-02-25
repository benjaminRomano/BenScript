package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.BreakPrimary;

public class BreakStatement implements Statement {
    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        return new BreakPrimary();
    }
}
