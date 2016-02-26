package org.bromano.benscript.nodes.statements;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.expressions.Expression;

public class VariableDeclarationStatement implements Statement {
    public Lexeme name;
    public Expression expression;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        context.addPrimary(name.value, expression.evaluate(context));

        return new NullPrimary();
    }
}
