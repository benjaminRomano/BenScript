package org.bromano.benscript.evaluator.complexTypes;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.evaluator.primaries.IdentifierPrimary;
import org.bromano.benscript.evaluator.primaries.NullPrimary;
import org.bromano.benscript.evaluator.primaries.Primary;
import org.bromano.benscript.evaluator.primaries.special.ReturnPrimary;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.List;

public class BenScriptBasicLambda implements BenScriptLambda {

    public Environment context;
    public Statement statement;
    private List<String> paramNames;

    public BenScriptBasicLambda(Environment context, Statement statement, List<String> paramNames) {

        this.context = context;
        this.statement = statement;
        this.paramNames = paramNames;
    }

    @Override
    public List<String> getParamNames() {
        return this.paramNames;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BenScriptBasicLambda)) {
            return false;
        }

        BenScriptBasicLambda bsLambda = (BenScriptBasicLambda) obj;

        return bsLambda.statement.equals(this.statement)
                && bsLambda.paramNames.equals(this.paramNames)
                && bsLambda.context.equals(this.context);
    }

    public Primary evaluate(List<Primary> args) throws EvaluatorException {
        Environment newContext = new Environment(context);

        if (this.paramNames.size() < args.size()) {
            throw new EvaluatorException("Too many arguments supplied.");
        } else if (this.paramNames.size() > args.size()){
            throw new EvaluatorException("Not enough arguments supplied.");
        }

        for (int i = 0; i < this.paramNames.size(); i++) {
            newContext.addPrimary(this.paramNames.get(i), new IdentifierPrimary(args.get(i).getPrimary()));
        }

        Primary result = statement.evaluate(newContext);

        if (result instanceof ReturnPrimary) {
            ReturnPrimary returnPrimary = (ReturnPrimary) result;

            return returnPrimary.getValue();
        }

        return new NullPrimary();
    }
}
