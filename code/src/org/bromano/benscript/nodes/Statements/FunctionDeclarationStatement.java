package org.bromano.benscript.nodes.statements;
import org.bromano.benscript.evaluator.complexTypes.BenScriptBasicLambda;
import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.LambdaPrimary;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.lexer.Lexeme;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionDeclarationStatement implements Statement {
    public Lexeme name;
    public List<Lexeme> parameters;
    public Statement statement;

    @Override
    public Primary evaluate(Environment context) throws EvaluatorException {

        Environment newContext = new Environment(context);

        List<String> paramNames = parameters.stream().map((l) -> l.value).collect(Collectors.toList());

        BenScriptBasicLambda bsLambda = new BenScriptBasicLambda(newContext, this.statement, paramNames);
        Primary bsLambdaPrimary = new LambdaPrimary(bsLambda);

        context.addPrimary(name.value, bsLambdaPrimary);

        return new NullPrimary();
    }
}
