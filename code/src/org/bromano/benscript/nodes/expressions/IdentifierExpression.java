package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;

public class IdentifierExpression implements Expression {
    public Lexeme name;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        return context.getVariable(name.value);
    }
}
