package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.IntegerPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;

public class IntegerLiteralExpression implements Expression {
    public Lexeme integer;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        return new IntegerPrimary(Integer.parseInt(integer.value));
    }
}
