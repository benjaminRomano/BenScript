package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;
import org.bromano.benscript.nodes.expressions.Expression;

import java.util.List;

public class DelayedPrimary extends BasePrimary {

    private Expression expression;
    private Environment context;
    private Primary value;

    public DelayedPrimary(Environment context, Expression expression) throws EvaluatorException {

        this.context = context;
        this.expression = expression;
        this.value = null;
    }

    @Override
    public PrimaryType getType() {
        if (this.value != null) {
            return this.value.getType();
        } else {
            return PrimaryType.Delayed;
        }
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return this.retrieveValue().castToString();
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
            return this.retrieveValue().castToBoolean();
    }

    @Override
    public IntegerPrimary castToInteger() throws EvaluatorException {
        return this.retrieveValue().castToInteger();
    }

    @Override
    public Primary add(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().add(rhs);
    }

    @Override
    public Primary subtract(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().subtract(rhs);
    }

    @Override
    public Primary multiply(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().multiply(rhs);
    }

    @Override
    public Primary divide(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().divide(rhs);
    }

    @Override
    public Primary modulo(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().modulo(rhs);
    }

    @Override
    public Primary exponent(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().exponent(rhs);
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().or(rhs);
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().and(rhs);
    }

    @Override
    public Primary lessThan(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().lessThan(rhs);
    }

    @Override
    public Primary greaterThan(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().greaterThan(rhs);
    }

    @Override
    public Primary lessThanEquals(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().lessThanEquals(rhs);
    }

    @Override
    public Primary greaterThanEquals(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().greaterThanEquals(rhs);
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().equals(rhs);
    }

    @Override
    public Primary index(Primary index) throws EvaluatorException {
        return this.retrieveValue().index(index);
    }

    @Override
    public Primary access(String prop) throws EvaluatorException {
        return this.retrieveValue().access(prop);
    }

    @Override
    public Primary call(List<Primary> args) throws EvaluatorException {
        return this.retrieveValue().call(args);
    }

    @Override
    public Primary minus() throws EvaluatorException {
        return this.retrieveValue().minus();
    }

    @Override
    public Primary not() throws EvaluatorException {
        return this.retrieveValue().not();
    }

    @Override
    public Primary assign(Primary rhs) throws EvaluatorException {
        return this.retrieveValue().assign(rhs);
    }

    public Primary retrieveValue() throws EvaluatorException {
        if (this.value != null) {
            return this.value;
        }

        this.value = this.expression.evaluate(context);

        return this.value;
    }
}
