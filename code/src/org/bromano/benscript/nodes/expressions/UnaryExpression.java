package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;

public class UnaryExpression implements Expression {
    public Lexeme operator;
    public Expression expression;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        if (operator.kind == LexemeKind.Exclamation) {
            return expression.evaluate(context).not();
        } else if (operator.kind == LexemeKind.Minus) {
            return expression.evaluate(context).minus();
        }

        throw new EvaluatorException("Could not interpret unary operator: " + operator.value);
    }
}
