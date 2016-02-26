package org.bromano.benscript.nodes.expressions;

import org.bromano.benscript.evaluator.complexTypes.BenScriptBasicLambda;
import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.LambdaPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;
import java.util.stream.Collectors;

public class LambdaExpression implements Expression {
    public List<Lexeme> parameters;
    public Statement statement;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        List<String> paramNames = this.parameters.stream()
                .map(l -> l.value)
                .collect(Collectors.toList());

        BenScriptBasicLambda bsLambda = new BenScriptBasicLambda(context, statement, paramNames);

        return new LambdaPrimary(bsLambda);
    }
}
