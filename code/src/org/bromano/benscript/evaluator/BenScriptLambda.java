package org.bromano.benscript.evaluator;

import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;
import java.util.Map;

public class BenScriptLambda {

    public Environment context;
    public Statement statement;
    public List<String> params;

    public BenScriptLambda(Environment context, Statement statement, List<String> params) {

        this.context = context;
        this.statement = statement;
        this.params = params;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BenScriptLambda)) {
            return false;
        }

        BenScriptLambda bsLambda = (BenScriptLambda) obj;

        return bsLambda.statement.equals(this.statement)
                && bsLambda.params.equals(this.params)
                && bsLambda.context.equals(this.context);
    }

    public Primary evaluate(List<Primary> args) throws EvaluatorException {
        Environment newContext = new Environment(context);

        if (this.params.size() < args.size()) {
            throw new EvaluatorException("Too many parameters supplied.");
        } else if (this.params.size() > args.size()){
            throw new EvaluatorException("Not enough parameters supplied.")
        }

        for (int i = 0; i < this.params.size(); i++) {
            newContext.variables.put(this.params.get(i), args.get(i));
        }

        return statement.evaluate(newContext);
    }
}
