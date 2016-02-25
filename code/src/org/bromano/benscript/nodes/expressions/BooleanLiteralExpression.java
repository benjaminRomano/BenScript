package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.BooleanPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;

public class BooleanLiteralExpression implements Expression {
    public Lexeme bool;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        if (bool.value.equals("true")) {
           return new BooleanPrimary(true);
        }

        return new BooleanPrimary(false);
    }
}
