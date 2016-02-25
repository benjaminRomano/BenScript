package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.lexer.LexemeKind;

public class PostfixExpression implements Expression {
    public Expression expression;
    public Lexeme operator;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        if (operator.kind == LexemeKind.QuestionMark) {
            return expression.evaluate(context).optional();
        }

        throw new EvaluatorException("Could not interpret postfix operator: " + operator.value);
    }
}
