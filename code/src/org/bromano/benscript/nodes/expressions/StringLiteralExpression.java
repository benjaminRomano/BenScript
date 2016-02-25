package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.StringPrimary;
import org.bromano.benscript.lexer.Lexeme;

public class StringLiteralExpression implements Expression {
    public Lexeme string;
    public String quoteType;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {
        // TODO: Verify nothing different needs to happen depending on the quoteType
        return new StringPrimary(string.value);
    }
}
