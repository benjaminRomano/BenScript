package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.BenScriptLambda;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.nodes.statements.Statement;

import java.util.Iterator;
import java.util.List;

public class LambdaPrimary extends BasePrimary<Statement> {

    BenScriptLambda bsLambda;

    public LambdaPrimary(BenScriptLambda bsLambda) {

        this.type = PrimaryType.Lambda;
        this.bsLambda = bsLambda;
    }

    @Override
    public Statement getValue() throws EvaluatorException {
        throw new EvaluatorException("Cannot extract value from " + this.type.name());
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return new BooleanPrimary(true);
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        StringBuilder paramsList = new StringBuilder();

        Iterator<String> iterator = this.bsLambda.params.iterator();

        while (iterator.hasNext()) {

            paramsList.append(iterator.next());

            if (iterator.hasNext()) {
                paramsList.append(",");
            }
        }

        return new StringPrimary("Func<" + paramsList.toString() + ">");
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return new BooleanPrimary(this.bsLambda.equals(rhs.getValue()));
    }

    @Override
    public Primary call(List<Primary> args) throws EvaluatorException {
        return this.bsLambda.evaluate(args);
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().or(rhs.castToBoolean());
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.castToBoolean().and(rhs.castToBoolean());
    }

    @Override
    public Primary optional() throws EvaluatorException {
        return new OptionalPrimary<>(this);
    }

    @Override
    public Primary not() throws EvaluatorException {
        return new BooleanPrimary(false);
    }
}
