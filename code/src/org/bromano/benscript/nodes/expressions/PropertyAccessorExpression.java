package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;

public class PropertyAccessorExpression implements Expression {
    public Expression expression;
    public Lexeme property;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        Primary lhs = this.expression.evaluate(context);

        return lhs.access(property.value);
    }
}
