package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;

public class NameExpression implements Expression {
    public Lexeme name;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        if (!context.variables.containsKey(name.value)) {
            return new NullPrimary();
        }

        return context.getVariable(name.value);
    }
}
