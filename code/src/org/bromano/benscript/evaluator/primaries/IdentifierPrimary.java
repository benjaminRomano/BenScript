package org.bromano.benscript.evaluator.primaries;

import org.bromano.benscript.evaluator.Environment;
import org.bromano.benscript.evaluator.EvaluatorException;

public class IdentifierPrimary extends BasePrimary<Object> {

    private Environment context;
    private String variableName;

    public IdentifierPrimary(Environment context, String variableName) {

        this.type = PrimaryType.Identifier;
        this.context = context;
        this.variableName = variableName;
    }

    @Override
    public Object getValue() throws EvaluatorException {
        return this.getVariable().getValue();
    }

    @Override
    public PrimaryType getType() {
        return this.getVariable().getType();
    }

    @Override
    public BooleanPrimary castToBoolean() throws EvaluatorException {
        return this.getVariable().castToBoolean();
    }

    @Override
    public StringPrimary castToString() throws EvaluatorException {
        return this.getVariable().castToString();
    }

    @Override
    public IntegerPrimary castToInteger() throws EvaluatorException {
        return this.getVariable().castToInteger();
    }

    @Override
    public Primary add(Primary rhs) throws EvaluatorException {
        return this.getVariable().add(rhs);
    }

    @Override
    public Primary subtract(Primary rhs) throws EvaluatorException {
        return this.getVariable().subtract(rhs);
    }

    @Override
    public Primary multiply(Primary rhs) throws EvaluatorException {
        return this.getVariable().multiply(rhs);
    }

    @Override
    public Primary divide(Primary rhs) throws EvaluatorException {
        return this.getVariable().divide(rhs);
    }

    @Override
    public Primary modulo(Primary rhs) throws EvaluatorException {
        return this.getVariable().modulo(rhs);
    }

    @Override
    public Primary exponent(Primary rhs) throws EvaluatorException {
        return this.getVariable().exponent(rhs);
    }

    @Override
    public Primary or(Primary rhs) throws EvaluatorException {
        return this.getVariable().or(rhs);
    }

    @Override
    public Primary and(Primary rhs) throws EvaluatorException {
        return this.getVariable().and(rhs);
    }

    @Override
    public Primary lessThan(Primary rhs) throws EvaluatorException {
        return this.getVariable().lessThan(rhs);
    }

    @Override
    public Primary greaterThan(Primary rhs) throws EvaluatorException {
        return this.getVariable().greaterThan(rhs);
    }

    @Override
    public Primary greaterThanEquals(Primary rhs) throws EvaluatorException {
        return this.getVariable().greaterThanEquals(rhs);
    }

    @Override
    public Primary equals(Primary rhs) throws EvaluatorException {
        return this.getVariable().equals(rhs);
    }

    @Override
    public Primary notEquals(Primary rhs) throws EvaluatorException {
        return this.getVariable().notEquals(rhs);
    }

    @Override
    public Primary assign(Primary rhs) throws EvaluatorException {
        this.context.updateVariable(variableName, rhs);
        return this;
    }

    private Primary getVariable() {
        return this.context.getVariable(this.variableName);
    }
}
